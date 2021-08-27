package org.rca.smis.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.rca.smis.dao.UserDAO;
import org.rca.smis.dao.impl.UserDAOImpl;
import org.rca.smis.orm.User;
import org.rca.smis.util.ApprovalStatus;
import org.rca.smis.util.UserRole;
import org.rca.smis.util.Util;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = UserDAOImpl.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("logout") != null) {
			request.getSession().invalidate();
			System.out.println("Session ID after logout >>>>>>>>: " + request.getSession().getId());
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		} else {
			String username = request.getParameter("username");
			System.out.println("Session ID in welcome page >>>>>>>>: " + request.getSession().getId()
					+ " User is >>>>: " + username);

			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usernotfound = null;

		if (username == null || username.equals("")) {
			usernotfound = "Username can't be null or empty";
		}
		if (password == null || password.equals("")) {
			usernotfound = "Password can't be null or empty";
		}

		if ((password == null || password.equals("")) && (username == null || username.equals(""))) {
			usernotfound = "Usernwme & password can't be empty";
		}

		if (usernotfound != null) {
			httpSession.setAttribute("error", usernotfound);
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		} else {

			try {
				User authenticatedUser = userDAO.getUserByDetails(username, username, Util.generateHashed512(password),
						ApprovalStatus.APPROVED);
				
				if (authenticatedUser != null) {
					UserRole privilege = authenticatedUser.getUserRole();

					httpSession.setAttribute("authenticatedUser", authenticatedUser);
					httpSession.setAttribute("privilege", privilege);

					if (privilege.equals(UserRole.ADMINISTRATOR)) {
						
						request.getRequestDispatcher("WEB-INF/homeadmin.jsp").forward(request, response);
					} else if (authenticatedUser != null && privilege.equals(UserRole.CUSTOMER)) {
						
						request.getRequestDispatcher("WEB-INF/homecustomer.jsp").forward(request, response);
					}else if (authenticatedUser != null && privilege.equals(UserRole.STUDENT)) {
						
						request.getRequestDispatcher("WEB-INF/homestudent.jsp").forward(request, response);
					}else {
						
						request.getRequestDispatcher("WEB-INF/homenowhere.jsp").forward(request, response);
					}
				} else {
					usernotfound = "Invalid user. Try again!";
					httpSession.setAttribute("error", usernotfound);
					request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
				}
			} catch (Exception e) {
				usernotfound = "Something wrong. Try again!";
				httpSession.setAttribute("error", usernotfound);
				request.getSession().invalidate();
				doGet(request, response);
			}
		}
	}

}
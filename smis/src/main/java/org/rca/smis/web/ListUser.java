package org.rca.smis.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.rca.smis.dao.OrganizationDAO;
import org.rca.smis.dao.UserDAO;
import org.rca.smis.dao.impl.OrganizationDAOImpl;
import org.rca.smis.dao.impl.UserDAOImpl;
import org.rca.smis.orm.Organization;
import org.rca.smis.orm.User;
import org.rca.smis.util.ApprovalStatus;

/**
 * Servlet implementation class ListUser
 */
public class ListUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = UserDAOImpl.getInstance();
	private OrganizationDAO organizationDAO = OrganizationDAOImpl.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageRedirect = request.getParameter("page");

		HttpSession httpSession = request.getSession();
		Object user = httpSession.getAttribute("authenticatedUser");
		System.out.println("The user in session is: " + user);

		if (pageRedirect != null) {
			if (pageRedirect.equals("users") && request.getParameter("action").equals("list")) {

				List<User> users = userDAO.getAllUsers();
				httpSession.setAttribute("users", users);
				ApprovalStatus[] statuses = ApprovalStatus.values();
				httpSession.setAttribute("approvalStatus", statuses);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/users.jsp");
				dispatcher.forward(request, response);

			} else if (pageRedirect.equals("statusapproval")) {
				if (request.getParameter("saveDataUserStatus") != null) {
					String st = request.getParameter("status");
					String[] approverIds = request.getParameterValues("usrIds");

					ApprovalStatus status = ApprovalStatus.valueOf(st);

					List<User> approvers = new ArrayList<User>();
					User approver = null;
					if (approverIds != null)

					{

						for (int i = 0; i < approverIds.length; i++) {

							approver = userDAO.getUserById(Integer.parseInt(approverIds[i]));
							approvers.add(approver);
						}
					} else {
						request.setAttribute("message", "No user selected");
					}
		
					if (approvers != null) {
						try {
							for (User us : approvers) {
									if (status != ApprovalStatus.NONE) {
										us.setStatus(ApprovalStatus.valueOf(st));
								}
								
								userDAO.updateUser(us);

							}
							request.setAttribute("message", approvers.size() + " affected by changes.");
						} catch (Exception e) {
							request.setAttribute("message", "Can't Create");
						}
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/users.jsp");
					dispatcher.forward(request, response);
				}

			} else if (pageRedirect.equals("profile")) {
				String userId = request.getParameter("id");
				User usr = userDAO.getUserById(Integer.parseInt(userId));
				httpSession.setAttribute("usr", usr);
				List<Organization> organizations = organizationDAO.findAllOrganizations();
				httpSession.setAttribute("organizations", organizations);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/useredit.jsp");
				dispatcher.forward(request, response);
			}
		} else if (request.getParameter("userregistrationsearch") != null) {
			String userId = request.getParameter("id");
			User usr = userDAO.getUserById(Integer.parseInt(userId));
			httpSession.setAttribute("usr", usr);
			List<Organization> organizations = organizationDAO.findAllOrganizations();
			httpSession.setAttribute("organizations", organizations);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/useredit.jsp");
			dispatcher.forward(request, response);
		} else {
			httpSession.setAttribute("error", "Invalid User. Try again!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

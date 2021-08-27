package org.rca.smis.web;

import java.io.IOException;
import java.util.List;

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
import org.rca.smis.util.UserRole;
import org.rca.smis.util.Util;

/**
 * Servlet implementation class CreateUserServlet
 */
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = UserDAOImpl.getInstance();
	private OrganizationDAO organizationDAO = OrganizationDAOImpl.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateUserServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pageRedirect = request.getParameter("page");
		HttpSession httpSession = request.getSession();
		Object userLog = httpSession.getAttribute("authenticatedUser");
		User usr = (User) userLog;
		if (pageRedirect != null) {
			if (pageRedirect.equals("createuser")) {
				if (request.getParameter("saveDataUser") != null) {
					User user = new User();
					String usernameauth = request.getParameter("username");
					String passwordauth = request.getParameter("password");
					String userfullname = request.getParameter("userfullname");
					String email = request.getParameter("email");
					String userRole = request.getParameter("userRole");
					String org = request.getParameter("organization");
					String st = request.getParameter("status");
					ApprovalStatus status = ApprovalStatus.valueOf(st);
					UserRole usrr = UserRole.valueOf(userRole);

					try {
						Organization organization = organizationDAO
								.findOrganizationById(Integer.parseInt(org));
						String hashedPsw = Util.generateHashed512(passwordauth);
						
						user.setUsername(usernameauth);
						user.setPassword(hashedPsw);
						user.setFullName(userfullname);
						user.setEmail(email);
						user.setUserRole(usrr);
						user.setOrganization(organization);
						user.setStatus(status);

						// sendEmail(request, response);
						// mail("aphrorwa@gmail.com", "Hello from IoT",
						// "This is to test and test");
						userDAO.saveOrUpdateUser(user);

						httpSession.setAttribute("message", "Created successfully");
					} catch (Exception e) {
						httpSession.setAttribute("message", "Can't Create");
					}

				}

			} else if (pageRedirect.equals("registration")) {

				if (request.getParameter("saveDataUserReg") != null) {
					User user = new User();
					String usernameauth = request.getParameter("username");
					String passwordauth = request.getParameter("password");
					String userfullname = request.getParameter("userfullname");
					String email = request.getParameter("email");

					try {
						if (!usernameauth.equals(null) && !usernameauth.isEmpty() && !passwordauth.equals(null) && !passwordauth.isEmpty()) {
							String hashedPsw = Util
									.generateHashed512(passwordauth);
							user.setUsername(usernameauth);
							user.setPassword(hashedPsw);
							user.setFullName(userfullname);
							user.setEmail(email);
							user.setUserRole(UserRole.NONE);
							user.setStatus(ApprovalStatus.PENDING);
							Organization org = new Organization();
							org= organizationDAO.findOrganizationById(1);
							user.setOrganization(org);

							userDAO.saveOrUpdateUser(user);
							// sendEmail(request, response);
							// mail("aphrorwa@gmail.com", "Hello from IoT",
							// "This is to test and test");

							httpSession.setAttribute(
									"message",
									"You have successfully registered. You will login to the system after being modelated by the administrator");
						} else if(usernameauth!=null || passwordauth!=null){
							httpSession.setAttribute(
									"message",
									"Username or password fields should not be empty");
						} 
					} catch (Exception e) {
						httpSession.setAttribute("message",
								"Can't register. Verify entered data and try again!");
					}
				}

			} else if (pageRedirect.equals("edituser")) {
				if (request.getParameter("saveDataUserChanges") != null) {

					String usernameauth = request.getParameter("username");
					
					String passwordauth = request.getParameter("password");
					String userfullname = request.getParameter("userfullname");
					String email = request.getParameter("email");
					String exUserProfile = request.getParameter("userid");
					String organ = request.getParameter("organization");

					User user = userDAO.getUserById(Integer
							.parseInt(exUserProfile));
					String exPassword = null;
					
					if (!usr.getUserRole().equals(UserRole.ADMINISTRATOR)){
						exPassword = request.getParameter("expassword");
					}

					if ((exPassword!=null && Util.generateHashed512(exPassword).equals(
							user.getPassword()))||usr.getUserRole().equals(UserRole.ADMINISTRATOR)) {
				

						try {
							Organization organization = organizationDAO
									.findOrganizationById(Integer
											.parseInt(organ));

							String hashedPsw = null;
							if(passwordauth != null){
							hashedPsw = Util
									.generateHashed512(passwordauth);
							}
							user.setUsername(usernameauth);
							if (hashedPsw != null) {
								user.setPassword(hashedPsw);
							}
							user.setFullName(userfullname);
							user.setEmail(email);

							if (organization != null) {
								user.setOrganization(organization);
							}
							
							userDAO.updateUser(user);
							

							httpSession.setAttribute("message",
									"You have successfully saved the user information");
						} catch (Exception e) {
							httpSession.setAttribute("message",
									"Can't save changes. Verify entered data and try again!");
						}
					} else {
						httpSession.setAttribute("message",
								"You have entered wrong existing password");
					}
				}

			}
			UserRole[] userRoles = UserRole.values();
			httpSession.setAttribute("userRoles", userRoles);
			ApprovalStatus[] statuses = ApprovalStatus.values();
			httpSession.setAttribute("approvalStatus", statuses);
			List<Organization> organizations = organizationDAO
					.findAllOrganizations();
			httpSession.setAttribute("organizations", organizations);
			request.getRequestDispatcher("WEB-INF/createuser.jsp").forward(
					request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

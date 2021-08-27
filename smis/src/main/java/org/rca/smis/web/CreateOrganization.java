package org.rca.smis.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.rca.smis.dao.OrganizationDAO;
import org.rca.smis.dao.impl.OrganizationDAOImpl;
import org.rca.smis.orm.Organization;

/**
 * Servlet implementation class CreateOrganization
 */
public class CreateOrganization extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrganizationDAO organizationDAO = OrganizationDAOImpl.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateOrganization() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pageRedirect = request.getParameter("page");
		HttpSession httpSession = request.getSession();
		if (pageRedirect.equals("createorganization")) {
			if (request.getParameter("saveDataOrganization") != null) {
				Organization organization = new Organization();
				String name = request.getParameter("name");
				String parent = request.getParameter("parent");
				String code = request.getParameter("code");

				try {
					Organization parentOrg = null;
					if (parent != null) {
						parentOrg = organizationDAO
								.findOrganizationById(Integer.parseInt(parent));
					}
					organization.setName(name);
					organization.setParent(parentOrg);
					organization.setCode(code);
					organizationDAO.saveOrUpdateOrganization(organization);
					request.setAttribute("message", "Created successfully");
				} catch (Exception e) {
					request.setAttribute("message", "Can't Create");
				}

			}
			List<Organization> organizations = organizationDAO.findAllOrganizations();
			httpSession.setAttribute("parents", organizations);

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("WEB-INF/createorganization.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

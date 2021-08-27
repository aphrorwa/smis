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
 * Servlet implementation class ListOrganization
 */
public class ListOrganization extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrganizationDAO organizationDAO = OrganizationDAOImpl.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListOrganization() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageRedirect = request.getParameter("page");
		HttpSession httpSession = request.getSession();

		if (pageRedirect != null) {
			if (pageRedirect.equals("organizations")
					&& request.getParameter("action").equals("list")) {
				List<Organization> organizations = null;
				organizations = organizationDAO.findAllOrganizations();
				httpSession.setAttribute("organizations", organizations);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("WEB-INF/organizations.jsp");
				dispatcher.forward(request, response);
			} else if (pageRedirect.equals("editorganization")) {
				String id = request.getParameter("id");
				Organization org = organizationDAO.findOrganizationById(Integer.parseInt(id));
				httpSession.setAttribute("organization", org);
				request.getRequestDispatcher("WEB-INF/show_organization.jsp")
						.forward(request, response);
			} else if (pageRedirect.equals("reportorganization")) {
				String id = request.getParameter("id");
				Organization org = organizationDAO.findOrganizationById(Integer.parseInt(id));
				httpSession.setAttribute("organization", org);
				request.getRequestDispatcher("WEB-INF/reportorganization.jsp")
						.forward(request, response);
			}
		} else {
			httpSession.setAttribute("error", "Invalid User. Try again!");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("WEB-INF/login.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

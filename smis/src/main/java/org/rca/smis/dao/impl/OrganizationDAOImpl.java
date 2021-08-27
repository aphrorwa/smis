/**
 * 
 */
package org.rca.smis.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.rca.smis.dao.OrganizationDAO;
import org.rca.smis.orm.Organization;
import org.rca.smis.orm.User;

/**
 * @author Aphrodice Rwagaju
 * 
 */
public class OrganizationDAOImpl extends DAO implements OrganizationDAO {

	public static OrganizationDAOImpl instance;

	public OrganizationDAOImpl() {

	}

	public static OrganizationDAOImpl getInstance() {
		if (instance == null) {
			return new OrganizationDAOImpl();
		} else {
			return instance;
		}
	}

	@Override
	public Organization saveOrUpdateOrganization(Organization organization) {
		try {
			begin();
			getSession().saveOrUpdate(organization);
			commit();
			return organization;

		} catch (Exception e) {
			rollback();
			return null;
		}
	}

	@Override
	public Organization updateOrganization(Organization organization) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> findAllOrganizations() {
		
		try {
			begin();
			Query query = getSession().createQuery("from Organization");
			List<Organization> results = query.list();
			commit();
			return results;

		} catch (Exception e) {
			rollback();
			return null;
		}
	}

	@Override
	public List<Organization> findOrganizationsByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Organization findOrganizationById(int id) {
		try {
			begin();
			Query query = (Query) getSession().createQuery("from Organization where id= :ref");
					
			((org.hibernate.Query) query).setInteger("ref", id);
			Organization organization = (Organization) ((org.hibernate.Query) query).uniqueResult();
			commit();
			return organization;

		} catch (Exception e) {
			rollback();
			return null;
		}
	}

	@Override
	public Organization findRoot() {
		return null;
	}

	@Override
	public Organization addChildOrganization(Organization parent,
			Organization child) {
		try {
			begin();
			child.setParent(parent);
			parent.addChild(child);
			getSession().saveOrUpdate(child);
			commit();
			return child;

		} catch (Exception e) {
			rollback();
			return null;
		}

	}

	@Override
	public Organization addUser(Organization organization, User user) {
		try {
			begin();
			organization.addUser(user);
			user.setOrganization(organization);
			getSession().saveOrUpdate(user);
			getSession().saveOrUpdate(organization);
			commit();
			return organization;

		} catch (Exception e) {
			rollback();
			return null;
		}

	}

}

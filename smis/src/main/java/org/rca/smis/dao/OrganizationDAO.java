/**
 * 
 */
package org.rca.smis.dao;

import java.util.List;

import org.rca.smis.orm.Organization;
import org.rca.smis.orm.User;

/**
 * @author Aphrodice Rwagaju
 * 
 */
public interface OrganizationDAO {

	public Organization saveOrUpdateOrganization(Organization organization);

	public Organization updateOrganization(Organization organization);

	public List<Organization> findAllOrganizations();

	public List<Organization> findOrganizationsByUser(User user);

	public Organization findOrganizationById(int id);
	
	public Organization findRoot();
	
	public Organization addChildOrganization(Organization parent, Organization child);
	
	public Organization addUser(Organization organization, User user);

}

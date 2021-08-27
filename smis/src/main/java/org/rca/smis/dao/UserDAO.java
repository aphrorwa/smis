/**
 * 
 */
package org.rca.smis.dao;

import java.util.List;

import org.rca.smis.orm.User;
import org.rca.smis.util.ApprovalStatus;
import org.rca.smis.util.UserRole;

/**
 * @author Aphrodice Rwagaju
 * 
 */
public interface UserDAO {
	
	public User saveUser(User user);

	public User updateUser(User user);

	public User saveOrUpdateUser(User user);

	public boolean deleteUser(User user);

	public User getUserById(int userId);

	public List<User> getAllUsers();

	public List<User> getUserByUsername(String username);

	public List<User> getUserByFullName(String fullName);
	
	public User getUserByUserNameAndPassword(String username, String password);
	
	public User getUserByDetails(String username, String email, String password, ApprovalStatus status);
	
	public List<User> getUserByUserRoleAndApprovalStatus(UserRole userRole, ApprovalStatus status);

}

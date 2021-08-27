/**
 * 
 */
package org.rca.smis.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.rca.smis.dao.UserDAO;
import org.rca.smis.orm.User;
import org.rca.smis.util.ApprovalStatus;
import org.rca.smis.util.UserRole;

/**
 * implements user interface extends DAO
 * 
 * @see UserDAO
 * @see DAO
 * @author Aphrodice Rwagaju
 * 
 */
public class UserDAOImpl extends DAO implements UserDAO {
	public static UserDAOImpl instance;

	/**
	 * @return user instance
	 */
	public static UserDAOImpl getInstance() {
		if (instance == null) {
			return new UserDAOImpl();

		} else {
			return instance;
		}
	}

	private UserDAOImpl() {
	}

	/**
	 * saves user
	 * 
	 * @return user
	 * @throws Exception
	 */
	@Override
	public User saveUser(User user) {
		try {
			begin();
			User usr = (User) getSession().save(user);
			commit();
			return usr;

		} catch (Exception e) {
			rollback();
			return null;
		}
	}

	/**
	 * updates user
	 * 
	 * @return user
	 * @throws Exception
	 */
	@Override
	public User updateUser(User user) {
		try {
			begin();
			getSession().update(user);
			commit();
			return user;
		} catch (Exception e) {
			rollback();
			return null;
		}
	}

	/**
	 * saves or updates user
	 * 
	 * @return user
	 * @throws Exception
	 */
	@Override
	public User saveOrUpdateUser(User user) {
		try {
			begin();
			getSession().saveOrUpdate(user);
			commit();
			return user;
		} catch (Exception e) {
			rollback();
			return null;
		}
	}

	/**
	 * deletes user
	 * 
	 * @return true
	 * @return false
	 * @throws Exception
	 */
	@Override
	public boolean deleteUser(User user) {
		try {
			begin();
			getSession().delete(user);
			commit();
			return true;
		} catch (Exception e) {
			rollback();
			return false;
		}
	}

	/**
	 * gets user by id
	 * 
	 * @return user
	 * @throws Exception
	 */
	@Override
	public User getUserById(int id) {
		try {
			begin();
			Query query = getSession().createQuery("from User where id= :ref");
			query.setInteger("ref", id);
			User user = (User) query.uniqueResult();
			commit();
			return user;
		} catch (Exception e) {
			rollback();
			return null;
		}
	}

	/**
	 * gets all users
	 * 
	 * @return list users
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		try {
			begin();
			Query query = getSession().createQuery("from User");
			List<User> users = query.list();
			commit();
			return users;
		} catch (Exception e) {
			rollback();
			return null;
		}
	}

	/**
	 * gets user by username
	 * 
	 * @return users the list of users
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByUsername(String username) {
		try {
			begin();
			Query query = getSession().createQuery("from User where username= :ref");
			query.setString("ref", username);
			List<User> users = query.list();
			commit();
			return users;
		} catch (Exception e) {
			rollback();
			return null;
		}
	}

	/**
	 * gets users by name
	 * 
	 * @return users the list of users
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByFullName(String fullName) {
		try {
			begin();
			Query query = getSession().createQuery("from User where fullName= :ref");
			query.setString("ref", fullName);
			List<User> users = query.list();
			commit();
			return users;
		} catch (Exception e) {
			rollback();
			return null;
		}
	}

	@Override
	public User getUserByUserNameAndPassword(String username, String password) {
		try {
			begin();
			Query query = getSession().createQuery("FROM User u WHERE u.username = :usrn AND " + "u.password = :pswd");
			query.setString("usrn", username);
			query.setString("pswd", password);
			User user = (User) query.uniqueResult();
			commit();
			return user;
		} catch (Exception e) {
			rollback();
			return null;
		}
	}

	@Override
	public User getUserByDetails(String username, String email, String password, ApprovalStatus status) {
		User user = null;
		try {
			begin();
			Criteria criteria = getSession().createCriteria(User.class);
			Criterion lhs = Restrictions.eq("username", username);
			Criterion rhs = Restrictions.eq("email", email);
			criteria.add(Restrictions.or(lhs, rhs));
			criteria.add(Restrictions.eq("password", password));
			criteria.add(Restrictions.eq("status", status));
			user = (User) criteria.uniqueResult();
			commit();

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByUserRoleAndApprovalStatus(UserRole userRole, ApprovalStatus status) {
		try {
			begin();
			Query query = getSession().createQuery("FROM User usr WHERE usr.userRole ="+ userRole.ordinal()+"AND usr.status ="+ status.ordinal());
			List<User> items = query.list();
			commit();
			return items;
		} catch (Exception e) {
			rollback();
			return null;
		}
	}

}
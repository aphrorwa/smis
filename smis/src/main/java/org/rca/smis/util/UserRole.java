/**
 * 
 */
package org.rca.smis.util;

/**
 * @author Aphrodice Rwagaju
 * 
 */
public enum UserRole {
	ADMINISTRATOR("IoT System Administrator"), CUSTOMER(
			"IoT Customer"), STUDENT(
			"IoT Student") ,NONE(
							"");
	private String roleDescription;

	UserRole(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

}

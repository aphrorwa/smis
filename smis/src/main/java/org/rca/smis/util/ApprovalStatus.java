/**
 * 
 */
package org.rca.smis.util;

/**
 * @author Aphrodice Rwagaju
 *
 */
public enum ApprovalStatus {
	
	APPROVED("Approved"), PENDING(
			"Pending"), VOIDED(
			"Voided"), NONE("");
	private String statusDescription;

	ApprovalStatus(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public String getApprovalStatusDescription() {
		return statusDescription;
	}
}

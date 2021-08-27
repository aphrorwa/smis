/**
 * 
 */
package org.rca.smis.orm;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * @author Aphrodice Rwagaju
 *
 */
@Entity
public class Student extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8680703317249517930L;
	private boolean isInternational;
	private boolean isPartTime;
	private boolean isOnProbation;
	@OneToMany(cascade= CascadeType.ALL, mappedBy = "student")
	private List<Enrol> enrollments;
	
	public boolean isInternational() {
		return isInternational;
	}
	public void setInternational(boolean isInternational) {
		this.isInternational = isInternational;
	}
	public boolean isPartTime() {
		return isPartTime;
	}
	public void setPartTime(boolean isPartTime) {
		this.isPartTime = isPartTime;
	}
	public boolean isOnProbation() {
		return isOnProbation;
	}
	public void setOnProbation(boolean isOnProbation) {
		this.isOnProbation = isOnProbation;
	}
	
	public List<Enrol> getEnrollments() {
		return enrollments;
	}
	public void setEnrollments(List<Enrol> enrollments) {
		this.enrollments = enrollments;
	}
	
	public Student() {
	}
	
	public Student(boolean isInternational, boolean isPartTime, boolean isOnProbation) {
		this.isInternational = isInternational;
		this.isPartTime = isPartTime;
		this.isOnProbation = isOnProbation;
	}
	
}

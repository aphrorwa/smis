/**
 * 
 */
package org.rca.smis.orm;

import javax.persistence.Entity;

/**
 * @author Aphrodice Rwagaju
 *
 */
@Entity
//@DiscriminatorValue("Instuctor")
public class Instructor extends Person {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6073878228230771199L;
	private double salary;
	
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Instructor() {
	}
	
	public Instructor(double salary) {
		this.salary = salary;
	}
	
}

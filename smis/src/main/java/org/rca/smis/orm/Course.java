/**
 * 
 */
package org.rca.smis.orm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Aphrodice Rwagaju
 *
 */
@Entity
@Table(name = "course")
public class Course implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String code;
	private int minStudents;
	private int maxStudents;
	private Date start;
	private Date end;
	private boolean isCancelled;
	@OneToMany(cascade= CascadeType.ALL, mappedBy = "course")
	private List<Enrol> enrollments;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getMinStudents() {
		return minStudents;
	}
	public void setMinStudents(int minStudents) {
		this.minStudents = minStudents;
	}
	public int getMaxStudents() {
		return maxStudents;
	}
	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public boolean isCancelled() {
		return isCancelled;
	}
	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
	

	public List<Enrol> getEnrollments() {
		return enrollments;
	}
	public void setEnrollments(List<Enrol> enrollments) {
		this.enrollments = enrollments;
	}
	
	public Course() {
	}
	public Course(String name, String code, int minStudents, int maxStudents, Date start, Date end,
			boolean isCancelled) {
		this.name = name;
		this.code = code;
		this.minStudents = minStudents;
		this.maxStudents = maxStudents;
		this.start = start;
		this.end = end;
		this.isCancelled = isCancelled;
	}
	
	

}

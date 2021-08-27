/**
 * 
 */
package org.rca.smis.orm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author Aphrodice Rwagaju
 *
 */
@Entity
@Table(name = "department")
public class Organization implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String code;
	private List<User> user;
	private Organization parent;
	private List<Organization> children;
	
	public Organization() {
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(cascade= CascadeType.ALL, mappedBy = "organization")
	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public void addUser(User user) {
		this.user.add(user);
	}
	@ManyToOne
	public Organization getParent() {
		return parent;
	}

	public void setParent(Organization parent) {
		this.parent = parent;
	}
	
	@OneToMany(cascade= CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="parent")
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Organization> getChildren() {
		return children;
	}

	public void setChildren(List<Organization> children) {
		this.children = children;
	}

	public void addChild(Organization organization) {
		this.children.add(organization);
	}

	@Override
	public String toString() {
		return getName();
	}

	/**
	 * @return the code
	 */
	@Column(unique = true)
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	@Transient
	public static List<Organization> returnAllNodes(Organization node) {
		List<Organization> listOfNodes = new ArrayList<Organization>();
		addAllNodes(node, listOfNodes);
		return listOfNodes;
	}
	@Transient
	private static void addAllNodes(Organization node, List<Organization> listOfNodes) {
		if (node != null && !listOfNodes.contains(node)) {
			listOfNodes.add(node);
			List<Organization> children = node.getChildren();
			if (children != null) {
				for (Organization child : children) {
					addAllNodes(child, listOfNodes);
				}
			}
		}
	}
}

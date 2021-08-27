/**
 * 
 */
package org.rca.smis.starter;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.rca.smis.orm.Address;
import org.rca.smis.orm.Course;
import org.rca.smis.orm.Enrol;
import org.rca.smis.orm.Instructor;
import org.rca.smis.orm.Student;

/**
 * @author Aphrodice Rwagaju
 *
 */
public class Starter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		//creating objects for school MIS
		
		
		System.out.println("Creating objects .......");
		Address add1 = new Address("Rwanda", "Kigali", "KN 59 ST Plot 13", "P.O.Box 896 Kigali Rwanda");
		
		//Not necessary in inheritance
		//Person pers1  = new Person("Aphrodice", "Rwagaju", new Date(), "+250788458261",add1);
		//Person pers2  = new Person("Jessica", "Birungi", new Date(), "+250784852816",add1);
		
		Student st1 = new Student(true, false, false);
		st1.setAddress(add1);
		st1.setDateOfBirth(new Date());
		st1.setFirstName("Rwagaju");
		st1.setLastName("Aphrodice");
		st1.setPhoneNumber("+250788458261");
		
		Instructor instr = new Instructor(250000);
		instr.setAddress(add1);
		instr.setDateOfBirth(new Date());
		instr.setFirstName("Birungi");
		instr.setLastName("Jessica");
		instr.setPhoneNumber("+250784852816");
		
		Course c1 = new Course("Sofware Analysis and Design", "SAD01", 20, 30, new Date(), new Date(), false);
		
		Enrol enr = new Enrol(new Date(), 73.5,st1,c1);
		
		//loading config file
		System.out.println("Loading configuration file .......");
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
				
		//creating session object
		System.out.println("Opening the session .......");
		@SuppressWarnings("deprecation")
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		//open transaction
		
		System.out.println("Begining transactions .......");
		Transaction transaction = session.beginTransaction();
		
		//saving objects
		
		session.saveOrUpdate(add1);
		//session.saveOrUpdate(pers1);
		//session.saveOrUpdate(pers2);
		session.saveOrUpdate(c1);
		session.saveOrUpdate(st1);
		session.saveOrUpdate(instr);
		session.saveOrUpdate(enr);
		
		System.out.println("Commiting transactions .......");
		transaction.commit();
		
		//closing connections
		System.out.println("Before closing the session.......");
		session.close();
		factory.close();
		
		System.out.println("Execution completed.......");

	}

}
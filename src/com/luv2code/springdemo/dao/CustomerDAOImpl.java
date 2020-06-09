package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

	// inject session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {

		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// create query oerder by last name
		Query<Customer> theQuery = session.createQuery(
				"from Customer order by lastName", Customer.class);
		
		// execute query and result list
		List<Customer> customers = theQuery.getResultList();
		
		//return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		Session session = sessionFactory.getCurrentSession();
		
		// if id is empty => insert else => update
		session.saveOrUpdate(theCustomer);
;		
	}

	@Override
	public Customer getCustomer(int theId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		return session.get(Customer.class, theId);
	}

	@Override
	public void deleteCustomer(int theId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query theQuery = session.createQuery(
				"delete from Customer where id=:customerId ");
		
		theQuery.setParameter("customerId", theId);
	
		theQuery.executeUpdate();
		
	}

	
}







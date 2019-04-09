package data.dao.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import exceptions.IllegalPhoneNumberException;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

import data.entities.Customer;

@Repository
public class CustomerService implements data.dao.CustomerService {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Customer addCustomer(Customer customer) {
        em.persist(customer);
        return customer;
    }

    @Override
    @Transactional
    @Cacheable("customersCache")
    public Customer getCustomerById(Long id) {
        System.out.println("called CustomerService.getCustomerById(" + id + ")");
        return em.find(Customer.class, id);
    }

    @Override
    @Transactional
    @Cacheable("customersCache")
    public List<Customer> getCustomersByName(String name) {
        System.out.println("called CustomerService.getCustomersByName(" + name + ")");
        Query query = em.createNamedQuery("Customer.findByName");
        query.setParameter("fname", name);
        return query.getResultList();
    }

    @Override
    @Transactional
    @Cacheable("customersCache")
    public List<Customer> getCustomers() {
        System.out.println("called CustomerService.getCustomers()");
        Query query = em.createNamedQuery("Customer.findAll", Customer.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void saveCustomer(final Customer customer) throws IllegalPhoneNumberException {
        if (9 > customer.getPhoneNumber().length() || customer.getPhoneNumber().length() > 15)
            throw new IllegalPhoneNumberException("This phone is not valid");
        em.merge(customer);

    }

}

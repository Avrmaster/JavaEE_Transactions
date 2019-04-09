package data.dao;

import data.entities.Customer;
import exceptions.IllegalPhoneNumberException;

import java.util.List;

public interface CustomerService {

    public Customer addCustomer(Customer customer);

    public Customer getCustomerById(Long id);

    public List<Customer> getCustomersByName(String name);

    public List<Customer> getCustomers();

    public void saveCustomer(Customer customer) throws IllegalPhoneNumberException;

}

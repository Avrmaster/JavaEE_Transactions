package worker;

import data.dao.CustomerService;
import data.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerWorker {

    @Autowired
    private CustomerService customerService;

    public void addCustomers(List<Customer> customers) {
        customers.forEach(customerService::addCustomer);
    }

    public List<Customer> getAllCustomers() {
        return customerService.getCustomers();
    }

    public List<Customer> getCustomersByName(String name) {
        return customerService.getCustomersByName(name);
    }

    public void saveCustomer(Customer customer) {
        try {
            customerService.saveCustomer(customer);
            System.out.println("Customer saved");
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}

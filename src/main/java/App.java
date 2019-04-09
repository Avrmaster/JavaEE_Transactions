import data.entities.Address;
import data.entities.Customer;
import data.entities.Lecture;
import worker.CustomerWorker;
import worker.PropertyPlaceholderConfigurer;
import worker.LectureWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("db/SpringBeans.xml");
        Lecture lecture = new Lecture();
        lecture.setName("Introduction to Spring");
        lecture.setCredits(2.5);
        LectureWorker worker = (LectureWorker) context.getBean("worker");
        if (worker.getLecturesList().size() == 0)
            worker.addLecture(lecture);

        CustomerWorker customerWorker = context.getBean(CustomerWorker.class);
        if (customerWorker.getAllCustomers().size() == 0)
            customerWorker.addCustomers(App.dummyCustomers());

        worker.getLecturesList().forEach(System.out::println);

//        System.out.println(customerWorker.getAllCustomers());
//        System.out.println("Get c ustomer by last name\n");
//        System.out.println(customerWorker.getCustomersByName("Mariel"));
//        System.out.println("\nGet customer by last name Kundik");
//        System.out.println(customerWorker.getCustomersByName("Kundik"));
//        System.out.println("\nGet customer by last name Kundik again");
//        System.out.println(customerWorker.getCustomersByName("Kundik"));
//        System.out.println("\nGet customer by last name Kundik again");
//        Customer customer = customerWorker.getCustomersByName("Kundik").get(0);
//        System.out.println(customer);
//        customer.setPhoneNumber("123");
//        customerWorker.saveCustomer(customer);

        PropertyPlaceholderConfigurer values = context.getBean(PropertyPlaceholderConfigurer.class);
        System.out.println(values.getValues());

    }

    private static List<Customer> dummyCustomers() {
        List<Customer> customers = new LinkedList<>();
        customers.add(new Customer("Kyrylo", "Kundik", "a nety ego", new Date(1000000L), new Address("lya kakaya", "KYIV GEROI", 2068, "UA")));
        customers.add(new Customer("Kolya", "Mariel", "lifecell", new Date(1500000L), new Address("lya kakaya street", "DRABIV GEROI", 1, "EU")));
        customers.add(new Customer("Max", "Nesterovich", "0961488322", new Date(2000000L), new Address("lya kakaya boulevard", "BELAYA CERKVA GEROI", 0, "UK")));
        customers.add(new Customer("Sashka", "Leskiv", "0442283228", new Date(2500000L), new Address("lya kakaya road", "KYIV GEROI", 1068, "UA")));
        return customers;
    }

}

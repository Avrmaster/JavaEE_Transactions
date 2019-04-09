package data.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customer")
@NamedQueries({
        @NamedQuery(name = Customer.FIND_ALL, query = "SELECT c FROM Customer c"),
        @NamedQuery(name = Customer.FIND_BY_NAME, query = "SELECT с FROM Customer с WHERE с.lastName = :fname")
})
public class Customer {

    public static final String FIND_ALL = "Customer.findAll";
    public static final String FIND_BY_NAME = "Customer.findByName";


//    @ManyToMany(mappedBy = "studentsAttending")
    @ManyToMany
    @JoinTable(
            name = "attendance",
            joinColumns = @JoinColumn(name = "customer_link"),
            inverseJoinColumns = @JoinColumn(name = "lecture_link")
    )
    private Set<Lecture> lecturesAttended;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    @Column(name = "c_version")
    private Integer version;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = {
            CascadeType.PERSIST, CascadeType.REMOVE
    })
    @JoinColumn(name = "address_fk")
    private Address address;

    public Customer() {

    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(String firstName, String lastName, String phoneNumber, Date dateOfBirth, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public Customer(Integer version, String firstName, String lastName, String phoneNumber, Date dateOfBirth, Address address) {
        this.version = version;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(version, customer.version) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(phoneNumber, customer.phoneNumber) &&
                Objects.equals(dateOfBirth, customer.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, firstName, lastName, phoneNumber, dateOfBirth);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", version=" + version +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address= " + address +
//                ", lecturesAttended= " + lecturesAttended +
                '}';
    }
}
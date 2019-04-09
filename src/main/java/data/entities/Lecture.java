package data.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "lecture")
@NamedQueries({
        @NamedQuery(name = Lecture.FIND_ALL, query = "SELECT l FROM Lecture l"),
})
public class Lecture {
    public static final String FIND_ALL = "Lecture.findAll";

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ManyToMany
    @JoinTable(
            name = "attendance",
            joinColumns = @JoinColumn(name = "lecture_link"),
            inverseJoinColumns = @JoinColumn(name = "customer_link")
    )
    private Set<Customer> studentsAttending;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    private String name;
    private double credits;

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

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", credits= " + credits +
                ", studentsAttending= " + studentsAttending +
                '}';
    }
}

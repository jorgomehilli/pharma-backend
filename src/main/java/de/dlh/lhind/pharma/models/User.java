package de.dlh.lhind.pharma.models;



import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;


@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"), name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

    public User(){ }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;


    @Column(nullable = false, name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(nullable = false, name = "last_name")
    @NotEmpty
    private String lastName;

    @Column(nullable = false, name = "email")
    @NotEmpty
    private String email;

    @Column(nullable = false, name = "password")
    @NotEmpty
    private String password;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "to_date")
    private Date toDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "roleId"))
    private Set<Roles> roles;

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}

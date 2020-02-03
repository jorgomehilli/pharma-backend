package de.dlh.lhind.pharma.models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"), name = "roles")
@EntityListeners(AuditingEntityListener.class)
public class Roles {

    public Roles() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(nullable = false, name = "name")
    @NotEmpty
    private String name;

    public Roles(@NotEmpty String name) {
        this.name = name;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

package de.dlh.lhind.pharma.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
@EntityListeners(AuditingEntityListener.class)
public class Cart_Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnoreProperties("hibernateLazyInitializer")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Produkt product;

    @Column(name = "quantity")
    private int quantity;

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Produkt getProduct() {
        return product;
    }

    public void setProduct(Produkt product) {
        this.product = product;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

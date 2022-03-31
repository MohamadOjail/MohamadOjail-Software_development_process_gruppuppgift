package se.ya.videobutik.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Store {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "store_id")
    private int store_id;

    // @Column(name = "manager_staff_id")

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @Column(name = "last_update")
    private Date lastUpdate;

    public Store() {
    }

    public Store(int store_id, Address address, Date lastUpdate) {
        this.store_id = store_id;
        this.address = address;
        this.lastUpdate = lastUpdate;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}

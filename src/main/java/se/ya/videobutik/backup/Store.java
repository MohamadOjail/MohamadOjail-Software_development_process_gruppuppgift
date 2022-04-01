package se.ya.videobutik.backup;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Store {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "store_id")
    private int store_id;

    @Column(name = "manager_staff_id")
    private int managerStaffId;

    @Column(name = "address_id")
    private int addressId;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @OneToOne(fetch = FetchType.EAGER,mappedBy = "store")
    private Address address;


    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getManagerStaffId() {
        return managerStaffId;
    }

    public void setManagerStaffId(int managerStaffId) {
        this.managerStaffId = managerStaffId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

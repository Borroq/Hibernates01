package org.example.oneToOne.model;

import jakarta.persistence.*;

@Entity
@Table(name = "parcels")
public class Parcel {
    public Parcel() {
    }

    public Parcel( String warehouse, int price) {
        this.warehouse = warehouse;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    private ParcelAddress parcelAddress;

    private String warehouse;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ParcelAddress getParcelAddress() {
        return parcelAddress;
    }

    public void setParcelAddress(ParcelAddress parcelAddress) {
        this.parcelAddress = parcelAddress;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "id=" + id +
                ", parcelAddress=" + parcelAddress +
                ", warehouse='" + warehouse + '\'' +
                ", price=" + price +
                '}';
    }
}

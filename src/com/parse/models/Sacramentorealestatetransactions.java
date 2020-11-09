package com.parse.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table (name = "sacramentorealestatetransactions")
public class Sacramentorealestatetransactions {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zip;
    private Integer beds;
    private Integer baths;
    private Integer sq_feet;
    private String type;
    private Date sale_date;
    private Integer price;
    private Integer latitude;
    private Integer longitude;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public void setBaths(Integer baths) {
        this.baths = baths;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public void setSale_date(Date sale_date) {
        this.sale_date = sale_date;
    }

    public void setSq_feet(Integer sq_feet) {
        this.sq_feet = sq_feet;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "transaction_no")
    public Long getId() {
        return id;
    }

    @Column (name = "city")
    public String getCity() {
        return city;
    }

    @Column (name = "state")
    public String getState() {
        return state;
    }

    @Column (name = "sale_date")
    public Date getSale_date() {
        return sale_date;
    }

    @Column (name = "baths")
    public Integer getBaths() {
        return baths;
    }

    @Column (name = "beds")
    public Integer getBeds() {
        return beds;
    }

    @Column (name = "latitude")
    public Integer getLatitude() {
        return latitude;
    }

    @Column (name = "longitude")
    public Integer getLongitude() {
        return longitude;
    }

    @Column (name = "price")
    public Integer getPrice() {
        return price;
    }

    @Column (name = "sq_feet")
    public Integer getSq_feet() {
        return sq_feet;
    }

    @Column (name = "street")
    public String getStreet() {
        return street;
    }

    @Column (name = "type")
    public String getType() {
        return type;
    }

    @Column (name = "zip")
    public String getZip() {
        return zip;
    }
}

package models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table (name = "salesjan2009")
public class SalesJan implements java.io.Serializable{
    private Long id;
    private Date transaction_date;
    private String product;
    private int price;
    private String payment_type;
    private String name;
    private String city;
    private String state;
    private String country;
    private Date account_created;
    private Date last_login;
    private int latitude;
    private int longitude;

    public SalesJan() {

    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAccount_created(Date account_created) {
        this.account_created = account_created;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "sale_no")
    public Long getId() {
        return id;
    }

    @Column (name = "transaction_name")
    public Date getAccount_created() {
        return account_created;
    }

    @Column (name = "last_login")
    public Date getLast_login() {
        return last_login;
    }

    @Column (name = "country")
    public String getCountry() {
        return country;
    }

    @Column (name = "transaction_date")
    public Date getTransaction_date() {
        return transaction_date;
    }

    @Column (name = "latitude")
    public int getLatitude() {
        return latitude;
    }

    @Column (name = "longitude")
    public int getLongitude() {
        return longitude;
    }

    @Column (name = "price")
    public int getPrice() {
        return price;
    }

    @Column (name = "city")
    public String getCity() {
        return city;
    }

    @Column (name = "name")
    public String getName() {
        return name;
    }

    @Column (name = "payment_type")
    public String getPayment_type() {
        return payment_type;
    }

    @Column (name = "product")
    public String getProduct() {
        return product;
    }

    @Column (name = "state")
    public String getState() {
        return state;
    }

}


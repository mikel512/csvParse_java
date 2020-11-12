package com.parse.models; 

import javax.persistence.*; 

@Entity 
@Table (name ="salesjan2009") 
public class SalesJan2009 implements java.io.Serializable { 
	private Long id; 
 	private String transaction_date;
 	private String product;
 	private String price;
 	private String payment_type;
 	private String name;
 	private String city;
 	private String state;
 	private String country;
 	private String account_created;
 	private String last_login;
 	private String latitude;
 	private String longitude;

	public SalesJan2009() {}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue (generator = "increment")
	@Column (name = "id")
	public Long getId() {
		 return id;
	}

	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}

	@Column (name = "transaction_date")
	public String getTransaction_date() {
		 return transaction_date;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@Column (name = "product")
	public String getProduct() {
		 return product;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Column (name = "price")
	public String getPrice() {
		 return price;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	@Column (name = "payment_type")
	public String getPayment_type() {
		 return payment_type;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column (name = "name")
	public String getName() {
		 return name;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column (name = "city")
	public String getCity() {
		 return city;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column (name = "state")
	public String getState() {
		 return state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column (name = "country")
	public String getCountry() {
		 return country;
	}

	public void setAccount_created(String account_created) {
		this.account_created = account_created;
	}

	@Column (name = "account_created")
	public String getAccount_created() {
		 return account_created;
	}

	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}

	@Column (name = "last_login")
	public String getLast_login() {
		 return last_login;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column (name = "latitude")
	public String getLatitude() {
		 return latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column (name = "longitude")
	public String getLongitude() {
		 return longitude;
	}


 }

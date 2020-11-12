package com.parse.models; 

import javax.persistence.*; 

@Entity 
@Table (name ="sacramentorealestatetransactions") 
public class Sacramentorealestatetransactions implements java.io.Serializable { 
	private Long id; 
 	private String street;
 	private String city;
 	private String zip;
 	private String state;
 	private String beds;
 	private String baths;
 	private String sq__ft;
 	private String type;
 	private String sale_date;
 	private String price;
 	private String latitude;
 	private String longitude;

	public Sacramentorealestatetransactions() {}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue (generator = "increment")
	@Column (name = "id")
	public Long getId() {
		 return id;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column (name = "street")
	public String getStreet() {
		 return street;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column (name = "city")
	public String getCity() {
		 return city;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column (name = "zip")
	public String getZip() {
		 return zip;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column (name = "state")
	public String getState() {
		 return state;
	}

	public void setBeds(String beds) {
		this.beds = beds;
	}

	@Column (name = "beds")
	public String getBeds() {
		 return beds;
	}

	public void setBaths(String baths) {
		this.baths = baths;
	}

	@Column (name = "baths")
	public String getBaths() {
		 return baths;
	}

	public void setSq__ft(String sq__ft) {
		this.sq__ft = sq__ft;
	}

	@Column (name = "sq__ft")
	public String getSq__ft() {
		 return sq__ft;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column (name = "type")
	public String getType() {
		 return type;
	}

	public void setSale_date(String sale_date) {
		this.sale_date = sale_date;
	}

	@Column (name = "sale_date")
	public String getSale_date() {
		 return sale_date;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Column (name = "price")
	public String getPrice() {
		 return price;
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

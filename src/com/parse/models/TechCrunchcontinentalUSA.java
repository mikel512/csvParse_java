package com.parse.models; 

import javax.persistence.*; 

@Entity 
@Table (name ="techcrunchcontinentalusa") 
public class TechCrunchcontinentalUSA implements java.io.Serializable { 
	private Long id; 
 	private String permalink;
 	private String company;
 	private String numemps;
 	private String category;
 	private String city;
 	private String state;
 	private String fundeddate;
 	private String raisedamt;
 	private String raisedcurrency;
 	private String round;

	public TechCrunchcontinentalUSA() {}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue (generator = "increment")
	@Column (name = "id")
	public Long getId() {
		 return id;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	@Column (name = "permalink")
	public String getPermalink() {
		 return permalink;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column (name = "company")
	public String getCompany() {
		 return company;
	}

	public void setNumemps(String numemps) {
		this.numemps = numemps;
	}

	@Column (name = "numemps")
	public String getNumemps() {
		 return numemps;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column (name = "category")
	public String getCategory() {
		 return category;
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

	public void setFundeddate(String fundeddate) {
		this.fundeddate = fundeddate;
	}

	@Column (name = "fundeddate")
	public String getFundeddate() {
		 return fundeddate;
	}

	public void setRaisedamt(String raisedamt) {
		this.raisedamt = raisedamt;
	}

	@Column (name = "raisedamt")
	public String getRaisedamt() {
		 return raisedamt;
	}

	public void setRaisedcurrency(String raisedcurrency) {
		this.raisedcurrency = raisedcurrency;
	}

	@Column (name = "raisedcurrency")
	public String getRaisedcurrency() {
		 return raisedcurrency;
	}

	public void setRound(String round) {
		this.round = round;
	}

	@Column (name = "round")
	public String getRound() {
		 return round;
	}


 }

package com.parse.models 

import javax.persistence.* 

@Entity 
@Table (name ="sacramentocrimejanuary2006") 
public class SacramentocrimeJanuary2006 implements java.io.Serializable { 
	private Long id; 
 	private String cdatetime;
 	private String address;
 	private String district;
 	private String beat;
 	private String grid;
 	private String crimedescr;
 	private String ucr_ncic_code;
 	private String latitude;
 	private String longitude;

	public SacramentocrimeJanuary2006() {}

	public void setId(Long id) {
		this.id = id;
	}

	@Column (name = "id")
	public Long getId() {
		 return id;
	}

	public void setCdatetime(String cdatetime) {
		this.cdatetime = cdatetime;
	}

	@Column (name = "cdatetime")
	public String getCdatetime() {
		 return cdatetime;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column (name = "address")
	public String getAddress() {
		 return address;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Column (name = "district")
	public String getDistrict() {
		 return district;
	}

	public void setBeat(String beat) {
		this.beat = beat;
	}

	@Column (name = "beat")
	public String getBeat() {
		 return beat;
	}

	public void setGrid(String grid) {
		this.grid = grid;
	}

	@Column (name = "grid")
	public String getGrid() {
		 return grid;
	}

	public void setCrimedescr(String crimedescr) {
		this.crimedescr = crimedescr;
	}

	@Column (name = "crimedescr")
	public String getCrimedescr() {
		 return crimedescr;
	}

	public void setUcr_ncic_code(String ucr_ncic_code) {
		this.ucr_ncic_code = ucr_ncic_code;
	}

	@Column (name = "ucr_ncic_code")
	public String getUcr_ncic_code() {
		 return ucr_ncic_code;
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

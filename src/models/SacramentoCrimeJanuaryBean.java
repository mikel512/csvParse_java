package models;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import utilities.CsvParse;

import javax.persistence.*;

@Entity
@Table (name = "sacramentocrimejanuary2006")
public class SacramentoCrimeJanuaryBean {
    private Long id;
    private String cdatetime;      // Date
    private String address;
    private Integer district;
    private String beat;
    private Integer grid;
    private String crimedescr;
    private Integer ucr_ncic_code;
    private Double latitude;
    private Double longitude;

    public SacramentoCrimeJanuaryBean() {
        //for hibernate use
    }
/*
    public SacramentoCrimeJanuaryBean(String cdatetime, String address, Integer district,
                                      String beat, Integer grid, String crimedescr, Integer ucr_ncic_code,
                                      Double latitude, Double longitude){
        // for application use
        this.cdatetime = cdatetime;
        this.address = address;
        this.district = district;
        this.beat = beat;
        this.grid = grid;
        this.crimedescr = crimedescr;
        this.ucr_ncic_code = ucr_ncic_code;
        this.latitude = latitude;
        this.longitude = longitude;
    }
*/

    public void setCdatetime(String cdatetime) {
        this.cdatetime = cdatetime;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public void setBeat(String beat) {
        this.beat = beat;
    }

    public void setGrid(Integer grid) {
        this.grid = grid;
    }

    public void setCrimedescr(String crimedescr) {
        this.crimedescr = crimedescr;
    }

    public void setUcr_ncic_code(Integer ucr_ncic_code) {
        this.ucr_ncic_code = ucr_ncic_code;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


    /**
     * getters
     */
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "crime_no")
    public Long getId() {
        return id;
    }

    @Column (name = "latitude")
    public Double getLatitude() {
        return latitude;
    }

    @Column (name = "longitude")
    public Double getLongitude() {
        return longitude;
    }

    @Column (name = "district")
    public Integer getDistrict() {
        return district;
    }

    @Column (name = "grid")
    public Integer getGrid() {
        return grid;
    }

    @Column (name = "ucr_ncic_code")
    public Integer getUcr_ncic_code() {
        return ucr_ncic_code;
    }

    @Column (name = "address")
    public String getAddress() {
        return address;
    }

    @Column (name = "beat")
    public String getBeat() {
        return beat;
    }

    @Column (name = "crime_date")
    public String getCdatetime() {
        return cdatetime;
    }

    @Column (name = "crime_description")
    public String getCrimedescr() {
        return crimedescr;
    }
}

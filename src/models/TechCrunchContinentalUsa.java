package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table (name = "techcrunchcontinentalusa")
public class TechCrunchContinentalUsa implements java.io.Serializable {
    private Long id;
    private String permalink;
    private String company;
    private int numempls;
    private String category;
    private String city;
    private String state;
    private Date fundedDate;
    private int raisedamt;
    private String raisedcurrency;
    private String round;

    public TechCrunchContinentalUsa() {}

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setFundedDate(Date fundedDate) {
        this.fundedDate = fundedDate;
    }

    public void setNumempls(int numempls) {
        this.numempls = numempls;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public void setRaisedamt(int raisedamt) {
        this.raisedamt = raisedamt;
    }

    public void setRaisedcurrency(String raisedcurrency) {
        this.raisedcurrency = raisedcurrency;
    }

    public void setRound(String round) {
        this.round = round;
    }

    @Column (name = "rec_no")
    public Long getId() {
        return id;
    }

    @Column (name = "raised_amount")
    public int getRaisedamt() {
        return raisedamt;
    }

    @Column (name = "raised_currency")
    public String getRaisedcurrency() {
        return raisedcurrency;
    }

    @Column (name = "state")
    public String getState() {
        return state;
    }

    @Column (name = "city")
    public String getCity() {
        return city;
    }

    @Column (name = "funded_date")
    public Date getFundedDate() {
        return fundedDate;
    }

    @Column (name = "num_empls")
    public int getNumempls() {
        return numempls;
    }

    @Column (name = "category")
    public String getCategory() {
        return category;
    }

    @Column (name = "company")
    public String getCompany() {
        return company;
    }

    @Column (name = "permalink")
    public String getPermalink() {
        return permalink;
    }

    @Column (name = "round")
    public String getRound() {
        return round;
    }
}

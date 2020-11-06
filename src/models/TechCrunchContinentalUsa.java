package models;

import java.sql.Date;

public class TechCrunchContinentalUsa {
    String _permalink;
    String _company;
    int _numEmpls;
    String _category;
    String _city;
    String _state;
    Date _fundedDate;
    int _raisedAmount;
    String _raisedCurrency;
    String _round;

    public TechCrunchContinentalUsa(String permalink, String company, int numEmpls, String category,
                                    String city, String state, Date fundedDate, int raisedAmount,
                                    String raisedCurrency, String round){
        _permalink = permalink;
        _company = company;
        _numEmpls = numEmpls;
        _category = category;
        _city = city;
        _state = state;
        _fundedDate = fundedDate;
        _raisedAmount = raisedAmount;
        _raisedCurrency = raisedCurrency;
        _round = round;
    }

}

package Models;

import java.sql.Date;

public class SalesJan2009 {
    Date _transactionDate;
    String _product;
    int _price;
    String _paymentType;
    String _name;
    String _city;
    String _state;
    String _country;
    Date _accountCreated;
    Date _lastLogin;
    int _latitude;
    int _longitude;

    public SalesJan2009(Date transactionDate, String product, int price, String paymentType,
                        String name, String city, String state, String country, Date accountCreated,
                        Date lastLogin, int latitude, int longitude){
        _transactionDate = transactionDate;
        _product = product;
        _price = price;
        _paymentType = paymentType;
        _name = name;
        _city = city;
        _state = state;
        _country = country;
        _accountCreated = accountCreated;
        _lastLogin = lastLogin;
        _latitude = latitude;
        _longitude = longitude;
    }
}

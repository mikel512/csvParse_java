package Models;

import java.util.Date;

public class SacramentoRealEstateTransactions {
    String _street;
    String _city;
    String _state;
    String _zip;
    int _beds;
    int _baths;
    int _sqFeet;
    String _type;
    Date _saleDate;
    int _price;
    int _latitude;
    int _longitude;

    public SacramentoRealEstateTransactions(String street, String city, String state, String zip,
                                            int beds, int baths, int sqFt, String type, Date saleDate,
                                            int price, int latitude, int longitude){
        _street = street;
        _city = city;
        _state = state;
        _zip = zip;
        _beds = beds;
        _baths = baths;
        _sqFeet = sqFt;
        _type = type;
        _saleDate = saleDate;
        _price = price;
        _latitude = latitude;
        _longitude = longitude;

    }
}

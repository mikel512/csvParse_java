package Models;

import java.util.Date;

public class SacramentoCrimeJanuary {
    Date _crimeDate;
    String _address;
    int _district;
    String _beat;
    int _grid;
    String _crimeDescription;
    int _ucrNcicCode;
    int _latitude;
    int _longitude;

    public SacramentoCrimeJanuary(Date crimeDate, String address, int district,
                                  String beat, int grid, String crimeDescription,
                                  int ucrNcic, int latitude, int longitude){
        _crimeDate = crimeDate;
        _address = address;
        _district = district;
        _beat = beat;
        _grid = grid;
        _crimeDescription = crimeDescription;
        _ucrNcicCode = ucrNcic;
        _latitude = latitude;
        _longitude = longitude;
    }
}

package Models;

import Logic.CsvParse;

import java.sql.Date;

class SacramentoCrimeJanuary {
    Date cdatetime;      // Date
    String address;
    Integer district;
    String beat;
    Integer grid;
    String crimedescr;
    Integer ucr_ncic_code;
    Double latitude;
    Double longitude;

    @CsvParse.CsvConstructor
    public SacramentoCrimeJanuary(Date cdatetime, String address, Integer district,
                                  String beat, Integer grid, String crimedescr, Integer ucr_ncic_code,
                                  Double latitude, Double longitude){
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
}

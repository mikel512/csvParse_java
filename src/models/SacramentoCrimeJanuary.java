package models;

import utilities.CsvParse;

public class SacramentoCrimeJanuary {
    String cdatetime;      // Date
    String address;
    Integer district;
    String beat;
    Integer grid;
    String crimedescr;
    Integer ucr_ncic_code;
    Double latitude;
    Double longitude;

    @CsvParse.CsvConstructor
    public SacramentoCrimeJanuary(String cdatetime, String address, Integer district,
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

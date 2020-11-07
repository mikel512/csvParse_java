package models;

import java.util.ArrayList;

public class SbaData {
    String _title;
    String _description;
    String _modified;       // if not null parse to LocalDate
    String _accessLevel;
    String _identifier;
    String _issued;         // if not null parse to LocalDate
    String _landingPage;
    String _license;
    Publisher _publisher;
    String _accrualPeriodicity;
    String _isPartOf;
    ContactPoint _contact;
    ArrayList<Distribution> _distributions;
    ArrayList<String> _keywords;
    String _bureauCode;
    String _programCode;
    ArrayList<String> _themes;
    String _language;

    public SbaData(String title, String description, String modified,
                   String accessLevel, String identifier, String issued,
                   String landingPage, String license, Publisher publisher,
                   String accrualPeriodicity, String isPartOf, ContactPoint contactPoint,
                   ArrayList<Distribution> distributions, ArrayList<String> keywords, String bureauCode,
                   String programCode,ArrayList<String> themes, String language){
        _title = title;
        _description = description;
        _modified = modified;
        _accessLevel = accessLevel;
        _identifier = identifier;
        _issued = issued;
        _landingPage = landingPage;
        _license = license;
        _publisher = publisher;
        _accrualPeriodicity = accrualPeriodicity;
        _isPartOf = isPartOf;
        _contact = contactPoint;
        _distributions = distributions;
        _keywords = keywords;
        _bureauCode = bureauCode;
        _programCode = programCode;
        _themes = themes;
        _language = language;
    }

}
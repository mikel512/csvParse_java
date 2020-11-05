package Models;

import java.sql.Date;
import java.util.ArrayList;

public class SbaData {
    String _title;
    String _description;
    Date _modified;
    String _accessLevel;
    String _identifier;
    Date _issued;
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

    public SbaData(String title, String description, Date modified,
                   String accessLevel, String identifier, Date issued,
                   String landingPage, String license, Publisher publisher,
                   String accrualPeriodicity, String isPartOf, ContactPoint contactPoint,
                   ArrayList<Distribution> distributions, ArrayList<String> keywords, String bureauCode,
                   String programCode,ArrayList<String> themes){
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

    }

}

package Logic;

import Models.ContactPoint;
import Models.Distribution;
import Models.Publisher;
import Models.SbaData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class SbaParse {

    public JSONObject getJsonObject(){
        JSONParser p = new JSONParser();
        JSONObject json = new JSONObject();
        try{
            json = (JSONObject) p.parse(new FileReader("src/Data/sba_data.json"));
            return json;
        }
        catch (IOException | ParseException e){
            e.printStackTrace();
        }
        return json;
    }

    public ArrayList<SbaData> returnSbaDataObjs(){
        ArrayList<SbaData> data = new ArrayList<>();
        JSONObject object = getJsonObject();
        JSONArray dataset = (JSONArray) object.get("dataset");

        Iterator it = dataset.iterator();
        while (it.hasNext()){
            Map current = (Map) it.next();

            Map contacts= (Map) current.get("contactPoint");
            JSONArray distributions = (JSONArray) current.get("distribution");

            String title = (String) current.get("title");
            String description = (String) current.get("description");
            String modified = (String) current.get("modified");
            String access = (String) current.get("accessLevel");
            String identifier = (String) current.get("identifier");
            String issued = (String) current.get("issued");
            String landing = (String)  current.get("landingPage");
            String license = (String) current.get("license");
            Map pub = (Map) current.get("publisher");
            Publisher publisher = new Publisher((String)pub.get("name"));
            String accrual = (String) current.get("accrualPeriodicity");
            String isPart = (String) current.get("isPartOf");
            ContactPoint contactPoint = new ContactPoint((String)contacts.get("hasEmail"), (String)contacts.get("fn"));
            ArrayList<Distribution> distributions_entry = new ArrayList<>();
            if(distributions != null){
                for(int i = 0; i < distributions.size(); i++){
                    JSONObject o = (JSONObject) distributions.get(i);
                    Distribution distr = new Distribution((String)o.get("mediaType"), (String)o.get("title"),
                            (String)o.get("description"), (String)o.get("downloadURL"),
                            (String)o.get("accessURL"));
                    distributions_entry.add(distr);
                }
            }
            ArrayList<String> keywords = (JSONArray) current.get("keyword");
            ArrayList<String> bureau = (JSONArray) current.get("bureauCode");
            ArrayList<String> program = (JSONArray) current.get("programCode");
            ArrayList<String> language = (JSONArray) current.get("language");
            ArrayList<String> themes = (ArrayList<String>) current.get("theme");

            //System.out.println("Object parsed.");
            SbaData newEntry = new SbaData(title, description, modified,
                    access, identifier, issued, landing, license, publisher,
                    accrual, isPart, contactPoint, distributions_entry,
                    keywords, bureau.get(0), program.get(0), themes,
                    (language != null)?language.get(0):null );
            data.add(newEntry);
        }

        return data;
    }

}

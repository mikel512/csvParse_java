package com.parse.utilities;

import com.parse.models.ContactPoint;
import com.parse.models.Distribution;
import com.parse.models.Publisher;
import com.parse.models.SbaData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This class supports serializing a JSONObject into SbaData objects
 */
public class SbaDataSerial {

    private JSONObject object;

    private SbaDataSerial(JSONObject object) {
        this.object = object;
    }

    /**
     * Creates a new SbaParse instance from the specified parser
     * @param object a {@link JSONObject} containing {@code n} JSON objects
     * @return a new SbaParse instance
     */
    public static SbaDataSerial newInstance(JSONObject object) {
        return new SbaDataSerial(object);
    }

    /**
     * Turns each entry in the dataset to an instance of type {@code SbaData}
     * @return a List of instances of type {@code SbaData} corresponding to each entry of the dataset
     */
    public List<SbaData> objects(){
        return getSbaDataObjects();
    }

/*
    private JSONObject getJsonObject() {
        JSONParser p = new JSONParser();
        JSONObject json = new JSONObject();
        try{
            json = (JSONObject) p.parse(new FileReader("src/Data/sba_data.json"));
            return json;
        }
        catch (IOException | ParseException e){
            e.printStackTrace();
        }
        return null;
    }
*/

    private List<SbaData> getSbaDataObjects() {
        ArrayList<SbaData> data = new ArrayList<>();
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

package Logic;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SbaParse {

    public JSONObject getJsonObject(){
        JSONParser p = new JSONParser();
        JSONObject json = new JSONObject();
        try{
            File f = new File(getClass().getResource("sba_data.json").getFile());
            json = (JSONObject) p.parse(new FileReader("src/Data/sba_data.json"));
            return json;
        }
        catch (IOException | ParseException e){
            e.printStackTrace();
        }
        return json;
    }

}

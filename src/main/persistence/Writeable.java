package persistence;

import org.json.JSONObject;


//Allows object to be converted to a JSON object
//code sourced from WorkRoom example
public interface Writeable {
    //EFFECTS: returns this as a JSON object
    JSONObject toJson();
}

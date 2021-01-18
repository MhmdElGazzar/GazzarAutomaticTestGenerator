package ViewFormGui;

import io.restassured.path.json.JsonPath;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sun.rmi.runtime.Log;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class JSONActions {
    private static String currentDirectory = System.getProperty("user.dir") + "/src/test/resources/";

    /**
     * @param response         Response from API Request.
     * @param responseJsonPath JSON responseJsonPath to retrieve the desired value.
     * @return Return the value from JSON Path provided.
     */
    public static String getJSONResponseAsValue(Response response, String responseJsonPath) {
        String result = "";
        try {
            String responseBody = response.getBody().asString();
            JsonPath jsonPath = new JsonPath(responseBody);
            result = jsonPath.getString(responseJsonPath);
        } catch (ClassCastException e) {
            System.out.println("Incorrect JSON Path: [ " + responseJsonPath + " ]");
        } catch (JsonPathException e) {
            System.out.println("Failed to parse the JSON Path: [ " + responseJsonPath + " ]");
        }

        if (result != null) {
            System.out.println("Value is: [" + result.toString() + "]");
        } else {
            System.out.println("JSON responseJsonPath didn't retrieve any values.");
        }
        return result;
    }

    /**
     * @param response JSON response from the API.
     * @param jsonPath JSON Path to get the list required
     * @return Returns the list as hash map
     */
    public static List<HashMap> getJSONResponseAsList(Response response, String jsonPath) {
        List<HashMap> result = null;
        try {
            result = response.jsonPath().getList(jsonPath);
        } catch (ClassCastException e) {
            System.out.println("Incorrect JSON Path: [ " + jsonPath + " ]");
        } catch (JsonPathException e) {
            System.out.println("Failed to parse the JSON Path: [ " + jsonPath + " ]");
        }
        if (result != null) {
            System.out.println("Result is: [" + result.toString() + "]");
            for (int i = 0; i < result.size(); i++) {
                System.out.println("Another result: " + result.get(i));
            }
        } else {
            System.out.println("JSON path didn't retrieve any values.");
        }
        return result;
    }

    /**
     * @param response JSON response from the API.
     * @param jsonPath JSON Path to get the list required
     * @return Returns  map
     */
    public static Map getJSONResponseAsHashMap(Response response, String jsonPath) {
        Map result = null;
        try {
            result = response.jsonPath().getMap(jsonPath);
        } catch (ClassCastException e) {
            System.out.println("Incorrect JSON Path: [ " + jsonPath + " ]");
        } catch (JsonPathException e) {
            System.out.println("Failed to parse the JSON Path: [ " + jsonPath + " ]");
        }
        if (result != null) {
            System.out.println("Result is: [" + result.toString() + "]");
            for (int i = 0; i < result.size(); i++) {
                System.out.println("Another result: " + result.get(i));
            }
        } else {
            System.out.println("JSON path didn't retrieve any values.");
        }
        return result;
    }

    /**
     * @param hashMap Hash map to work on.
     * @param key     Key used to get value from the hash map
     * @return Returns the value retrieved by the key
     */
    public static String getValueFromHashMapUsingKey(HashMap<String, String> hashMap, String key) {
        String searchPool = "";
        if (hashMap.get(key) != null) {
            searchPool = String.valueOf(hashMap.get(key));
        } else {
            searchPool = hashMap.get(key);
        }
        if (searchPool != null) {
            return searchPool;
        } else {
            return null;
        }
    }

    /**
     * @param hashMap Hash map to work on.
     * @param key1    First key used to get another hash map
     * @param key2    Second key used to get the value from the second hash map
     * @return Returns the value retrieved by the key
     */
    public static String getValueFromHashMapOfHashMapUsingKey(HashMap<String, String> hashMap, String key1, String key2) {
        HashMap map = new HashMap();
        String searchPool = "";
        if (hashMap.get(key1) != null) {
            Object object = hashMap.get(key1);
            map = (HashMap) object;
            searchPool = String.valueOf(map.get(key2));
        }
        if (searchPool != null) {
            return searchPool;
        } else {
            return null;
        }
    }

    /**
     * @param filename Of jsonFile which be read
     * @return content of json file which be read
     */
    public static String JsonFileReader(String filename) {
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(new FileReader(currentDirectory + filename));
        } catch (IOException | ParseException e) {
            System.out.println("Failed to parse the JSON file.");
            e.printStackTrace();
        }
        JSONArray jsonArray = (JSONArray) obj;
        return jsonArray.toString();
    }

    /**
     * @param response for Actual Response
     * @return String of Response after arrangement
     */
    public static String ResponseTypeToStringTypeToRearrangeByJsonArray(Response response) {
        String responseToString = response.asString();
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(new StringReader(responseToString));
        } catch (IOException | ParseException e) {
            System.out.println("Failed to parse the JSON file.");
            e.printStackTrace();
        }
        JSONArray jsonObject = (JSONArray) obj;
        return jsonObject.toString();
    }

    public static JSONObject setChangeValuesOfContactData(JSONObject note, String fieldToBeChanged, String value){

        JSONObject  actualContactDetailsObject = (JSONObject) note.get("newContactDetails");
        actualContactDetailsObject.put(fieldToBeChanged,value);
        note.put("newContactDetails",actualContactDetailsObject);
        return  note;
    }
    public static void iterateOverJsonList(JSONObject jsonObject){
       try {
           jsonObject.keySet().forEach(keyStr ->
        {   Object keyvalue = jsonObject.get(keyStr);
            System.out.println("key: "+ keyStr + " value: " + keyvalue);

        });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

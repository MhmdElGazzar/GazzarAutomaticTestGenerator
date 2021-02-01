package ViewFormGui;

import com.google.gson.JsonObject;
import org.json.simple.JSONObject;

public class CodeGenerator {
    CodeWriter codeWriter;
    public void generateLayout(String pageName) {
        pageName=pageName+"Page";
        codeWriter =new CodeWriter(pageName+".java");
        codeWriter.generateLayout(pageName);
       
    }
    public void initiate(String key) {
         codeWriter.initiateText(key);
    }
    public void createLocators(JSONObject locators) {

        locators.keySet().forEach(keyStr ->
        {   String locatorName = keyStr.toString();
            String locatorValue = locators.get(keyStr).toString();
            codeWriter.generateLocatorDefinition(locatorName,locatorValue);
        });
    }
    public void createTextArrays(JSONObject textValues) {

        textValues.keySet().forEach(keyStr ->
        {   String element = keyStr.toString();
            String elementEnglish =( (JSONObject)textValues.get(keyStr)).get("English").toString();
            String elementDeutsch =( (JSONObject)textValues.get(keyStr)).get("Deutsch").toString();
            codeWriter.generateTextDefinition(element,elementEnglish,elementDeutsch);
        });
    }
    public void createMethod(JSONObject elements, String methodType) {
        elements.keySet().forEach(keyStr ->
        {
            String element = elements.get(keyStr).toString();
            codeWriter.createActionMethod(methodType,element);
        });}
    public void createActions(JSONObject elements) {

            elements.keySet().forEach(keyStr ->
            {
                String element = elements.get(keyStr).toString();

                codeWriter.createActionMethod(element);
            });
    }


    public void produce() {
        codeWriter.closeFile();
        System.out.println("File closed");

    }
}

package ViewFormGui;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class TestGenerator {
    private JSONObject pageData;
    private JSONObject pageLocators;
    private JSONObject pageText;
    private JSONObject preconditions;
    private JSONObject existanceAssertions;
    private JSONObject languageAssertions;
    FeatureWriter featureWriter;
    public TestGenerator(String fileName) {
        pageData = JsonTestDataReader.parseJson(fileName);
        pageLocators = (JSONObject) pageData.get("Locators");
        pageText = (JSONObject) pageData.get("Text");
        preconditions = (JSONObject) pageData.get("Preconditions");
        existanceAssertions = (JSONObject) pageData.get("ExistanceAssertions");
        languageAssertions = (JSONObject) pageData.get("LanguageAssertions");

    }
    public void generateTestLayout(String featureName, String tags)
    {
        featureWriter=new FeatureWriter(featureName+".feature");
        featureWriter.setTags(tags);
        featureWriter.setFeatureTitle(featureName);
        featureWriter.setScenarioTitle(featureName);
    }

    public void performActions() {

        preconditions.keySet().forEach(keyStr ->
        {
            JSONObject action = (JSONObject) preconditions.get(keyStr);
            String action_type = action.get("action").toString();
            String action_value1 = action.get("value1").toString();
            if (action.containsKey("value2")) {
            String action_value2=action.get("value2").toString();
            featureWriter.createSteps(action_type,action_value1,action_value2);
            }
            else
            {
                featureWriter.createSteps(action_type,action_value1);
            }
        });
    }
    public void performExistanceAssertions() {

        existanceAssertions.keySet().forEach(keyStr ->
        {
            String attr  = existanceAssertions.get(keyStr).toString();
                featureWriter.createSteps("see",attr);
                  });
    }
    public void performLanguageAssertions() {
        featureWriter.createSteps("language","English");
        languageAssertions.keySet().forEach(keyStr ->
        {
            String attr  = languageAssertions.get(keyStr).toString();
            featureWriter.createSteps("text",attr);
        });
        featureWriter.createSteps("language","Deutsch");
        languageAssertions.keySet().forEach(keyStr ->
        {
            String attr  = languageAssertions.get(keyStr).toString();
            featureWriter.createSteps("text",attr);
        });
    }
    public void produceFeature()
    {
        featureWriter.closeFile();
        System.out.println("File closed");

    }

}

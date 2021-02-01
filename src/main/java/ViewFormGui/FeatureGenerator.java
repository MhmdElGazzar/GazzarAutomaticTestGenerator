package ViewFormGui;

import com.google.gson.JsonObject;
import org.json.simple.JSONObject;

public class FeatureGenerator {
    FeatureWriter featureWriter;
    public void generateLayout(String featureName, String tags) {
        featureWriter=new FeatureWriter(featureName+".feature");
        featureWriter.setTags(tags);
        featureWriter.setFeatureTitle(featureName);
        featureWriter.setScenarioTitle(featureName);
    }
    public void performActions(JSONObject preconditions) {

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
    public void performExistanceAssertions(JSONObject existanceAssertions) {

        existanceAssertions.keySet().forEach(keyStr ->
        {
            String attr  = existanceAssertions.get(keyStr).toString();
            featureWriter.createSteps("see",attr);
        });
    }
    public void performLanguageAssertions(JSONObject languageAssertions) {
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
    public void produce() {
        featureWriter.closeFile();
        System.out.println("File closed");

    }
}

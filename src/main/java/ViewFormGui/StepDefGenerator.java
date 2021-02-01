package ViewFormGui;

import com.google.gson.JsonObject;
import org.json.simple.JSONObject;

public class StepDefGenerator {
    StepDefWriter stepDefWriter;
    public void createExistanceStepDefinitions(JSONObject existanceAssertions) {

        existanceAssertions.keySet().forEach(keyStr ->
        {
            String attr = existanceAssertions.get(keyStr).toString();
            stepDefWriter.defineExistanceAssertionStepDef(attr);
        });
    }
    public void generateLayout(String featureName)
    {
        stepDefWriter=new StepDefWriter(featureName+"_StepDef.java");
        stepDefWriter.setStepDefHeader(featureName);

    }
    public void createLanguageStepDef(JSONObject languageAssertions) {

        languageAssertions.keySet().forEach(keyStr ->
        {
            String attr = languageAssertions.get(keyStr).toString();
            stepDefWriter.defineLanguageAssertionStepDef(attr);
        });

    }
    public void produce()
    {
        stepDefWriter.setStepDefFooter();
        stepDefWriter.closeFile();
    }
}
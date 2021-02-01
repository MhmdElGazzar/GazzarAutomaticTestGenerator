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



    public TestGenerator(String fileName) {
        pageData = JsonTestDataReader.parseJson(fileName);
        pageLocators = (JSONObject) pageData.get("Locators");
        pageText = (JSONObject) pageData.get("Text");
        preconditions = (JSONObject) pageData.get("Preconditions");
        existanceAssertions = (JSONObject) pageData.get("ExistanceAssertions");
        languageAssertions = (JSONObject) pageData.get("LanguageAssertions");

    }

    public void generateStepDefinition(String featureTitle)
    { StepDefGenerator stepDefGenerator= new StepDefGenerator();
        stepDefGenerator.generateLayout(featureTitle);
        stepDefGenerator.createExistanceStepDefinitions(existanceAssertions);
        stepDefGenerator.createLanguageStepDef(languageAssertions);
        stepDefGenerator.produce();
    }
    public void generateFeatureFile(String featureTitle,String featureTags)
    { FeatureGenerator featureGenerator= new FeatureGenerator();
        featureGenerator.generateLayout(featureTitle,featureTags);
        featureGenerator.performActions(preconditions);
        featureGenerator.performExistanceAssertions(existanceAssertions);
        featureGenerator.performLanguageAssertions(languageAssertions);
        featureGenerator.produce();

    }
    public void generateCode(String pageName)
    { CodeGenerator codeGenerator= new CodeGenerator();
        codeGenerator.generateLayout(pageName);
        codeGenerator.initiate("Locators");
        codeGenerator.createLocators(pageLocators);
        codeGenerator.initiate("TextDefinition");
        codeGenerator.createTextArrays(pageText);
        codeGenerator.initiate("Assertions");
        codeGenerator.createMethod(existanceAssertions,"ExistanceAssertion");
        codeGenerator.initiate("Text Assertions");
        codeGenerator.createMethod(languageAssertions,"LanguageAssertion");
        codeGenerator.initiate("Actions");
        codeGenerator.createActions(existanceAssertions);
        codeGenerator.produce();
    }

}

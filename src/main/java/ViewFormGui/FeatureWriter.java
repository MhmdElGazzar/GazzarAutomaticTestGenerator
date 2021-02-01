package ViewFormGui;

import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;

public class FeatureWriter {
     FileWriter myWriter;
    public  FeatureWriter(String fileName)  {
        try {
            myWriter = new FileWriter(fileName);
        }
        catch (IOException e)
        {
            System.out.println("Can't Create File");
        }
    }
    public void addText(String text,String errorPlace) {
        try {
            myWriter.write(text);
            System.out.println(text+"is written");
        } catch (IOException e) {
            System.out.println("An error occurred in "+errorPlace+".");
            e.printStackTrace();
        }
    }
    public void setTags(String tags) {addText(tags,"Tags writing");
    }
    public void setFeatureTitle(String featureName) { addText("Feature:"+featureName+"\n","setFeatureTitle"); }
    public void setScenarioTitle(String scenarioTitle)
    {addText("Scenario: Testing view form for "+scenarioTitle+"\n","writing scenario title"); }

    public void closeFile() {
      try{
          myWriter.close();
    }
        catch (IOException e)
    {
        System.out.println("Can't close file");
    }

    }
    public void createSteps (String key, String value) { String step;
        switch (key)
        { case "open":
            step= "Given I open "+ value+" Portal\n";
            break;
            case "click":
                step= "When I click on "+ value+"\n";
                break;
            case "see":
                step= "Then I should see "+ value+"\n";
                break;
            case "language":
                step= "When the language is \""+ value+"\"\n";
                break;
            case "onPage":
                step= "And I am on \""+ value+"\" page\n";
                break;
            case "text":
                step= "Then I should see "+ value+" has the translated text\n";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + key);
        }
        addText("\t"+step,"Step writing");

    }
    public void createSteps (String key, String value1,String value2) {
        String step= "When I type \""+value2+"\" in "+value1+" TextBox\n";
        addText("\t"+step,"Step writing");

    }
}

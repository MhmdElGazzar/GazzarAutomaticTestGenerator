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
    public void addLine(String line)
    {
        try {
            myWriter.write(line);
            System.out.println(line+"is written");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void setTags(String line)
    {
        try {
            myWriter.write("@FERegression   "+line+"\n");
            System.out.println(line+"is written");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void setFeatureTitle(String featureName)
    {
        try {
            myWriter.write("Feature:"+featureName+"\n");

        } catch (IOException e) {
            System.out.println("An error occurred in writing title.");
            e.printStackTrace();
        }
    }
    public void setScenarioTitle(String scenarioTitle)
    {
        try {
            myWriter.write("Scenario: Testing view form for "+scenarioTitle+"\n");
        } catch (IOException e) {
            System.out.println("An error occurred in writing scenario title.");
            e.printStackTrace();
        }
    }

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
                step= "When I click on \""+ value+"\"\n";
                break;
            case "see":
                step= "Then I \"should\" see \""+ value+"\"\n";
                break;
            case "language":
                step= "When the language is \""+ value+"\"\n";
                break;
            case "onPage":
                step= "And I am on \""+ value+"\" page\n";
                break;
            case "text":
                step= "Then I should see \""+ value+"\" has the translated text\n";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + key);
        }
     try{
        myWriter.write("\t"+step);
    }
        catch (IOException e)
    {
        System.out.println("Can't Write Step");
    }
    }
    public void createSteps (String key, String value1,String value2) {
        String step= "When I type \""+value2+"\" in \""+value1+"\" TextBox\n";
        try {
            myWriter.write("\t"+step);
        }
        catch (IOException e)
        {
            System.out.println("Can't Write Step");
        }
    }
}

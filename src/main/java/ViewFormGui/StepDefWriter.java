package ViewFormGui;

import java.io.FileWriter;
import java.io.IOException;

public class StepDefWriter {
     FileWriter myWriter;
    public StepDefWriter(String fileName)  {
        try {
            myWriter = new FileWriter(fileName);
        }
        catch (IOException e)
        {
            System.out.println("Can't Create File");
        }
    }
    public void addText(String line,String errorPlace) {
        try {
            myWriter.write(line);
            System.out.println(line+"is written");
        } catch (IOException e) {
            System.out.println("An error occurred in "+errorPlace+".");
            e.printStackTrace();
        }
    }
    public void setStepDefHeader(String featureName) {
         String header= "package stepDefinition.fe;\n" +
                 "import io.cucumber.java.en.Given;\n" +
                 "import io.cucumber.java.en.Then;\n" +
                 "import io.cucumber.java.en.When;\n" +
                 "import pages.fe.CommonPage;\n" +
                 "import pages.fe.LoginPage;\n" +
                 "\n" +
                 "public class "+featureName+" extends LoginPage\n" +
                 "{";

       addText(header,"StepDefinition header writing");
    }
    public void defineExistanceAssertionStepDef(String element) { String stepDef= "@Then(\"I should see "+element+"\")\n" +
            "    public void i_should_see_"+element+"() {\n" +
            "        assert"+element+"();\n" +
            "    }";
      addText(stepDef,"StepDef exist. assert writing");
    }
    public void defineLanguageAssertionStepDef(String element) { String stepDef= " @Then(\"I should see "+element+" has the translated text\")\n" +
            "    public void i_should_see_"+element+"_has_the_translated_text() {\n" +
            "        assert"+element+"Text();\n" +
            "    }";
        addText(stepDef,"StepDef lang. assert writing");
    }
    public void setStepDefFooter() {
        String footer= "}";
        addText(footer,"StepDefinition footer writing");
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
}

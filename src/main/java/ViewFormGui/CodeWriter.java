package ViewFormGui;

import org.openqa.selenium.By;

import java.io.FileWriter;
import java.io.IOException;

public class CodeWriter {
     FileWriter myWriter;
    public CodeWriter(String fileName)  {
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
            myWriter.write(text+"\n");
            System.out.println(text+"is written");
        } catch (IOException e) {
            System.out.println("An error occurred in "+errorPlace+".");
            e.printStackTrace();
        }
    }
    public void generateLayout(String pageName)
    { String layout="package pages.fe;\n" +
            "\n" +
            "import org.openqa.selenium.Keys;\n" +
            "import utilities.assertions.Assertions_FE;\n" +
            "import utilities.TestBase;\n" +
            "import utilities.actions.ElementActions;\n" +
            "import org.openqa.selenium.By;\n" +
            "\n" +
            "public class "+pageName+" extends TestBase {\n" ;
    addText(layout,"code layout writing");
    }
    public void generateLocatorDefinition(String loctorName,String locatorValue)
    { String locatorDefinition= " By "+loctorName+" = By.id(\""+locatorValue+"\");";
            addText(locatorDefinition,"locator definition writing");
    }
    public void initiateText(String key)
    {  String initiateText;
        switch (key)
        {   case "Locators":
            initiateText= "//=============================locators==================================";
            break;
            case "TextDefinition":
            initiateText= "//=============================Strings==================================";
            break;
            case "Actions":
                initiateText= "//=============================Actions==================================";
                break;
            case "Assertions":
                initiateText= "//=============================Assertions==================================";
                break;
            case "Text Assertions":
                initiateText= "//=============================Text Assertions==================================";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + key);
        }
           addText(initiateText,"initiateText writing");
    }
    public void generateTextDefinition(String elementString,String elementEnglishText,String elementDeutschText)
    { String textDefinition= "String "+elementString+"Text[] =\n" +
            "            {\""+elementEnglishText+"\"\n" +
            "            , \""+elementDeutschText+"\"};";
        addText(textDefinition,"text definition writing");
    }
    public void createActionMethod (String key, String element) {
        String step;
        switch (key)
        {   case "ExistanceAssertion":
                step= " public void assert"+element+"()\n" +
                        "    {\n" +
                        "        Assertions_FE.assertElementExist("+element+");\n" +
                        "    }";
                break;
            case "LanguageAssertion":
                step= "public void assert"+element+"Text() {\n" +
                        "        Assertions_FE.assertElementText("+element+","+element+"Text[languageIndex]);\n" +
                        "    }";
                break;
            case "click":
                step= " public void click"+element+"() {\n" +
                        "        ElementActions.getElement("+element+").click();\n" +
                        "    }\n" +
                        " ";
                break;
            case "typeIn":
                step= " public void typeIn"+element+"(String input) {\n" +
                        "        try { " +
                        "ElementActions.getElement("+element+").sendKeys(input+\"\\t\");\n" +
                        "        } " +
                        "catch (Exception e) " +
                        "{ e.printStackTrace(); } }\n";
                break;
            case "clear":
                step= "   public void clear"+element+"() {\n" +
                        "        ElementActions.getElement("+element+").sendKeys(Keys.chord(Keys.CONTROL, \"a\", Keys.DELETE));\n" +
                        "    }";
                break;
            default: return;

        }
        addText("\t"+step,"Step writing");

    }
    public void createActionMethod ( String element) {
        String step;
        if (element.contains("Button"))
        {
            createActionMethod("click",element);
        }
        else if (element.contains("TextBox"))
        {
            createActionMethod("typeIn",element);
            createActionMethod("clear",element);
        }
        else {}

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

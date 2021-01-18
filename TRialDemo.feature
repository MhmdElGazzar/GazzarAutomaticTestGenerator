@FERegression   @THOR-48
Feature:TRialDemo
Scenario: Testing view form for TRialDemo
	Given I open Easy Ticket Portal
	When I type "12345" in "CustomerNumber" TextBox
	When I type "12345" in "AccountNumber" TextBox
	When I click on "SignInButton"
	When I click on "CreateIncidentButton"
	Then I "should" see "DeutschRadioButton"
	Then I "should" see "EnglishRadioButton"
	Then I "should" see "ContactDataSaveAndContinueButton"
	Then I "should" see "ProblemClassificationTitle"
	Then I "should" see "ProblemDescriptionTitle"
	Then I "should" see "ConfiramtionTitle"
	Then I "should" see "LocationLabel"
	Then I "should" see "LocationValue"
	Then I "should" see "ContactDataTitle"
	Then I "should" see "SalutaionDropDownList"
	Then I "should" see "FirstNameTextBox"
	Then I "should" see "LastNameTextBox"
	Then I "should" see "LandLineTextBox"
	Then I "should" see "smsRadioButton"
	Then I "should" see "EmailRadioButton"
	Then I "should" see "MobileTextBox"
	Then I "should" see "ToDashBoardLabel"
	Then I "should" see "ToDashBoardButton"
	Then I "should" see "CreateIncidentLabel"
	Then I "should" see "YourReferenceTextBox"
	Then I "should" see "YourReferenceHelpText"
	Then I "should" see "CustomerNameLabel"
	Then I "should" see "CustomerNameValue"
	Then I "should" see "ProductNameLabel"
	Then I "should" see "ProductNameValue"
	Then I "should" see "EmailTextBox"
	Then I "should" see "LanguageLabel"
	When the language is "English"
	Then I should see "DeutschRadioButton" has the translated text
	Then I should see "EnglishRadioButton" has the translated text
	Then I should see "ContactDataSaveAndContinueButton" has the translated text
	Then I should see "ProblemClassificationTitle" has the translated text
	Then I should see "ProblemDescriptionTitle" has the translated text
	Then I should see "ConfiramtionTitle" has the translated text
	Then I should see "ToDashBoardLabel" has the translated text
	Then I should see "CreateIncidentLabel" has the translated text
	Then I should see "YourReferenceHelpText" has the translated text
	Then I should see "CustomerNameLabel" has the translated text
	Then I should see "ProductNameLabel" has the translated text
	Then I should see "LocationLabel" has the translated text
	Then I should see "ContactDataTitle" has the translated text
	Then I should see "smsRadioButton" has the translated text
	Then I should see "EmailRadioButton" has the translated text
	Then I should see "LanguageLabel" has the translated text
	When the language is "Deutsch"
	Then I should see "DeutschRadioButton" has the translated text
	Then I should see "EnglishRadioButton" has the translated text
	Then I should see "ContactDataSaveAndContinueButton" has the translated text
	Then I should see "ProblemClassificationTitle" has the translated text
	Then I should see "ProblemDescriptionTitle" has the translated text
	Then I should see "ConfiramtionTitle" has the translated text
	Then I should see "ToDashBoardLabel" has the translated text
	Then I should see "CreateIncidentLabel" has the translated text
	Then I should see "YourReferenceHelpText" has the translated text
	Then I should see "CustomerNameLabel" has the translated text
	Then I should see "ProductNameLabel" has the translated text
	Then I should see "LocationLabel" has the translated text
	Then I should see "ContactDataTitle" has the translated text
	Then I should see "smsRadioButton" has the translated text
	Then I should see "EmailRadioButton" has the translated text
	Then I should see "LanguageLabel" has the translated text

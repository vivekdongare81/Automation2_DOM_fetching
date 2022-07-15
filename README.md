# Validating URL \& fetching its DOM 

A task consist validating URL and fetching DOM of page to be used in checking for phishing websites. It preforms headless Automation using Selenium with Firefox.

## Prerequisite 

- Java SE 8 (JDK 1.8)
- Selenium Jars for Java 
- WebDriver of Firefox (GekoDriver)


|              | Required Download Link                                                                  |
| ----------------- | ------------------------------------------------------------------ |
| GekoDriver ( Firefox ) | [https://github.com/mozilla/geckodriver/releases](https://github.com/mozilla/geckodriver/releases)|
| Selenium Jars ( Java ) | [https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.3.0/selenium-java-4.3.0.zip](https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.3.0/selenium-java-4.3.0.zip)|

 * Move GekoDriver in any specific folder and add this folder into PATH of Environmental Variables. <br />
-- Search for 'edit environmental variable' > Environmental variable > System variable > PATH <br />
-- Add a new folder's path in PATH.  <br />


## Installation to Run Locally


 -  Step 1 - Clone the project and navigate to the project directory
  
```bash
git init 
git clone < repo link >

```

  - step 2 - Set up classpath of Selenium jar files 

  -  Step 3 - Input URL and run Script.java 

  -  Step 4 - open domContent.html (consists DOM of URL)



## Documentation

- [Selenium](https://www.selenium.dev/documentation)

#AXA Selenium Test Prototype


### Page object location
```src > test > java > axa > pages```

### Test location
```src > test > java > axa > tests```

AutoversicherungsTest.java contains the test 

### Data adapter location
``src > test > java > axa > utils``

ExcelAdapter.java contains the TestNG Adapter for an Excel dataprovider  
 
### Run tests from command line 
You need to be in the main project directory, where the pom.xml fike is located:

``mvn -Dtest=AutoversicherungsTest clean test``

**Generate report:**

``allure serve target/surefire-reports``

Note: currently multiple tests with dataprovider show up as one test in allure

**Videos:**

Videos are only available when running on Selenium Box but not locally. Selenium Box will be available for AXA around the end of March 2018. 


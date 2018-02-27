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

``mvn -Dtests=AutoversicherungsTest clean test``

Generate report:

``allure serve target/surefire-reports``
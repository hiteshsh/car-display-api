####Assumptions
* Data in db is not going to change.
* Dependencies are resolved before running tests(no compilaton error)

####Follow the below steps for executing tests
* Add the Base url in the test.properties file before running tests
* I'm using lombok as a library(https://projectlombok.org/) for getter and setter methods.
So ensure you install the lombok plugin in your editor. If you are using intellij, don't 
forget to enable Annotation processing in "Annotation Processor" settings(refer lombok.png in repository).
* For running all the tests execute below command:
```
./gradlew clean test
```
### Report can be found at
 ```<project root directory>/build/reports/tests/test/index.html```
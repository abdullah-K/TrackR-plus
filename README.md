# TrackR+ - Tests

## Running JUnit tests
to compile:
`javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar:lib/java-json.jar *.java`

to execute:
`java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar:lib/java-json.jar org.junit.runner.JUnitCore UserTest`
`java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar:lib/java-json.jar org.junit.runner.JUnitCore UserAttributesTest`

## Running JSON tester
JSON Tester populates the user's JSON file with "fake" data in order to demo the features of TrackR+

to compile: 
`javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar:lib/java-json.jar *.java`

*make sure a `user.json` file does not already exist before running JSONTester*

to execute:
`java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar:lib/java-json.jar JSONTester`


**important**: replace the `:` with `;` in all the commands above if running on Windows

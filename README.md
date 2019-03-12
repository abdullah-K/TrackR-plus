# TrackR+

TrackR+ is a personal expense tracker

The text version of this program contains a home navigation with 3 menus,
each allowing the user to interact with the app and keep track of incoming and
outgoing flow of money into their personal account.

The GUI based version, allows the user to navigate between 3 menus, including a Today tab which has a detailed pie-chart that describes the users spending, an Expenses tab which allows the user to log different expenses according to several categories, and a goals tab which gives the user the ability to set a new savings goal.

## Running text-based version
to compile:
`javac -cp .:lib/java-json.jar *.java`

to execute:
`java -cp .:lib/java-json.jar Home`

## Running GUI-based version 
to compile: 
`javac -cp .:lib/java-json.jar:lib/java-json.jar *.java`

to execute:
`java LoginGUI`

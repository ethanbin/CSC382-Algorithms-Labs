To create an executable jar, open console in the directory of this README, and enter the following:

javac lab1/*.java
jar cfe project.jar lab1.SortTimeCounting lab1/*.class

To run it, enter "java -jar project.jar"
This will produce a CVS file named "results"
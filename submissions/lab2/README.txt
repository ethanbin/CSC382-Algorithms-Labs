To create an executable jar, open console in the directory of this README, and enter the following:

javac lab2/*.java Main/*.java Sorters/*.java
jar cfe project.jar Main.Main Main/*.class lab2/*.class Sorters/*.class

To run it, enter "java -jar project.jar"
This will produce a CVS file named "results"
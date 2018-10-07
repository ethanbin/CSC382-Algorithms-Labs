This will allow you to compile the java files into an executable jar:
1) compile the .java files by typing "javac *.java" into terminal without quotes
2) type "jar -cfe project.jar *.class SortTimeCounting.class" in terminal without quotes to create the jar file.

To run it, enter "java -jar CSC382.jar" followed by a "t" or a "s", all without quotes, depending on whether you want to calculate time or steps.
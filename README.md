# Airline-carrier-delay-HADOOP

## Big Data Systems 2017, Hadoop Assignment

In this assignment, you will investigate the Airline On-Time Performance Data. 

Check the web site for details.

https://transtats.bts.gov/Tables.asp?DB_ID=120&DB_Name=Airline%20On-Time%20Performance%20Data&DB_Short_Name=On-Time

In particular, you will download the file 78462560_T_ONTIME.csv.zip from 

here https://drive.google.com/file/d/0B26_NyylDb8qalBUSkFyaDRZSUU/view, 

here https://www.dropbox.com/s/3emmnyfjah4mcn6/78462560_T_ONTIME.csv.zip?dl=0, 

or here https://transtats.bts.gov/Tables.asp?DB_ID=120&DB_Name=Airline%20On-Time%20Performance%20Data&DB_Short_Name=On-Time

The file contains flight details for 2016.

## Question

Write a Hadoop program that reads the flights data and outputs the carriers in increasing misery. It should output the carriers ordered by the percentage of flights that are delayed and the average delay per delayed flight. That is, your output should be of the form:

0.0     CARRIER NaN
0.061180637851775646    HA 48.57598978288633
0.09541403429602888     AS 53.01253325450784
0.1386383685217817      DL 67.54905884560065
0.14480148795328857     OO 75.02136995669022
0.1654860587792012      EV 77.3608034263772
0.1684426924149394      AA 65.12225396001038
0.18684125070862848     UA 68.20114688583183
0.18952490449761591     WN 48.980623444333006
0.20954557949103744     VX 58.28148301574151
0.2143480409163066      F9 73.77267153857473
0.2267237823717972      NK 64.78725305588357
0.23593759403553613     B6 63.85789094619332

which means that the best carrier in 2016 was HA (Hawaiian Airlines) with a 6% probability of delay and an average delay of 48.57 minutes; then the second best carrier was AS (Alaska Airlines), with a 9.5% probability of delay, the third best carrier was DL (Delta Airlines) and so on.

When you unzip the data file, you will see that the first line is a header explaining the various fields. A flight is considered delayed if the field DEP_DEL15 is equal to 1.00 (the flight is delayed more than 15 minutes). The actual delay is given by the field DEP_DELAY. The carrier is given by the CARRIER field.

## Setup Instructions

As you will notice, the file is in CSV format (CSV = Comma Separated Values). If you try to split the lines on the commas, you will fail, because a field may contain commas inside it. CSV files are handled with special libraries. In Java, an appropriate library is Apache Commons CSV. You will have to use that library in your program. The most straightforward way to do that is as follows.
Download and install maven. Once you have installed it properly, you should be able to run it my entering mvn in the command line.
Assume that you are working in directory called hadoop-assignment.

In the hadoop-assignment directory create a file pom.xml with the following contents:

<project>

  <modelVersion>4.0.0</modelVersion>
  <groupId>msc.ba.hadoop</groupId>
  <artifactId>flights</artifactId>
  <packaging>jar</packaging>
  <version>1.0-SNAPSHOT</version>

  <properties>
    <hadoop.version>2.7.3</hadoop.version>
  </properties>

  <dependencies>
    <dependency>
      <groupId>org.apache.commons</groupId>
      <artifactId>commons-csv</artifactId>
      <version>1.4</version>
    </dependency>    
    <dependency>
      <groupId>org.apache.hadoop</groupId>
      <artifactId>hadoop-common</artifactId>
      <version>${hadoop.version}</version>
    </dependency>
    <dependency>
      <groupId>org.apache.hadoop</groupId>
      <artifactId>hadoop-mapreduce-client-core</artifactId>
      <version>${hadoop.version}</version>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <version>3.0.0</version>
        <executions>
          <execution>
            <phase>package</phase>
            <goals>
              <goal>shade</goal>
            </goals>
            <configuration>
              <artifactSet>
                <includes>
                  <include>org.apache.commons:commons-csv</include>
                </includes>
              </artifactSet>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>

</project>

Create a directory hadoop-assignment/src/main/java and write your Java programs inside that directory (in MS-Windows this would be hadoop-assignment\src\main\java).

To compile your program, enter mvn compile from the command line at the hadoop-assignment directory. The first time you do it, it will take a long time because it will download a lot of files.

To make a self-contained Jar file, enter mvn package. This will create a file flights-1.0-SNAPSHOT.jar inside a directory called target that will be created automatically.

The Jar file contains the program that you can run using Hadoop.

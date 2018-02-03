This is a sample Java / Maven / Spring Boot (version 1.5.10) application where user can upload a file with few meta-data fields.

How to Run:

This application is packaged as a war which has Tomcat 8 embedded.
You run it using the java -jar command.

Below are the steps:
1.Clone this repository
2.Make sure you are using JDK 1.8 and Maven 3.x
3.You can build the project and run the tests by running mvn clean package
4.mvn spring-boot:run

About the Service:

The service is just a file upload with meta-data REST service. It uses an in-memory database (H2) to store the data 
and it also displays all the files uploaded along with the meta data in tabular format.

This application uses default serverport of 8080. if you wish to change, go to application.properties file
add "server.port = 8090" without quotes.

Example :

FileName	FileType	Description
twc.pdf		pdf			test
Sid.doc		doc			mydoc

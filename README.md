
<b>Steps to run the application:
1.	Clone the project Rabobank (Spring Boot Application).
https://github.com/laxman-jadi/AssignmentCSP.git
2.	Run CSPSpringBootApplication.java file 
3.	This Web service application have one active service to process Csv/Xml files. please find service url below,
http://localhost:8081/csp/processStatment
4.	Upload input csv/xml file in the service using postman client.
5.	The input file will be validated based on two conduction mentioned in the problem statment.(validation condition mentioned in expected output section)
o	Duplicate Transaction key check,
o	End balance calculation check. (endbalance = startbalance – mutation)
6.	Finally invalid records will be getting as webservice response with status code.
 


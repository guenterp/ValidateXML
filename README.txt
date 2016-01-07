
example how to validate a xml file against a schema

tests were done with NetBeans 8.1 and JDK 1.8

xml files ending with <_2.xml> (EmployeeResponse_2.xml, helloFundsXML_2.xml) contain 2 errors
other xml files should validate without error

2 different schema files are used - Employee.xsd, FundsXML3.2.xsd (for details see fundsXML.org)

there are 2 test methods - validateXMLSchema, validateXMLSchemaAllErrors
These methods validate the same files.
The only difference is that method validateXMLSchema diplays the first error only
whereas method validateXMLSchemaAllErrors stores all errors in a list and displays them all.

how to deploy:
get project from Git (git clone) or import zip file
(Netbeans: Team/Git/Clone or File/Import Project/From ZIP) 

how to run:
execute ValidateTest as JUnit class (Netbeans: right click ValidateTest.java, choose "Test File" or CTRL-F6).
MyRetail Restful Service
-----------------------------------------------------------------------------------------------------------------------------------------------

Technology Stack
-----------------------------------------------------------------------------------------------------------------------------------------------

Maven
SpringBoot
RestTemplate
MongoDb
Postgres
Swagger 2

Set Up Instructions(Windows set up for the POC purpose)
-----------------------------------------------------------------------------------------------------------------------------------------------

Download Maven and configure the windows environment variables
https://www.mkyong.com/maven/how-to-install-maven-in-windows/

Download MongoDb for windows and Create the Data Folders to Store our Databases
https://medium.com/@LondonAppBrewery/how-to-download-install-mongodb-on-windows-4ee4b3493514

Download Postgres for Windows and set up the data directory
https://www.guru99.com/download-install-postgresql.html

Download the code from the git repository : https://github.com/mrutunjayachavadi/MyRetail

Navigate to DBScript folder, execute the contents of create.sql file contents in pgadmin console with super user postgres
to create product and current_price tables in postgres.

Navigate to MyRetail Folder in command prompt
execute "mvn spring-boot:run" command to run the application

Please find the Rest documentation at below url :
http://localhost:8080/swagger-ui.html

Also find the "MyRetail.postman_collection.json" for the rest Api documentation.

Unit Test Document with the sample input and output cane be found in file "Unit_Test_Document.docx".

Assumptions made for the use cases mentioned below
-----------------------------------------------------------------------------------------------------------------------------------------------

Use Case :: Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 

I have assumed the Datastore to be RDBMS(Postgres), on matching the product id, product data is fetched from the postgres.

Use Case :: Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from redsky.target.com,
 but let’s just pretend this is an internal resource hosted by myRetail)

 For above use case I have hosted the Example url mentioned in the use case in the myretail rest service to fetch the product name from external 
 service endpoint given
 
 Use Case :: Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response.
 I have used mongodb as NoSQL datastore for above use case, based on the product id, price is fetched from current_price mongoDb Table.
 
 Use Case :: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store
 
 Price information is updated in the rdbms(postgres) current_price table, no updates are done to NO-SQL(MongoDB) data store

Considerations for the Production Environment
-----------------------------------------------------------------------------------------------------------------------------------------------
1. Authentication and Authorization should be added for the restful web service.
2. Data Validation for all the api's should be added.
3. Exception handling  can be improved to include proper response messages for failure/erroneous scenarios.
4. Externalization of property files like application.properties for pointing application to desired datastore.
5. Software Excellence like junit code coverage, fanout, Cyclomatic complexity and compiler warnings should be taken care.
6. Automation of the installation steps can be considered for linux environment to reduce errors and manual intervention.











# AWS Document DB POC

### Reads 

- https://docs.localstack.cloud/user-guide/aws/docdb/
- https://aws.amazon.com/blogs/database/integrate-your-spring-boot-application-with-amazon-documentdb/
- https://whattodevnow.medium.com/using-localstack-with-docker-compose-to-mock-aws-services-bb25a5b01d4b

### What is

- Fully managed, scalable, and highly available document database service
- DocumentDB is designed to be compatible with MongoDB

1. What is a document database?
   - common way of data model in normalized rows and columns
   - On application level usually we represent data into JSON format
   - Document database let you persist data into database using same model that we use in application
   - ![relational-db.png](diagrams/relational-db.png)
   - ![json-document.png](diagrams/json-document.png)
   - Both represent same data model using different structures
   - Pro: flexible schema 

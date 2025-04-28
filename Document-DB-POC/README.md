# AWS Document DB POC

### Reads 

- https://docs.localstack.cloud/user-guide/aws/docdb/
- https://aws.amazon.com/blogs/database/integrate-your-spring-boot-application-with-amazon-documentdb/
- https://whattodevnow.medium.com/using-localstack-with-docker-compose-to-mock-aws-services-bb25a5b01d4b

### What is

- Fully managed, scalable, and highly available document database service
- DocumentDB is designed to be compatible with MongoDB
- O Amazon DocumentDB é compatível com o MongoDB 3.6, 4.0 e 5.0.

1. What is a document database?
   - common way of data model in normalized rows and columns
   - On application level usually we represent data into JSON format
   - Document database let you persist data into database using same model that we use in application
   - ![relational-db.png](diagrams/relational-db.png)
   - ![json-document.png](diagrams/json-document.png)
   - Both represent same data model using different structures
   - Pro: flexible schema 

2. How it works?
   - create a cluster
      - two components
         - cluster volume
            - manage data to instances
            - An Amazon DocumentDB cluster volume is a virtual database storage volume that spans multiple Availability Zones.
               - each AZ has a copy of the data
            - can have 0 to 16 instances
         - instances
            - cluster is 0 or n database instances
            - processing power for the database
            - writes data to, reads data from the cluster storage volume
            - Cluster instances do not need to be of the same instance class, and they can be provisioned and terminated as desired.
            - instances type:
               - primary instance
                  - each AWS DocumentDB cluster has one primary instance
                  - support read and writes
                  - perform all data modifications to cluster volume
               - replica instance
                  - support only read 
                  - each AWS DocumentDB can have up to 15 instances 
                  - enables distribute read workload
                  - May enable Multi AZ Availability

   - Replication
      - Write operation to primary instance, the primary instance executes a durable write to the cluster volume
      - After the primary instance replicates the state of the write (not the data) to each ACTIVE replica
         - Read replicas does NOT participate on the write process
            - downside for read scaling
            - Reads from Amazon DocumentDB replicas are eventually consistent with minimal replica lag—usually less than 100 milliseconds after the primary instance writes the data

            
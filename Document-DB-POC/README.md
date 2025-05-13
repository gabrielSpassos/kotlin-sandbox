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
      - Amazon DocumentDB replicas are also failover targets and are quickly promoted if the primary instance for your Amazon DocumentDB cluster fails.

   - Storage
      - data is stored in cluster volume
         - single, virtual volume that uses SSDs
      - cluster volume
         - consists of six copies of your data
         - data is automatically replicated across multiple Availability Zones (AZ) in single AWS Region
         - Replication helps ensure high durability, less possibility of data loss, also higher availability during failover because data already exist in other AZ, since these copies could continue to serve data to instances of the AWS DocumentDB
      
      - How is billed?
         - charged for the space that you use in an Amazon DocumentDB cluster volume
            - Amazon DocumentDB 4.0, when data is removed (drop collection, index, database) the overall allocated space decreases
            - Amazon DocumentDB 3.6 even if you remove data the volume itseft does not decrease, you only freed up space on the volume
               -  costs are based on the storage "high water mark" (the maximum amount that was allocated for the Amazon DocumentDB cluster at any point in time).
   
   - Reliability 
      - https://docs.aws.amazon.com/documentdb/latest/developerguide/how-it-works.html#how-it-works.reliability
      - designed to be reliable, durable and fault tolerant
      - storage auto-repair
         - Since we have copies of the data in multiple AZ's, this reduce the change of data loss by storage failure.
         - When a segment of cluster volume fails, it uses the data from other volumes that make up the cluster to ensure the the data repaired is correct
            - reducing the need to perform a point-in-time restore to recover from an instance failure.
      - Survivable cache warming
         - The page cache is manages in a separate process from the database so that the page cache can survive independently of the database.
         - If occour an database failure the cache remains in memory
            - the buffer pool is warmed when the database restart
      - Crash Recovery
         - After crash the AWS Document DB will perform crash recovery async in parallel threads
      - Resource governance 
         - when an instance is experiencing high memory pressure, the requests will be throttle. Some operations may be queued to wait for memory pressure to subside. If the memory pressure continues, the queue operations may suffer timeouts.
      
   - Read preference options
      - Amazon DocumentDB does not rely on replicating data to multiple instances to achieve durability.
      - The cluster data is durable with 1 or 15 instances
      - Write durability
         - Amazon DocumentDB uses a unique, distributed, fault-tolerant, self-healing storage system.
         - When writing, the database ensures that all writes are durably recorded on majority of nodes before ack the write to the client.
         - An acknowledged write from an Amazon DocumentDB primary instance is durable, and can't be rolled back
      - Read Isolation
         - reads only return data already durable before the query begins
         - read never return data modified after the query begins
         - no dirty read
         - read consistency 
            - read from primary instance are strongly consistent under normal operation conditions and have read-after-write consistency 
               (is the ability to view changes (read data) right after making those changes (write data))
            - if a failover event happens between the write and the following read
            
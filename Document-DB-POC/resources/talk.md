2. How it works?
    - two components
        - cluster volume
          - manage data to instances
        - instances
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

3. Replication
    - When a write operation occurs on the primary instance, it is first executes a durable write to the cluster volume.
    - Then, the state of that write (not the raw data) is replicated to each active replica.
    - Read replicas does NOT participate on the write process
    - Reads from Amazon DocumentDB replicas are eventually consistent after the primary instance writes the data
      - read replica lag—usually less than 100 milliseconds

4. Storage
   - data is stored in cluster volume
        - single, virtual volume that uses SSDs
   - cluster volume
       - consists of six copies of your data
       - data is automatically replicated across multiple Availability Zones (AZ) in single AWS Region
       - Replication helps ensure high durability, less possibility of data loss, also higher availability during
     failover because data already exist in other AZ, since these copies could continue to serve data to instances of the AWS DocumentDB

5. Amazon DocumentDB elastic cluster sharding
   - Amazon DocumentDB elastic clusters use hash-based sharding to partition data across a distributed storage system.
     - Algorithm: hash function transforms the shard key into hash and uses this hash to partition the data across 
     - Sharding: also known as partitioning, splits large data sets into small data sets across multiple nodes 
     enabling you to scale out your database beyond vertical scaling limits
   - AWS Document DB decouples compute from storage, allowing scale independently of each other
   - Shard definitions
       - Shard
           - A shard provides compute for an elastic cluster.
           - Single writer instance and 0-15 read replicas
           - By default, a shard will have two instances: a writer and a single read replica.
       - Shard Key
           - Required field on JSON document in shared collections that elastic cluster uses to distribute read and write to matching shard
           - A good shard key will evenly partition your data across the underlying shards, giving your workload the best throughput and performance.
       - Sharded collection
           - Collection whose data is distributed across an elastic cluster in data partitions
       - Partition
           - Logical portion of shared data
           - When shared collection is created, the data is organized into partitions inside each shard basead on the shard key

6. Durability 
   - When writing, the database ensures that all writes are durably recorded on majority of nodes before ack the write to the client.
   - Read Isolation
     - reads only return data already durable before the query begins
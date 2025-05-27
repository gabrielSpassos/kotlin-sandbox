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

4. 
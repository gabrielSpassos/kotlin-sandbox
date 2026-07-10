# HTTP Mock Server With Test Containers POC

### Stack

- Java 26
- Kotlin 2.4.0
- Maven
- Spring Boot 4.1.0
- Test Containers
- Test Containers Mock Server
- Mock Server Client Java

### Tests

```bash
[INFO] Results:
[INFO] 
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- jar:3.5.0:jar (default-jar) @ testcontainers-mockserver ---
[INFO] Building jar: /home/passos/Documentos/workspace/kotlin-sandbox/testcontainers-mockserver/target/testcontainers-mockserver-0.0.1-SNAPSHOT.jar
[INFO] 
[INFO] --- spring-boot:4.1.0:repackage (repackage) @ testcontainers-mockserver ---
[INFO] Replacing main artifact /home/passos/Documentos/workspace/kotlin-sandbox/testcontainers-mockserver/target/testcontainers-mockserver-0.0.1-SNAPSHOT.jar with repackaged archive, adding nested dependencies in BOOT-INF/.
[INFO] The original artifact has been renamed to /home/passos/Documentos/workspace/kotlin-sandbox/testcontainers-mockserver/target/testcontainers-mockserver-0.0.1-SNAPSHOT.jar.original
[INFO] 
[INFO] --- install:3.1.4:install (default-install) @ testcontainers-mockserver ---
[INFO] Installing /home/passos/Documentos/workspace/kotlin-sandbox/testcontainers-mockserver/pom.xml to /home/passos/.m2/repository/com/gabrielspassos/testcontainers-mockserver/0.0.1-SNAPSHOT/testcontainers-mockserver-0.0.1-SNAPSHOT.pom
[INFO] Installing /home/passos/Documentos/workspace/kotlin-sandbox/testcontainers-mockserver/target/testcontainers-mockserver-0.0.1-SNAPSHOT.jar to /home/passos/.m2/repository/com/gabrielspassos/testcontainers-mockserver/0.0.1-SNAPSHOT/testcontainers-mockserver-0.0.1-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  34.179 s
[INFO] Finished at: 2026-07-10T08:47:47-03:00
[INFO] ------------------------------------------------------------------------
```
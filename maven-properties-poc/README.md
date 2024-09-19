# Maven Properties Reader POC

1. Add on pom.xml
```maven
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>properties-maven-plugin</artifactId>
    <version>1.2.1</version>
    <executions>
        <execution>
            <phase>generate-resources</phase>
            <goals>
                <goal>write-project-properties</goal>
            </goals>
            <configuration>
                <outputFile>${project.build.outputDirectory}/properties-from-pom.properties</outputFile>
            </configuration>
        </execution>
    </executions>
</plugin>
```

2. Run build + test
```bash
mvn clean install
```
# Kotlin 2.0 POC

### References

- https://kotlinlang.org/docs/whatsnew20.html
- https://kotlinlang.org/docs/whatsnew2020.html

### Enable K2 compiler

- https://kotlinlang.org/docs/k2-compiler-migration-guide.html#support-in-ides
    - Settings > Languages & Frameworks > Kotlin > Enable K2 mode
    ![Screenshot 2024-09-04 at 14 21 34](https://github.com/user-attachments/assets/1c02a0d5-0991-4928-ae04-86fa747bfcef)

### Maven configuration
```maven
<properties>
    <java_version>21</java_version>
    <kotlin.version>2.0.20</kotlin.version>
    <maven.compiler.source>${java_version}</maven.compiler.source>
    <maven.compiler.target>${java_version}</maven.compiler.target>
    <maven.compiler.release>${java_version}</maven.compiler.release>
    <kotlin.compiler.jvmTarget>${java_version}</kotlin.compiler.jvmTarget>
    <kotlin.code.style>official</kotlin.code.style>
</properties>

<build>
    <sourceDirectory>src/main/kotlin</sourceDirectory>
    <testSourceDirectory>src/test/kotlin</testSourceDirectory>
    <plugins>
        <plugin>
            <groupId>org.jetbrains.kotlin</groupId>
            <artifactId>kotlin-maven-plugin</artifactId>
            <version>${kotlin.version}</version>
            <executions>
                <execution>
                    <id>compile</id>
                    <phase>compile</phase>
                    <goals>
                        <goal>compile</goal>
                    </goals>
                </execution>
                <execution>
                    <id>test-compile</id>
                    <phase>test-compile</phase>
                    <goals>
                        <goal>test-compile</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
        <plugin>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>2.22.2</version>
        </plugin>
        <plugin>
            <artifactId>maven-failsafe-plugin</artifactId>
            <version>2.22.2</version>
        </plugin>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>exec-maven-plugin</artifactId>
            <version>1.6.0</version>
            <configuration>
                <mainClass>MainKt</mainClass>
            </configuration>
        </plugin>
    </plugins>
</build>

<dependencies>
    <dependency>
        <groupId>org.jetbrains.kotlin</groupId>
        <artifactId>kotlin-test-junit5</artifactId>
        <version>${kotlin.version}</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.jetbrains.kotlin</groupId>
        <artifactId>kotlin-stdlib</artifactId>
        <version>${kotlin.version}</version>
    </dependency>
</dependencies>
```

### Features

- [x] https://kotlinlang.org/docs/whatsnew20.html#local-variables-and-further-scopes
- [x] https://kotlinlang.org/docs/whatsnew20.html#type-checks-with-logical-or-operator
- [x] https://kotlinlang.org/docs/whatsnew20.html#inline-functions
- [x] https://kotlinlang.org/docs/whatsnew20.html#properties-with-function-types
- [x] https://kotlinlang.org/docs/whatsnew20.html#exception-handling
- [x] https://kotlinlang.org/docs/whatsnew20.html#increment-and-decrement-operators

### Outputs

Kotlin 2.0.0 was released: May 21, 2024

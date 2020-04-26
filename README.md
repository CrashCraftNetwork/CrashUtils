# CrashUtils

## Using Maven Build System
Make sure maven is installed correctly.
Use `mvn clean install` in the root directory.

## Building
To revert back to default build location.
Open `pom.xml` and locate

```
    <build>
        ...
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>2.3.1</version>
                <configuration>
                    <outputDirectory>./Server/start.bat</outputDirectory>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

Delete the plugin to use mavens default build path.

## Git
Please do not commit changes to the pom.xml unless they are needed.
For example don't commit the changing of the outputDirectory.

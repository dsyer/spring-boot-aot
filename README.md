Native image generation works, but requires a patch for https://jira.spring.io/browse/SPR-17198

```
$ mvn clean install
$ CP=`java -jar target/spring-init-experiment-1.0-SNAPSHOT.jar --thin.classpath --thin.profile=graal`
$ native-image -Dorg.springframework.boot.logging.LoggingSystem=org.springframework.boot.logging.java.JavaLoggingSystem -H:Name=target/app -H:IncludeResources='META-INF/spring.factories|org/springframework/boot/logging/.*' -H:ReflectionConfigurationFiles=app.json --report-unsupported-elements-at-runtime -cp $CP com.acme.SampleApplication
$ ./target/app -Dorg.springframework.boot.logging.LoggingSystem=org.springframework.boot.logging.java.JavaLoggingSystem
11:09:17.864 [main] DEBUG org.springframework.boot.context.logging.ClasspathLoggingApplicationListener - Application started with classpath: unknown

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                        

11:09:17.865 [main] INFO com.acme.SampleConfiguration - Initializing
...
11:09:17.866 [main] INFO com.acme.SampleConfiguration - Creating: com.acme.Bar@7f1a6ad164a8
11:09:17.866 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.boot.autoconfigure.internalCachingMetadataReaderFactory'
11:09:17.866 [main] INFO org.springframework.boot.SpringApplication - Started application in 0.008 seconds (JVM running for 0.009)
11:09:17.866 [main] INFO com.acme.SampleConfiguration - Bar: com.acme.Bar@7f1a6ad164a8

```

Notes:

The `LogbackLoggingSystem` doesn't work because of a [Graal bug](https://github.com/oracle/graal/issues/564).

The `thin-graal.properties` includes `dependencies.tomcat=org.springframework.boot:spring-boot-starter-tomcat` because of a [Spring Boot issue](https://github.com/spring-projects/spring-boot/issues/14157), even though this is not a web application. You would have to include it in a Netty webflux app as well to work around the issue.

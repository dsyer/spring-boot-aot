```
$ mvn clean install
$ CP=`java -jar target/spring-init-experiment-1.0-SNAPSHOT.jar --thin.classpath`
$ native-image  -H:Name=target/app -H:ReflectionConfigurationFiles=app.json --report-unsupported-elements-at-runtime -cp $CP com.acme.SampleApplication
Build on Server(pid: 24011, port: 34265)
   classlist:   2,285.89 ms
       (cap):     788.94 ms
       setup:   1,017.87 ms
  (typeflow):   7,109.63 ms
   (objects):   4,600.27 ms
  (features):      60.82 ms
    analysis:  11,988.33 ms
    universe:     725.21 ms
     (parse):   1,695.33 ms
    (inline):   2,775.64 ms
   (compile):   8,811.49 ms
     compile:  13,779.88 ms
       image:     911.70 ms
       write:     320.93 ms
     [total]:  31,066.25 ms
graal-spring$ ./target/app
Exception in thread "main" com.oracle.svm.core.jdk.UnsupportedFeatureError: Unresolved element found 
	at java.lang.Throwable.<init>(Throwable.java:265)
	at java.lang.Error.<init>(Error.java:70)
	at com.oracle.svm.core.jdk.UnsupportedFeatureError.<init>(UnsupportedFeatureError.java:31)
	at com.oracle.svm.core.jdk.Target_com_oracle_svm_core_util_VMError.unsupportedFeature(VMErrorSubstitutions.java:109)
	at com.oracle.svm.core.snippets.SnippetRuntime.unresolved(SnippetRuntime.java:205)
	at org.springframework.boot.SpringApplication.setApplicationContextClass(SpringApplication.java:1176)
	at com.acme.SampleApplication.main(SampleApplication.java:9)
	at com.oracle.svm.core.JavaMainWrapper.run(JavaMainWrapper.java:177)
```

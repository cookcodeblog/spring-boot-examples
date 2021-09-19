[TOC]

# Spring Boot Application Graceful Shutdown

Graceful shutdown with Tomcat requires Tomcat 9.0.33 or later.

Check Tomcat version:
```bash
mvn dependency:tree | grep -i tomcat

# org.apache.tomcat.embed:tomcat-embed-core:jar:9.0.39:compile
```


application.yaml:
```yaml
server:
  shutdown: graceful
```

Terminate the process:
```bash
# or press ctrl + c
ps -ef | grep java | grep xdevops | grep -i application

# Terminate the process to send SIGTERM
# If use `kill -9`, it sends SIGKILL to force kill the process
kill <pid>
```

```text
2021-09-19 20:52:05.833  INFO 70363 --- [extShutdownHook] o.s.b.w.e.tomcat.GracefulShutdown        : Commencing graceful shutdown. Waiting for active requests to complete
2021-09-19 20:52:05.846  INFO 70363 --- [tomcat-shutdown] o.s.b.w.e.tomcat.GracefulShutdown        : Graceful shutdown complete
2021-09-19 20:52:05.902  INFO 70363 --- [extShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2021-09-19 20:52:05.902  INFO 70363 --- [extShutdownHook] .SchemaDropperImpl$DelayedDropActionImpl : HHH000477: Starting delayed evictData of schema as part of SessionFactory shut-down'
2021-09-19 20:52:05.909  INFO 70363 --- [extShutdownHook] o.s.s.concurrent.ThreadPoolTaskExecutor  : Shutting down ExecutorService 'applicationTaskExecutor'
2021-09-19 20:52:06.128  WARN 70363 --- [extShutdownHook] o.s.b.f.support.DisposableBeanAdapter    : Invocation of destroy method failed on bean with name 'inMemoryDatabaseShutdownExecutor': org.h2.jdbc.JdbcSQLNonTransientConnectionException: Database is already closed (to disable automatic closing at VM shutdown, add ";DB_CLOSE_ON_EXIT=FALSE" to the db URL) [90121-200]
2021-09-19 20:52:06.129  INFO 70363 --- [extShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2021-09-19 20:52:06.132  INFO 70363 --- [extShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.

Process finished with exit code 143 (interrupted by signal 15: SIGTERM)
```

References:
- [Shutdown Spring Boot Applications Gracefully](https://medium.com/amitph/shutdown-spring-boot-applications-gracefully-6e4e7a359553)
- <https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.graceful-shutdown>
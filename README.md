## Redis-Cluster
jedis

### docker
[Grokzen/docker-redis-cluster](https://github.com/Grokzen/docker-redis-cluster)を利用

環境変数
```
export REDIS_CLUSTER_IP=127.0.0.1
export REDIS_USE_SENTINEL=false
export REDIS_USE_STANDALONE=false
```
docker-compose up -d

`chmod -x ./gradlew`




### redis timeout
https://stackoverflow.com/questions/44245581/spring-redis-connection-fail-socket-timeout-read-timed-out


http://pppurple.hatenablog.com/entry/2017/11/30/230149


https://ja.ojit.com/so/java/1615442


https://smoothed9.medium.com/introduction-to-spring-data-redis-with-repository-4404355a3133


```
2021-02-20 21:09:22.731  WARN 2666 --- [ioEventLoop-6-1] i.l.c.c.t.DefaultClusterTopologyRefresh  : Unable to connect to [127.0.0.2:7000]: connection timed out: /127.0.0.2:7000
2021-02-20 21:09:22.742  INFO 2666 --- [           main] ConditionEvaluationReportLoggingListener : 

Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
2021-02-20 21:09:22.756 ERROR 2666 --- [           main] o.s.boot.SpringApplication               : Application run failed

java.lang.IllegalStateException: Failed to execute CommandLineRunner
        at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:807) ~[spring-boot-2.4.2.jar:2.4.2]
        at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:788) ~[spring-boot-2.4.2.jar:2.4.2]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:333) ~[spring-boot-2.4.2.jar:2.4.2]
        at example.awsdemo.AwsDemoApplication.main(AwsDemoApplication.java:19) ~[main/:na]
Caused by: org.springframework.data.redis.RedisConnectionFailureException: Unable to connect to Redis; nested exception is io.lettuce.core.RedisException: Cannot obtain initial Redis Cluster topology
        at org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory$ExceptionTranslatingConnectionProvider.translateException(LettuceConnectionFactory.java:1553) ~[spring-data-redis-2.4.3.jar:2.4.3]
        at org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory$ExceptionTranslatingConnectionProvider.getConnection(LettuceConnectionFactory.java:1461) ~[spring-data-redis-2.4.3.jar:2.4.3]
        at org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory$SharedConnection.getNativeConnection(LettuceConnectionFactory.java:1247) ~[spring-data-redis-2.4.3.jar:2.4.3]
        at org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory$SharedConnection.getConnection(LettuceConnectionFactory.java:1230) ~[spring-data-redis-2.4.3.jar:2.4.3]
        at org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory.getClusterConnection(LettuceConnectionFactory.java:378) ~[spring-data-redis-2.4.3.jar:2.4.3]
        at org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory.getConnection(LettuceConnectionFactory.java:355) ~[spring-data-redis-2.4.3.jar:2.4.3]
        at org.springframework.data.redis.core.RedisConnectionUtils.fetchConnection(RedisConnectionUtils.java:193) ~[spring-data-redis-2.4.3.jar:2.4.3]
        at org.springframework.data.redis.core.RedisConnectionUtils.doGetConnection(RedisConnectionUtils.java:144) ~[spring-data-redis-2.4.3.jar:2.4.3]
        at org.springframework.data.redis.core.RedisConnectionUtils.getConnection(RedisConnectionUtils.java:105) ~[spring-data-redis-2.4.3.jar:2.4.3]
        at org.springframework.data.redis.core.RedisTemplate.execute(RedisTemplate.java:209) ~[spring-data-redis-2.4.3.jar:2.4.3]
        at org.springframework.data.redis.core.RedisTemplate.execute(RedisTemplate.java:189) ~[spring-data-redis-2.4.3.jar:2.4.3]
        at org.springframework.data.redis.core.AbstractOperations.execute(AbstractOperations.java:96) ~[spring-data-redis-2.4.3.jar:2.4.3]
        at org.springframework.data.redis.core.DefaultValueOperations.set(DefaultValueOperations.java:236) ~[spring-data-redis-2.4.3.jar:2.4.3]
        at example.awsdemo.SpringRunner.run(SpringRunner.java:47) ~[main/:na]
        at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:804) ~[spring-boot-2.4.2.jar:2.4.2]
        ... 3 common frames omitted
Caused by: io.lettuce.core.RedisException: Cannot obtain initial Redis Cluster topology
        at io.lettuce.core.cluster.RedisClusterClient.lambda$getPartitions$0(RedisClusterClient.java:320) ~[lettuce-core-6.0.2.RELEASE.jar:6.0.2.RELEASE]
        at io.lettuce.core.cluster.RedisClusterClient.get(RedisClusterClient.java:886) ~[lettuce-core-6.0.2.RELEASE.jar:6.0.2.RELEASE]
        at io.lettuce.core.cluster.RedisClusterClient.getPartitions(RedisClusterClient.java:320) ~[lettuce-core-6.0.2.RELEASE.jar:6.0.2.RELEASE]
        at org.springframework.data.redis.connection.lettuce.ClusterConnectionProvider.getConnectionAsync(ClusterConnectionProvider.java:92) ~[spring-data-redis-2.4.3.jar:2.4.3]
        at org.springframework.data.redis.connection.lettuce.ClusterConnectionProvider.getConnectionAsync(ClusterConnectionProvider.java:40) ~[spring-data-redis-2.4.3.jar:2.4.3]
        at org.springframework.data.redis.connection.lettuce.LettuceConnectionProvider.getConnection(LettuceConnectionProvider.java:53) ~[spring-data-redis-2.4.3.jar:2.4.3]
        at org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory$ExceptionTranslatingConnectionProvider.getConnection(LettuceConnectionFactory.java:1459) ~[spring-data-redis-2.4.3.jar:2.4.3]
        ... 16 common frames omitted
Caused by: io.lettuce.core.cluster.topology.DefaultClusterTopologyRefresh$CannotRetrieveClusterPartitions: Cannot retrieve cluster partitions from [redis://127.0.0.2:7000?timeout=1s]

Details:
        [redis://127.0.0.2:7000?timeout=1s]: connection timed out: /127.0.0.2:7000

        Suppressed: io.lettuce.core.RedisConnectionException: Unable to connect to [127.0.0.2:7000]: connection timed out: /127.0.0.2:7000
                at io.lettuce.core.cluster.topology.DefaultClusterTopologyRefresh.lambda$openConnections$11(DefaultClusterTopologyRefresh.java:339) ~[lettuce-core-6.0.2.RELEASE.jar:6.0.2.RELEASE]
                at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:859) ~[na:na]
                at java.base/java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:837) ~[na:na]
                at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:506) ~[na:na]
                at java.base/java.util.concurrent.CompletableFuture.completeExceptionally(CompletableFuture.java:2088) ~[na:na]
                at io.lettuce.core.AbstractRedisClient.lambda$initializeChannelAsync0$4(AbstractRedisClient.java:405) ~[lettuce-core-6.0.2.RELEASE.jar:6.0.2.RELEASE]
                at io.netty.util.concurrent.DefaultPromise.notifyListener0(DefaultPromise.java:578) ~[netty-common-4.1.58.Final.jar:4.1.58.Final]
                at io.netty.util.concurrent.DefaultPromise.notifyListeners0(DefaultPromise.java:571) ~[netty-common-4.1.58.Final.jar:4.1.58.Final]
                at io.netty.util.concurrent.DefaultPromise.notifyListenersNow(DefaultPromise.java:550) ~[netty-common-4.1.58.Final.jar:4.1.58.Final]
                at io.netty.util.concurrent.DefaultPromise.notifyListeners(DefaultPromise.java:491) ~[netty-common-4.1.58.Final.jar:4.1.58.Final]
                at io.netty.util.concurrent.DefaultPromise.setValue0(DefaultPromise.java:616) ~[netty-common-4.1.58.Final.jar:4.1.58.Final]
                at io.netty.util.concurrent.DefaultPromise.setFailure0(DefaultPromise.java:609) ~[netty-common-4.1.58.Final.jar:4.1.58.Final]
                at io.netty.util.concurrent.DefaultPromise.tryFailure(DefaultPromise.java:117) ~[netty-common-4.1.58.Final.jar:4.1.58.Final]
                at io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe$1.run(AbstractNioChannel.java:262) ~[netty-transport-4.1.58.Final.jar:4.1.58.Final]
                at io.netty.util.concurrent.PromiseTask.runTask(PromiseTask.java:98) ~[netty-common-4.1.58.Final.jar:4.1.58.Final]
                at io.netty.util.concurrent.ScheduledFutureTask.run(ScheduledFutureTask.java:170) ~[netty-common-4.1.58.Final.jar:4.1.58.Final]
                at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:164) ~[netty-common-4.1.58.Final.jar:4.1.58.Final]
                at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:472) ~[netty-common-4.1.58.Final.jar:4.1.58.Final]
                at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:500) ~[netty-transport-4.1.58.Final.jar:4.1.58.Final]
                at io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:989) ~[netty-common-4.1.58.Final.jar:4.1.58.Final]
                at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74) ~[netty-common-4.1.58.Final.jar:4.1.58.Final]
                at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30) ~[netty-common-4.1.58.Final.jar:4.1.58.Final]
                at java.base/java.lang.Thread.run(Thread.java:834) ~[na:na]
        Caused by: io.netty.channel.ConnectTimeoutException: connection timed out: /127.0.0.2:7000
                at io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe$1.run(AbstractNioChannel.java:261)
                ... 9 common frames omitted

```



https://stackoverflow.com/questions/47965495/how-to-isolate-spring-boot-app-redis-and-spring-boot-session-global-redis


https://github.com/spring-projects/spring-session/issues/834
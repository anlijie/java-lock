# java-lock
java版本的用Zookeeper实现的分布式锁

#业务场景

在分布式情况，生成全局订单号ID

生成订单号方案

1.	使用时间戳

2.	使用UUID

3.	推特 (Twitter) 的 Snowflake 算法——用于生成唯一 ID

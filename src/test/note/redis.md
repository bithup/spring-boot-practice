- 引入pom依赖时一同一如commons-pool2
- 数据库可视化工具：http://www.treesoft.cn/dms.html

- spring boot默认使用lettuce作为Redis客户端，相比jedis  
lettuce是线程安全的

api
- opsForValue：对应String字符串
- opsForZSet ：对应ZSet有序集合
- opsForHash ：对应Hash哈希
- opsForList ：对应List列表
- opsForSet  ：对应Set集合
- opsForGeo  ：对应GEO地理位置
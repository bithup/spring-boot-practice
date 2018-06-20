JPA是ORM框架的规范，spring-data-jpa和标准的jpa  
是同一级别的，功能更丰富，切换底层ORM实现框架更方便

spring-data-jpa中的Repository

### 基本的JPA注解
- javax.persistence包，实体类与数据库表映射注解
- @Entity
- @Table
- @Id
- @GeneratedValue
- @Column
- @ManyToOne
- @OneToMany
- @JoinColumn

- CRUD注解
- @Query

### spring data jpa接口
- Repository：仅仅是一个标识，表明任何继承它的均为仓库接口类，方便Spring自动扫描识别 
- CrudRepository：继承Repository，实现了一组CRUD相关的方法 
- PagingAndSortingRepository：继承CrudRepository，实现了一组分页排序相关的方法 
- JpaRepository：继承PagingAndSortingRepository，实现一组JPA规范相关的方法 
- JpaSpecificationExecutor：比较特殊，不属于Repository体系，实现一组JPA Criteria查询相关的方法。

### JpaRepository
- save() 可以保存和更新对象，save一个list可批量更新，实体类中没有主键ID  
执行插入操作，有ID执行更新操作
- saveAndFlush() 刷新缓存到数据库



### 问题
- @Column注解字段时，字段的长度一般设为多少比较合适，  
比如email字段的长度设为多少合适
- MySQL存汉字，一个汉字的长度算多少
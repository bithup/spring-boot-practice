### jdbc版本与jdk版本

- jdk6包含jdbc4.0
- jdk7包含jdbc4.1
- jdk8包含jdbc4.2

### jdbc新特性

- 自动加载驱动，只要引入依赖，不需手动加载
- 异常处理改进
- 增强的BLOB/CLOB功能
- Connection 和 Statement接口的增强
- 国家字符集支持
- SQL ROWID访问
- 注解支持

### jdbc驱动的类型
- 访问一种数据库时，使用纯Java编写的驱动
- 同时访问多种数据库，如：同时访问MySQL和Oracle(区别于同一种数据库多数据源)，  
使用三层方法访问数据库
- 其他还有桥接odbc等

### jdbc连接Oracle
- 由于版权问题，Maven仓库中没有最新的ojdbc驱动，需要先获取驱动jar文件，  
然后手动安装到本地Maven仓库

- 在oracle的安装目录中可以找到ojdbc的jar文件
- 安装到本地Maven仓库，artifactId、groupId、version如何填写

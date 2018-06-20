- 在写一个公用的log方法，不用在每个类中获取日志对象
- 记录日志按用户、业务流程记录、重要的业务记录到单独的日志
- 日志可以先保存到内存Vector向量中，在保存到磁盘上
- 将INFO输出到控制台，将WARN和ERROR分别输出到不同的文件中

spring boot 默认使用logback记录日志
### 控制台打印日志的格式（从左到右）：
- 时间日期：精确到毫秒
- 日志级别：ERROR, WARN, INFO, DEBUG or TRACE
- 进程ID
- 分隔符：\— 标识实际日志的开始
- 线程名：方括号括起来（可能会截断控制台输出）
- Logger名：通常使用源代码的类名
- 日志内容

### 日志级别
- TRACE < DEBUG < INFO < WARN < ERROR < FATAL
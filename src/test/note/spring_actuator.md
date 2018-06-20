### 端点

endpoint       |描述	                           |HTTP
---------------|-------------------------------|--------
auditevents	   |显示通用的监控信息	               |GET
beans	       |显示应用程序上下文所有的Spring bean |GET
configprops	   |显示所有 @ConfigurationProperties 的配置属性列表	 |GET
dump	       |显示线程活动的快照	               |GET
env	           |显示应用的环境变量	               |GET
health	       |显示应用程序的健康指标，这些值由HealthIndicator的实现类提供。常见取值：UP / DOWN / UNKNOWN / OUT_OF_SERVICE 	|GET
info	       |显示应用的信息，可使用 info.* 属性自定义info端点公开的数据	|GET
mappings	   |显示所有的URL路径	|GET
metrics	       |显示应用的度量标准信息	|GET
shutdown	   |关闭应用（默认情况下不启用，如需启用，需设置endpoints.shutdown.enabled=true）  |	POST
sessions	   |程序sessions的信息	|GET
conditions	   |显示配置的条件和原因	|GET
httptrace	   |显示http追踪信息	|GET
loggers	       |显示修饰和配置的日志	|GET
scheduledtasks |显示程序中预定的任务	|GET

### 访问方式
如果配置 management.server.port
spring actuator 会启动一个单独的tomcat service ，并监听该端口  

如果配置management.server.servlet.context-path
访问路径前都要加上context-path

如果都不配置，访问路径就是 /actuator/端点名


### 自定义端点


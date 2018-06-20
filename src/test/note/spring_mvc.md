### 请求参数绑定

- @RequestBody用来绑定对象或List
- 问题：前端请求发送Json对象和发送Json字符串有什么区别  
接收参数时有什么区别，[参考](https://blog.csdn.net/lzdujing1/article/details/52325143)
- @ModelAttribute的用法有两种：一是用在方法参数上，绑定参数到  
实体类，而是用在Controller中的一个无返回值方法上，每次调用Controller  
中的接口时都会先执行该方法
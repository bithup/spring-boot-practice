- 通过配置error page在Controller中发生异常时  
返回一个友好的提示
- 通过自定义异常处理器，用到  
@ControllerAdvice  
@ResponseBody  
@ExceptionHandler
@ResponseStatus等注解

- @ControllerAdvice只能处理Controller中发生的异常，比如  
前端出入的时间字符串在自动转换时发生错误不会被捕捉

- 实现ErrorController，捕捉过滤器中发生的异常

- 如何实现异常保存到日志文件，并给开发人员发送通知
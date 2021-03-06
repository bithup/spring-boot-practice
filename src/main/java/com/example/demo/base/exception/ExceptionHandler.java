package com.example.demo.base.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 同一处理异常
 */
//@Component
@Log4j2
public class ExceptionHandler implements HandlerExceptionResolver {
    private static final int simpleStackTraceLines = 5;

    public static void printSimpleStackTrace(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        sb.append(ex.toString()).append("\n");

        StackTraceElement[] stackElements = ex.getStackTrace();
        if (stackElements != null) {
            for (int i = 0; i < (stackElements.length > simpleStackTraceLines ? simpleStackTraceLines : stackElements.length); i++) {
                sb.append("\t")
                        .append(stackElements[i].getClassName())
                        .append(".")
                        .append(stackElements[i].getMethodName())
                        .append("(")
                        .append(stackElements[i].getFileName())
                        .append(":")
                        .append(stackElements[i].getLineNumber()).append(")")
                        .append("\n");

            }
            if (stackElements.length > simpleStackTraceLines)
                sb.append("---ignore ").append(stackElements.length - simpleStackTraceLines).append(" lines---\n");

            log.error(sb.toString());
        }
    }

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception e) {
        ModelAndView mv = new ModelAndView();

        //设置状态码
        response.setStatus(HttpStatus.OK.value());
        //设置ContentType
        response.setContentType(String.valueOf(MediaType.APPLICATION_JSON));
        //避免乱码
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");

        try {
            String ret="错误信息";

            if (e instanceof BaseRuntimeException) {
                log.debug(e.getMessage(), e);
                printSimpleStackTrace(e);

                int code = ((BaseRuntimeException) e).getCode();
                String msg = e.getMessage();

                //ret = ResultUtil.error(code, msg).toString();
            } else {
                if(log.isDebugEnabled())
                    log.debug(e.getMessage(), e);
                else
                    printSimpleStackTrace(e);

//                ret = ResultUtil.error(-1, e.getLocalizedMessage()).toString();
                //ret = ResultUtil.error(-1, e.toString()).toString();
            }

            response.getWriter().write(ret);
        } catch (IOException ioe) {
            log.debug(ioe.getMessage(), ioe);
            printSimpleStackTrace(e);
        }

        return mv;
    }
}

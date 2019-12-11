package com.mountain.common.exception;

import com.mountain.common.util.response.GlobalStatusCode;
import com.mountain.common.util.response.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

/**
 * @author chzz
 * @version V1.0
 * @Title:
 * @Package com.mountain.common.exception
 * @Description: TODO 系统－全局异常类
 * @date 2019/2/23
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 系统异常
     * @param e
     * @return
     */
    @ExceptionHandler({RuntimeException.class})
    public ResultBean runtimeExceptionHandler(RuntimeException e) {
        logger.error("{}", e);
        return new ResultBean(GlobalStatusCode.CODE_500000.code(), e.getMessage());
    }


    /**
     * dubbo服务方面的异常
     * @param e
     * @return
     */
    /*@ExceptionHandler({RpcException.class})
    public ResultBean rpcExceptionHandler(RpcException e) {
        logger.error(e);
        return new ResultBean(GlobalStatusCode.CODE_500000.value(), GlobalStatusCode.CODE_500000.remark());
    }*/

    /**
     * 业务方面的异常
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public ResultBean serviceExceptionHandler(ServiceException e) {
        logger.error(e.getMsg(), e);
        return new ResultBean(e.getCode(), e.getMsg(), e.getData());
    }

    /**
     * shiro方面的异常
     * @param e
     * @return
     */
//    @ExceptionHandler(AuthorizationException.class)
//    public ResultBean serviceExceptionHandler(Object e) {
//        AuthorizationException authorizationException = (AuthorizationException)e;
//        logger.error(authorizationException.getMessage(), authorizationException);
//        if (e instanceof UnauthenticatedException) {
//            return new ResultBean(GlobalStatusCode.CODE_400001.code(), GlobalStatusCode.CODE_400001.value());
//        }
//        return new ResultBean(GlobalStatusCode.CODE_400003.code(), GlobalStatusCode.CODE_400003.value());
//    }

    /**
     * 接口参数检验方面的异常
     * @param e
     * @param bindingResult
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResultBean bindExceptionHandler(BindException e, BindingResult bindingResult) {
        logger.error("{}", e.getAllErrors());
        List<ObjectError> objectErrorList = bindingResult.getAllErrors();
        StringBuilder sb = new StringBuilder();
        ObjectError objectError;
        for (int i = 0, inx = objectErrorList.size(); i < inx; i++) {
            objectError = objectErrorList.get(i);
            sb.append(objectError.getDefaultMessage());
            if (i < objectErrorList.size() - 1) {
                sb.append(",");
            }
        }
        return new ResultBean(GlobalStatusCode.CODE_400000.code(), sb.toString());
    }

    /**
     * 404异常信息
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResultBean noHandlerFoundExceptionHandler(NoHandlerFoundException e) {
        logger.error("{}", e);
        return new ResultBean(GlobalStatusCode.CODE_400004.code(), new StringBuffer(GlobalStatusCode.CODE_400004.value())
                .append(":").append(e.getRequestURL())
                .append("|").append(e.getHttpMethod()).toString());
    }
}

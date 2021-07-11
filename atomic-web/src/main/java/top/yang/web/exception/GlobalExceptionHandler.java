package top.yang.web.exception;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.yang.exception.AtomicCode;
import top.yang.exception.ResultCode;
import top.yang.web.enums.WebCode;
import top.yang.web.response.ResponseResult;

@ControllerAdvice(basePackages = "top.yang")
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  //定义map，配置异常类型所对应的错误代码
  private static ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;
  //定义map的builder对象，去构建ImmutableMap
  protected static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableMap
      .builder();

  //捕获CustomException此类异常
  @ExceptionHandler(WebException.class)
  @ResponseBody
  public ResponseResult customException(WebException webException) {
    webException.printStackTrace();
    //记录日志
    logger.error("catch exception:{}", webException.getMessage());
    ResultCode resultCode = webException.getResultCode();
    return new ResponseResult(resultCode);
  }

  //捕获Exception此类异常
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseResult exception(Exception exception) {
    exception.printStackTrace();
    //记录日志
    logger.error("catch exception:{}", exception.getMessage());
    if (EXCEPTIONS == null) {
      EXCEPTIONS = builder.build();//EXCEPTIONS构建成功
    }
    //从EXCEPTIONS中找异常类型所对应的错误代码，如果找到了将错误代码响应给用户，如果找不到给用户响应99999异常
    ResultCode resultCode = EXCEPTIONS.get(exception.getClass());
    if (resultCode != null) {
      return new ResponseResult(resultCode);
    } else {
      //返回9999异常
      return new ResponseResult(AtomicCode.FAIL);
    }


  }

  static {
    //定义异常类型所对应的错误代码
    builder.put(HttpMessageNotReadableException.class, WebCode.INVALID_PARAM);
  }
}

package top.yang.web.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.yang.constants.Globals;
import top.yang.exception.CommonCode;
import top.yang.io.FileWapper;
import top.yang.web.domain.vo.ResponseResult;

/**
 * @date 2021/8/30 11:28
 */
@ControllerAdvice(basePackages = "top.yang")
public class GlobalResponseHandler implements ResponseBodyAdvice {

    private final static Logger logger = LoggerFactory.getLogger(GlobalResponseHandler.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final static String VOID_VALUE = "void";

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return returnType.hasMethodAnnotation(ResponseBody.class)
                || returnType.getDeclaringClass().isAnnotationPresent(RestController.class) || returnType.getDeclaringClass().isAnnotationPresent(Controller.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
        String requestId = MDC.get(Globals.REQUEST_ID);
        if (VOID_VALUE.equals(Objects.requireNonNull(returnType.getMethod()).getReturnType().getName())) {
            return new ResponseResult(CommonCode.SUCCESS, requestId);
        }
        if (body instanceof File) {
            File file = (File) body;
            try {
                response
                        .getHeaders()
                        .add(
                                "Content-Disposition",
                                "attachment;filename=" + java.net.URLEncoder.encode((file).getName(), "UTF-8"));
                response.getBody().write(FileWapper.from(file).read());
                response.flush();
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseResult(CommonCode.FAIL, requestId);
            }
            return null;
        } else if (body instanceof String) {
            try {
                return objectMapper.writeValueAsString(new ResponseResult(CommonCode.SUCCESS, body, requestId));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                logger.error("catch exception:{}", e.getMessage());
                return new ResponseResult(CommonCode.FAIL, requestId);
            }
        } else {
            return new ResponseResult(CommonCode.SUCCESS, body, requestId);
        }
    }
}
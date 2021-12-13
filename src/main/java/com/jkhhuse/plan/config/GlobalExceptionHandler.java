package com.jkhhuse.plan.config;

import com.jkhhuse.plan.common.CommonResponse;
import com.jkhhuse.plan.common.MetaCode;
import com.jkhhuse.plan.constants.ExceptionConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice(annotations = {RestController.class, ControllerAdvice.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse<String> validationErrorHandler(MethodArgumentNotValidException ex) {
        // 同样是获取BindingResult对象，然后获取其中的错误信息,
        // 如果前面开启了fail_fast，事实上这里只会有一个信息, 如果没有，则可能又多个
        List<String> errorList = ex.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        // 创建并构造错误提示响应实例
        CommonResponse<String> response = new CommonResponse<String>();
        response.setData(ExceptionConstants.VALIDATE_PARAMS_ERROR);
        MetaCode meta = new MetaCode("402", ExceptionConstants.VALIDATE_PARAMS_ERROR
                + ":" + StringUtils.join(errorList, ';'));
        response.setMeta(meta);
        return response;
    }
}
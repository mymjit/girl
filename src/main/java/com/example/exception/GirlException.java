package com.example.exception;


import com.example.enums.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定义自己的异常
 * 必需继承RuntimeException 不是RuntimeException Spring事物不会回滚
 */
public class GirlException extends RuntimeException {



    private Integer code;

    public GirlException() {
    }

    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

package util.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.core.env.Environment;
import util.CodeConstant;
import util.SpringContextHolder;

import java.io.Serializable;
import java.util.Optional;

/**
 * 响应消息头
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestResponseHeader implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1031448932631195295L;

    /**
     * key:spring.application.name
     */
    private static final String KEY_APP = "spring.application.name";

    /**
     * 状态码
     */
    private String code = CodeConstant.SUCCESS;

    /**
     * APP
     */
    private String app = null;

    /**
     * 状态描述
     */
    private String message;

    /**
     * 二级状态码
     */
    private String subCode;

    /**
     * 二级状态描述
     */
    private String subMessage;

    /**
     * 异常类型
     */
    private String type;

    /**
     * 异常堆栈信息
     */
    private String errTrace;

    /**
     * 扩展字段
     */
    private String ext;

    /**
     * 调用跟踪栈元素
     */
    private StackTraceElement[] stackTraceElements;

    /**
     * 构造方法
     */
    public RestResponseHeader() {
        super();
        app = Optional.ofNullable(app).orElse(SpringContextHolder.getBean(Environment.class).getProperty(KEY_APP));
    }

    /**
     * 构造方法
     *
     * @param code    状态码
     * @param message 状态描述
     */
    public RestResponseHeader(String code, String message) {
        super();
        this.code = code;
        this.message = message;
        app = Optional.ofNullable(app).orElse(SpringContextHolder.getBean(Environment.class).getProperty(KEY_APP));
    }

    /**
     * 构造方法
     *
     * @param code     状态码
     * @param message  状态描述
     * @param errTrace 异常堆栈信息
     */
    public RestResponseHeader(String code, String message, String errTrace) {
        super();
        this.code = code;
        this.message = message;
        this.errTrace = errTrace;
        app = Optional.ofNullable(app).orElse(SpringContextHolder.getBean(Environment.class).getProperty(KEY_APP));
    }

    /**
     * 构造方法
     *
     * @param code     状态码
     * @param message  状态描述
     * @param errType  异常类型
     * @param errTrace 异常堆栈信息
     */
    public RestResponseHeader(String code, String message, String errType, String errTrace) {
        super();
        this.code = code;
        this.message = message;
        this.type = errType;
        this.errTrace = errTrace;
        app = Optional.ofNullable(app).orElse(SpringContextHolder.getBean(Environment.class).getProperty(KEY_APP));
    }

    /**
     * 构造方法
     *
     * @param code       状态码
     * @param message    状态描述
     * @param subCode    二级状态码
     * @param subMessage 二级状态描述
     * @param errType    异常类型
     * @param errTrace   异常堆栈信息
     */
    public RestResponseHeader(String code, String message, String subCode, String subMessage, String errType,
                              String errTrace) {
        super();
        this.code = code;
        this.message = message;
        this.subCode = subCode;
        this.subMessage = subMessage;
        this.type = errType;
        this.errTrace = errTrace;
        app = Optional.ofNullable(app).orElse(SpringContextHolder.getBean(Environment.class).getProperty(KEY_APP));
    }

    /**
     * setter
     *
     * @param stackTraceElements
     */
    public void setStackTraceElements(StackTraceElement[] stackTraceElements) {
        if (stackTraceElements != null) {
            this.stackTraceElements = stackTraceElements.clone();
        } else {
            this.stackTraceElements = null;
        }
    }

    /**
     * getter
     *
     * @return
     */
    public StackTraceElement[] getStackTraceElements() {
        if (stackTraceElements == null) {
            return null;
        }
        return stackTraceElements.clone();
    }
}

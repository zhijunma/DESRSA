package util.request;

/**
 * @author:HuMin Date:2019/3/5
 * Time:10:12
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * 请求消息体
 *
 * @author YAZUO)WU-TONG
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestRequest<T> implements Serializable {
    private static final long serialVersionUID = -1296515585582912062L;

    /**
     * 请求消息头
     */
    private RestRequestHeader header;

    /**
     * 响应消息体(泛型)
     */
    @Valid
    private T body = null;

    /**
     * 构造方法
     */
    public RestRequest() {
        super();
        this.header = new RestRequestHeader();
    }

    /**
     * 构造方法
     *
     * @Param body 请求消息体(泛型)
     */
    public RestRequest(T body) {
        super();
        this.header = new RestRequestHeader();
        this.body = body;
    }

    /**
     * 静态构造方法
     *
     * @Param body 请求消息体(泛型)
     */
    public static <T> RestRequest<T> instance(T body) {
        return new RestRequest<T>(body);
    }
}

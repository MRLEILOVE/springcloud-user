package com.leigq.user.bean;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 响应对象。包含处理结果（Meta）和返回数据（Data）两部分，在 Controller 处理完请求后将此对象转换成 json 返回给前台。注意：
 * <ul>
 * <li>处理成功一般返回处理结果和返回数据，失败只返回处理结果。具体返回什么需看接口文档。</li>
 * <li>处理成功结果码一般是200，失败码具体看出了什么错，对照 HTTP 响应码填。</li>
 * <li>默认处理方法慎用，前台最想要拿到的还是具体的结果码和信息。</li>
 * </ul>
 * <p>
 * 创建人：leigq <br>
 * 创建时间：2017年10月9日 下午3:26:17 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
@Component
@Scope("prototype")
public class Response {

	/** 默认成功响应码 */
	private static final String DEAFAULT_SUCCESS_CODE = "200";
	/** 默认成功响应信息 */
	private static final String DEAFAULT_SUCCESS_MSG = "请求/处理成功！";
	/** 默认失败响应码 */
	private static final String DEAFAULT_FAILURE_CODE = "500";
	/** 默认失败响应信息 */
	private static final String DEAFAULT_FAILURE_MSG = "请求/处理失败！";

    private Meta meta;
    private Object data;

    /**
     * 处理成功响应，仅返回默认响应码和信息。
     * <p>
     * 创建人：leigq <br>
     * 创建时间：2017年10月9日 下午3:55:26 <br>
     * <p>
     * 修改人： <br>
     * 修改时间： <br>
     * 修改备注： <br>
     * </p>
     * @return 响应对象
     */
    public Response success() {
        this.meta = new Meta(DEAFAULT_SUCCESS_CODE, DEAFAULT_SUCCESS_MSG);
        return this;
    }

    /**
     * 处理成功响应，返回自定义响应码、信息和数据。
     * <p>
     * 创建人：leigq <br>
     * 创建时间：2017年10月9日 下午3:55:57 <br>
     * <p>
     * 修改人： <br>
     * 修改时间： <br>
     * 修改备注： <br>
     * </p>
     * @param code HTTP 响应码，如果要使用默认值请传null
     * @param msg 处理结果信息，如果要使用默认值请传null
     * @param data 返回数据，如果没有数据请传null
     * @return 响应对象
     */
    public Response success(String code, String msg, Object data) {
    	if (StringUtils.isBlank(code)) {
			code = Response.DEAFAULT_SUCCESS_CODE;
		}
    	if (StringUtils.isBlank(msg)) {
    		msg = Response.DEAFAULT_SUCCESS_MSG;
		}
    	this.meta = new Meta(code, msg);
        this.data = data;
        return this;
	}

	/**
	 * 处理成功响应，返回自定义响应码、信息和数据。
	 * <p>
	 * 创建人：leigq <br>
	 * 创建时间：2017年10月9日 下午3:55:57 <br>
	 * <p>
	 * 修改人： <br>
	 * 修改时间： <br>
	 * 修改备注： <br>
	 * </p>
	 * @param msg 处理结果信息
	 * @param data 返回数据
	 * @return 响应对象
	 */
	public Response success(String msg, Object data) {
		if (StringUtils.isBlank(msg)) {
			msg = Response.DEAFAULT_SUCCESS_MSG;
		}
		this.meta = new Meta(Response.DEAFAULT_SUCCESS_CODE, msg);
		this.data = data;
		return this;
	}

	/**
	 * 处理成功响应，返回自定义响应码、信息和数据。
	 * <p>
	 * 创建人：leigq <br>
	 * 创建时间：2017年10月9日 下午3:55:57 <br>
	 * <p>
	 * 修改人： <br>
	 * 修改时间： <br>
	 * 修改备注： <br>
	 * </p>
	 * @param data 返回数据，如果没有数据请传null
	 * @return 响应对象
	 */
	public Response success(Object data) {
		this.meta = new Meta(Response.DEAFAULT_SUCCESS_CODE, Response.DEAFAULT_SUCCESS_MSG);
		this.data = data;
		return this;
	}

    /**
     * 处理失败响应，仅返回默认响应码和信息。
     * <p>
     * 创建人：leigq <br>
     * 创建时间：2017年10月9日 下午3:56:46 <br>
     * <p>
     * 修改人： <br>
     * 修改时间： <br>
     * 修改备注： <br>
     * </p>
     * @return 响应对象
     */
    public Response failure(Object data) {
        this.meta = new Meta(DEAFAULT_FAILURE_CODE, DEAFAULT_FAILURE_MSG);
		this.data = data;
        return this;
    }
    
    /**
     * 处理失败响应，返回自定义响应码、信息和数据。
     * <p>
     * 创建人：leigq <br>
     * 创建时间：2017年10月9日 下午3:58:54 <br>
     * <p>
     * 修改人： <br>
     * 修改时间： <br>
     * 修改备注： <br>
     * </p>
     * @param code HTTP 响应码，如果要使用默认值请传null
     * @param msg 处理结果信息，如果要使用默认值请传null
     * @param data 返回数据，如果没有数据请传null
     * @return 响应对象
     */
    public Response failure(String code, String msg, Object data) {
    	if (StringUtils.isBlank(code)) {
			code = Response.DEAFAULT_FAILURE_CODE;
		}
    	if (StringUtils.isBlank(msg)) {
    		msg = Response.DEAFAULT_FAILURE_MSG;
		}
    	this.meta = new Meta(code, msg);
        this.data = data;
        return this;
	}

	/**
	 * 处理失败响应，返回自定义响应码、信息和数据。
	 * <p>
	 * 创建人：leigq <br>
	 * 创建时间：2017年10月9日 下午3:58:54 <br>
	 * <p>
	 * 修改人： <br>
	 * 修改时间： <br>
	 * 修改备注： <br>
	 * </p>
	 * @param msg 处理结果信息，如果要使用默认值请传null
	 * @param data 返回数据，如果没有数据请传null
	 * @return 响应对象
	 */
	public Response failure(String msg, Object data) {
		if (StringUtils.isBlank(msg)) {
			msg = Response.DEAFAULT_FAILURE_MSG;
		}
		this.meta = new Meta(DEAFAULT_FAILURE_CODE, msg);
		this.data = data;
		return this;
	}
    
    public Meta getMeta() {
        return meta;
    }

    public Object getData() {
        return data;
    }

    /**
     * 元数据，包含响应码和信息。
     * <p>
     * 创建人：leigq <br>
     * 创建时间：2017年10月9日 下午3:31:17 <br>
     * <p>
     * 修改人： <br>
     * 修改时间： <br>
     * 修改备注： <br>
     * </p>
     */
    @Data
    public class Meta {
    	
        /**
         * 处理结果代码，与 HTTP 状态响应码对应
         */
    	private String code;
    	
    	/**
    	 * 处理结果信息
    	 */
        private String msg;

		Meta(String code, String msg) {
			super();
			this.code = code;
			this.msg = msg;
		}
    }
}

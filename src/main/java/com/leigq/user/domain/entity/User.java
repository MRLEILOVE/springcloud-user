package com.leigq.user.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.leigq.common.springboot.bean.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户
 * <p>
 * 创建人：LeiGQ <br>
 * 创建时间：2019-03-14 20:01 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity implements Serializable {
    /**
	* 用户名
	*/
    private String userName;

    /**
	* 密码（MD5加密）
	*/
    private String password;

    private static final long serialVersionUID = 1L;
}
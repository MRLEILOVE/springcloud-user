package com.leigq.user.service;

import com.leigq.user.domain.entity.User;
import com.leigq.user.domain.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 用户
 * <p>
 * 创建人：LeiGQ <br>
 * 创建时间：2019-03-14 20:05 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
@Service
@Transactional
public class UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 根据用户名获取用户
     * <br>创建人： leiGQ
     * <br>创建时间： 2019-03-14 20:06
     * <p>
     * 修改人： <br>
     * 修改时间： <br>
     * 修改备注： <br>
     * </p>
     * <br>
     * @param userName 用户名
     * @return 用户
     */
    public User getByUserName(String userName) {
        return userMapper.getByUserName(userName);
    }

}

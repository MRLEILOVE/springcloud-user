package com.leigq.user.domain.mapper;

import com.leigq.user.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;

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
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

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
     *
     * @param userName 用户名
     * @return 用户
     */
    User getByUserName(@Param("userName")String userName);
}
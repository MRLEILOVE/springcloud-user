package com.leigq.user.controller;

import com.leigq.user.bean.Response;
import com.leigq.user.constant.CookieConstant;
import com.leigq.user.domain.entity.User;
import com.leigq.user.service.UserService;
import com.leigq.user.util.CookieUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 用户
 * <p>
 * 创建人：LeiGQ <br>
 * 创建时间：2019-03-14 20:12 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private Response response;


    /**
     * 买家登录
     * <br>创建人： leiGQ
     * <br>创建时间： 2019-03-14 20:19
     * <p>
     * 修改人： <br>
     * 修改时间： <br>
     * 修改备注： <br>
     * </p>
     * <br>
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping("/buyer/actions/login")
    public Response buyerLogin(HttpServletResponse resp, String userName, String password) {
        final User buyerUser = userService.getByUserName(userName);
        if (Objects.isNull(buyerUser)) {
            return response.failure("用户不存在!");
        }

        // DigestUtils.md5Hex(password) 暂时不加密
        if (!buyerUser.getPassword().equals(password)) {
            return response.failure("登录密码错误！");
        }
        CookieUtil.set(resp, CookieConstant.TOKEN, userName, CookieConstant.MAX_AGE);

        return response.success("登录成功！");
    }


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
    @GetMapping("/users/{username}")
    public Response getByUserName(@PathVariable("username") String userName) {
        return response.success(userService.getByUserName(userName));
    }
}

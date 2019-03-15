package com.leigq.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leigq.user.bean.Response;
import com.leigq.user.constant.CookieConstant;
import com.leigq.user.constant.RedisConstant;
import com.leigq.user.domain.entity.User;
import com.leigq.user.service.UserService;
import com.leigq.user.util.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private StringRedisTemplate strRedisTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 登录
     * <br>创建人： leiGQ
     * <br>创建时间： 2019-03-14 20:19
     * <p>
     * 修改人： <br>
     * 修改时间： <br>
     * 修改备注： <br>
     * </p>
     * <br>
     *
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping("/actions/login")
    public Response login(HttpServletRequest request, HttpServletResponse resp,
                               String userName, String password) throws IOException {
        // 判断是否已登录, 一般写在登录拦截器里面
        final Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (Objects.nonNull(cookie)) {
            String userRedis = strRedisTemplate.opsForValue()
                    .get(String.format(RedisConstant.USER_KEY, cookie.getValue()));
            if (StringUtils.isNotBlank(userRedis)) {
                return response.success("登录成功！", objectMapper.readValue(userRedis, User.class));
            }
        }

        final User user = userService.getByUserName(userName);
        if (Objects.isNull(user)) {
            return response.failure("用户不存在!");
        }

        // DigestUtils.md5Hex(password) 暂时不加密
        if (!user.getPassword().equals(password)) {
            return response.failure("登录密码错误！");
        }

        // 存Redis key = token_UUID, value = user对象
        final String token = UUID.randomUUID().toString();
        strRedisTemplate.opsForValue().set(String.format(RedisConstant.USER_KEY, token),
                objectMapper.writeValueAsString(user), CookieConstant.MAX_AGE, TimeUnit.SECONDS);

        // 存Cookie token = UUID
        CookieUtil.set(resp, CookieConstant.TOKEN, token, CookieConstant.MAX_AGE);

        return response.success("登录成功！", user);
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
     *
     * @param userName 用户名
     * @return 用户
     */
    @GetMapping("/users/{username}")
    public Response getByUserName(@PathVariable("username") String userName) {
        return response.success(userService.getByUserName(userName));
    }
}

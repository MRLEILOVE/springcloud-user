package com.leigq.user.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName
 * <p>
 * 创建人：LeiGQ <br>
 * 创建时间：2019-03-14 20:37 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
public class CookieUtil {

    public static void set(HttpServletResponse response, String name,
                           String value, Integer maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
}

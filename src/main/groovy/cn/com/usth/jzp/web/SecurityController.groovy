package cn.com.usth.jzp.web

import cn.com.usth.jzp.entity.User
import cn.com.usth.jzp.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Administrator on 2016/5/7.
 */
@RestController
@RequestMapping("/free")
class SecurityController {

    @Autowired
    UserService userService

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    Object register(@RequestBody User u) {
        userService.add(u)
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    String token(@RequestBody User u) {
        u = userService.login(u.loginname, u.password)
        if (!u) {
            throw new RuntimeException("登录失败！")
        }
        u.token
    }

    @RequestMapping(value = "/master-login", method = RequestMethod.POST)
    String token(String masterPassword) {
        String token = userService.masterLogin(masterPassword)
        if (!token) {
            throw new RuntimeException("登录失败！")
        }
        token
    }


}

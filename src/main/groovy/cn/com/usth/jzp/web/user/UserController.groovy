package cn.com.usth.jzp.web.user

import cn.com.usth.jzp.entity.User
import cn.com.usth.jzp.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Administrator on 2016/5/8.
 */
@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    UserService userService

    @RequestMapping(method = RequestMethod.PUT)
    Object addOrUpdate(@RequestBody User user) {
        userService.update(user)
    }

    @RequestMapping(method = RequestMethod.DELETE)
    Object delete(Integer id) {
        userService.delete(id)
        ResponseEntity.ok()
    }

}

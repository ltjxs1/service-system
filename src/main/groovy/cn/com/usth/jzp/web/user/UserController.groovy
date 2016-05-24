package cn.com.usth.jzp.web.user

import cn.com.usth.jzp.entity.User
import cn.com.usth.jzp.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Created by Administrator on 2016/5/8.
 */
@CrossOrigin(maxAge = 3600L)
@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    UserService userService

    @RequestMapping(method = RequestMethod.PUT)
    Object update(@RequestBody User user) {
        userService.update(user)
    }

    @RequestMapping(method = RequestMethod.GET)
    User validate(String token){
        userService.validate(token)
    }

    @RequestMapping(value="/password",method = RequestMethod.PUT)
    Object refreshPassWord(@RequestBody Map map){
        Integer id = Integer.parseInt(map["id"].toString())
        String oldPassword =map['oldPassword']
        String newPassword =map['newPassword']
        userService.refreshPassWord(id,oldPassword,newPassword)
        [success:true]
    }




}

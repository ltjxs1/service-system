package cn.com.usth.jzp.service

import cn.com.usth.jzp.entity.User
import cn.com.usth.jzp.entity.jpa.UserJpaRepository
import org.apache.commons.codec.digest.Md5Crypt
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * Created by Administrator on 2016/5/7.
 */
@Component
class UserService {

    @Value("\${password.salt}")
    String salt

    @Autowired
    UserJpaRepository userJpaRepository

    User add(User u){
        u.password = passwordEncode(u.password)
        userJpaRepository.save(u)
    }

    User login(String loginname,String password){
        User u = userJpaRepository.findTop1ByLoginnameAndPassword(loginname,passwordEncode(password))
        u.token = RandomStringUtils.randomAlphanumeric(10)
        userJpaRepository.save(u)
    }

    User validate(String token){
        userJpaRepository.findTop1ByToken(token)
    }

    private String passwordEncode(String password){
        Md5Crypt.md5Crypt(password.bytes,salt)
    }

}

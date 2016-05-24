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

    @Value("\${password.masterPassword}")
    String masterPassword

    String masterToken

    @Autowired
    UserJpaRepository userJpaRepository

    User add(User u) {
        u.password = passwordEncode(u.password)
        userJpaRepository.save(u)
    }

    User login(String loginname, String password) {
        User u = userJpaRepository.findTop1ByLoginnameAndPassword(loginname, passwordEncode(password))
        u.token = RandomStringUtils.randomAlphanumeric(10)
        userJpaRepository.save(u)
    }

    User validate(String token) {
        userJpaRepository.findTop1ByToken(token)
    }

    User update(User user) {
        user = flush(user)
        userJpaRepository.save(user)
    }

    void delete(Integer id) {
        userJpaRepository.delete(id)
    }

    User flush(User user) {
        if (user?.id == null) {
            return user
        }
        User user1 = userJpaRepository.findOne(user.id)
        user1.telephone = user.telephone
        user1.wechat = user.wechat
        user1.birthday = user.birthday
        user1.gender = user.gender
        user1.nickname = user.nickname
        user1
    }

    String masterLogin(String masterPassWord) {
        if (validateMasterPassword(masterPassWord)) {
            return masterToken = RandomStringUtils.randomAlphanumeric(10)
        } else {
            return null
        }
    }

    boolean masterValidate(String masterToken) {
        this.masterToken == masterToken
    }

    private String passwordEncode(String password) {
        Md5Crypt.md5Crypt(password.bytes, salt)
    }

    private boolean validateMasterPassword(String masterPassword) {
        this.masterPassword == masterPassword
    }

    User refreshPassWord(Integer id,String oldPassWord,String newPassWord){
        User user = userJpaRepository.findOne(id)
        if(!user?.password?.equals(passwordEncode(oldPassWord))){
            throw new RuntimeException("密码错误！")
        }
        user.password = passwordEncode(newPassWord)
        userJpaRepository.save(user)
    }

}

package cn.com.usth.jzp.entity.jpa;

import cn.com.usth.jzp.entity.User;
import cn.com.usth.jzp.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/5/5.
 */

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

    User findTop1ByLoginnameAndPassword(String username,String password)

    User findTop1ByToken(String token)

}

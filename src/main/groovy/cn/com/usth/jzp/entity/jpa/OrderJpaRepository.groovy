package cn.com.usth.jzp.entity.jpa

import cn.com.usth.jzp.entity.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by Administrator on 2016/5/5.
 */

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Integer> {

}

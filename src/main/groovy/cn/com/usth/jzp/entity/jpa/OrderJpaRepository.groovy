package cn.com.usth.jzp.entity.jpa

import cn.com.usth.jzp.entity.Order
import cn.com.usth.jzp.entity.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

/**
 * Created by Administrator on 2016/5/5.
 */

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Integer> {

    @Override
    @Query("SELECT o FROM Order o ORDER BY o.updateAt DESC")
    Page<Order> findAll(Pageable pageable)

    @Query("SELECT o FROM Order o WHERE o.user.id = ?1 ORDER BY o.createAt DESC")
    Page<Order> findByUserId(Integer userId, Pageable pageable)

}

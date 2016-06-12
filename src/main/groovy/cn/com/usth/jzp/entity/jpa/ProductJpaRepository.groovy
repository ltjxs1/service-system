package cn.com.usth.jzp.entity.jpa

import cn.com.usth.jzp.entity.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by Administrator on 2016/5/5.
 */

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Integer> {

    Page<Product> findByOnSale(boolean onSale, Pageable page)

}

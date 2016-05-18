package cn.com.usth.jzp.service

import cn.com.usth.jzp.entity.Product
import cn.com.usth.jzp.entity.jpa.ProductJpaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by Administrator on 2016/5/8.
 */
@Component
class ProductService {

    @Autowired
    ProductJpaRepository productJpaRepository

    Product addOrUpdate(Product product) {
        product = flush(product)
        productJpaRepository.save(product)
    }

    void delete(Integer id) {
        productJpaRepository.delete(id)
    }

    Product flush(Product product) {
        if (product?.id == null) {
            return product
        }
        Product product1 = productJpaRepository.findOne(product.id)
        product1.description = product.description
        product1.name = product.name
        product1.price = product.price
        product1.onSale = product.onSale
        product1
    }

}

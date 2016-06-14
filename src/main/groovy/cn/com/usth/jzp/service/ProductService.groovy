package cn.com.usth.jzp.service

import cn.com.usth.jzp.entity.Product
import cn.com.usth.jzp.entity.jpa.ProductJpaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

/**
 * Created by Administrator on 2016/5/8.
 */
@Component
class ProductService {

    @Autowired
    ProductJpaRepository productJpaRepository

    Page<Product> select(boolean onSale,int page,int size){
        Pageable pageable = new PageRequest(page,size)
        if(onSale){
            return productJpaRepository.findByOnSale(true,pageable)
        }else{
            return productJpaRepository.findAll(pageable)
        }
    }

    Product changeSaleStat(Integer id){
        Product product = productJpaRepository.findOne(id)
        product.onSale = !product.onSale
        productJpaRepository.save(product)
    }

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
        product1
    }



}

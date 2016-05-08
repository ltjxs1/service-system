package cn.com.usth.jzp.web.master

import cn.com.usth.jzp.entity.Product
import cn.com.usth.jzp.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Administrator on 2016/5/9.
 */
@RestController
@RequestMapping("/master/product")
class ProductController {

    @Autowired
    ProductService productService

    @RequestMapping(method = [RequestMethod.POST, RequestMethod.PUT])
    Object addOrUpdate(@RequestBody Product product) {
        productService.addOrUpdate(product)
    }

    @RequestMapping(method = RequestMethod.DELETE)
    Object delete(Integer id) {
        productService.delete(id)
        ResponseEntity.ok()
    }


}

package cn.com.usth.jzp.web.master

import cn.com.usth.jzp.entity.Product
import cn.com.usth.jzp.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Created by Administrator on 2016/5/9.
 */
@RestController
@RequestMapping("/master/product")
@CrossOrigin(maxAge = 3600L)
class ProductController {

    @Autowired
    ProductService productService

    @RequestMapping(method = [RequestMethod.POST, RequestMethod.PUT])
    Object addOrUpdate(@RequestBody Product product) {
        productService.addOrUpdate(product)
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Object findOne(@PathVariable Integer id) {
        productService.productJpaRepository.findOne(id)
    }
    @RequestMapping(value = "/change/{id}", method = RequestMethod.POST)
    Object change(@PathVariable Integer id) {
        productService.changeSaleStat(id)
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    Object find(@RequestParam(required = false,defaultValue = "0")int page,
                @RequestParam(required = false,defaultValue = "5")int size) {
        productService.select(false,page,size)
    }

    @RequestMapping(method = RequestMethod.DELETE)
    Object delete(Integer id) {
        productService.delete(id)
        [success: true]
    }


}

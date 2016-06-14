package cn.com.usth.jzp.web.user

import cn.com.usth.jzp.entity.Product
import cn.com.usth.jzp.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Created by Administrator on 2016/5/9.
 */
@RestController
@RequestMapping("/user/product")
@CrossOrigin(maxAge = 3600L)
class UserProductController {

    @Autowired
    ProductService productService

    @RequestMapping(value = "/", method = RequestMethod.GET)
    Object find(@RequestParam(required = false, defaultValue = "0") int page,
                @RequestParam(required = false, defaultValue = "5") int size) {
        productService.select(true, page, size)
    }

}

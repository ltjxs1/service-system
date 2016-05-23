package cn.com.usth.jzp.web.master

import cn.com.usth.jzp.entity.Product
import cn.com.usth.jzp.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Created by Administrator on 2016/5/9.
 */
@RestController
@RequestMapping("/master")
@CrossOrigin(maxAge = 3600L)
class MasterController {

    @RequestMapping(value ="/validate",method = RequestMethod.GET)
    String validate(){
        "success"
    }

}

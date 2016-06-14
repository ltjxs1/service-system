package cn.com.usth.jzp.web.master

import cn.com.usth.jzp.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Administrator on 2016/5/9.
 */
@RestController
@RequestMapping("/master/order")
@CrossOrigin(maxAge = 3600L)
class MasterOrderController {

    @Autowired
    OrderService orderService

    @RequestMapping(value = "/", method = RequestMethod.GET)
    Object find(@RequestParam(required = false, defaultValue = "0") int page,
                @RequestParam(required = false, defaultValue = "5") int size) {
        orderService.select(page,size)
    }

    @RequestMapping(value = "/assign", method = RequestMethod.POST)
    Object assign(Integer id,String hint){
        orderService.assign(id,hint)
    }

    @RequestMapping(value = "/aftAssign", method = RequestMethod.POST)
    Object aftAssign(Integer id){
        orderService.afterAssign(id)
    }


}

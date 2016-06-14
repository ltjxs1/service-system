package cn.com.usth.jzp.web.user

import cn.com.usth.jzp.entity.Order
import cn.com.usth.jzp.entity.User
import cn.com.usth.jzp.service.OrderService
import cn.com.usth.jzp.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Created by Administrator on 2016/5/8.
 */
@CrossOrigin(maxAge = 3600L)
@RestController
@RequestMapping("/user/order")
class UserOrderController {

    @Autowired
    OrderService orderService

    @RequestMapping(method = RequestMethod.POST)
    Object deal(Integer productId, String token) {
        orderService.deal(productId, token)
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    Object find(@RequestParam(required = false, defaultValue = "0") int page,
                @RequestParam(required = false, defaultValue = "5") int size,
    String token) {
        orderService.select(page,size,token)
    }

    @RequestMapping(value="/finish",method = RequestMethod.POST)
    Object finish(Integer id, String token) {
        orderService.finService(id, token)
    }


}

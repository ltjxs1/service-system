package cn.com.usth.jzp.web

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Administrator on 2016/5/7.
 */
@RestController
class SampleSecurityController {

    @RequestMapping(value = "/manager/a", method = RequestMethod.GET)
    Object a() {
        [a: 1]
    }

    @RequestMapping(value = "/anyone", method = RequestMethod.GET)
    Object b() {
        [a: 1]
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    Object c() {
        [a: 1]
    }

    @RequestMapping(value = "/user/status", method = RequestMethod.GET)
    Object d() {
        [a: 1]
    }
}

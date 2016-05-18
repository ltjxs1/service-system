package cn.com.usth.jzp.web.master

import cn.com.usth.jzp.entity.Worker
import cn.com.usth.jzp.service.WorkerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Created by Administrator on 2016/5/8.
 */
@RestController
@RequestMapping("/master/worker")
@CrossOrigin(maxAge = 3600L)
class WorkerController {

    @Autowired
    WorkerService workerService

    @RequestMapping(method = [RequestMethod.POST, RequestMethod.PUT])
    Object addOrUpdate(@RequestBody Worker worker) {
        workerService.addOrUpdate(worker)
    }

    @RequestMapping(method = RequestMethod.DELETE)
    Object delete(Integer id) {
        workerService.delete(id)
        [success: true]
    }

}

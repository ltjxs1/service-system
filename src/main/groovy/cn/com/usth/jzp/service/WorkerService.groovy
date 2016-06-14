package cn.com.usth.jzp.service

import cn.com.usth.jzp.entity.Worker
import cn.com.usth.jzp.entity.jpa.WorkerJpaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

/**
 * Created by Administrator on 2016/5/8.
 */
@Component
class WorkerService {

    @Autowired
    WorkerJpaRepository workerJpaRepository

    Worker addOrUpdate(Worker worker) {
        if (worker?.id != null) {
            worker = flush(worker)
        }
        workerJpaRepository.save(worker)
    }

    void delete(Integer id) {
        workerJpaRepository.delete(id)
    }

    Worker findByHint(String hint) {
        Integer id = hint.isInteger() ? Integer.parseInt(hint) : null
        if (id) {
            return workerJpaRepository.findOne(id)
        } else {
            List<Worker> list = workerJpaRepository.findByName(hint)
            if (list?.size() == 1) {
                return list[0]
            } else {
                return null
            }
        }
    }

    Page<Worker> select(int page, int size) {
        Pageable pageable = new PageRequest(page, size)
        workerJpaRepository.findAll(pageable)
    }

    Worker flush(Worker worker) {
        if (worker?.id == null) {
            return worker
        }
        Worker worker1 = workerJpaRepository.findOne(worker.id)
        worker1.birthday = worker.birthday
        worker1.name = worker.name
        worker1.telephone = worker.telephone
        worker1.wechat = worker.wechat
        worker1
    }

}

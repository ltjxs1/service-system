package cn.com.usth.jzp.service

import cn.com.usth.jzp.entity.Order
import cn.com.usth.jzp.entity.OrderMessage
import cn.com.usth.jzp.entity.Product
import cn.com.usth.jzp.entity.User
import cn.com.usth.jzp.entity.Worker
import cn.com.usth.jzp.entity.jpa.OrderJpaRepository
import cn.com.usth.jzp.entity.jpa.OrderMessageJpaRepository
import cn.com.usth.jzp.entity.jpa.ProductJpaRepository
import cn.com.usth.jzp.entity.jpa.UserJpaRepository
import cn.com.usth.jzp.entity.jpa.WorkerJpaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

/**
 * Created by Administrator on 2016/5/18.
 */
@Component
class OrderService {

    @Autowired
    OrderJpaRepository orderJpaRepository

    @Autowired
    UserJpaRepository userJpaRepository

    @Autowired
    WorkerJpaRepository workerJpaRepository

    @Autowired
    ProductJpaRepository productJpaRepository

    @Autowired
    OrderMessageJpaRepository orderMessageJpaRepository

    @Autowired
    WorkerService workerService

    Order flush(Order order) {
        if (order?.id == null) {
            return null
        }
        Order o = orderJpaRepository.findOne(order.id)
        o.updateAt = order.updateAt
        o.status = order.status
        o
    }

    Page<Order> select(int page, int size) {
        Pageable pageable = new PageRequest(page, size)
        orderJpaRepository.findAll(pageable)
    }

    Order deal(Integer productId, String token) {
        User u = userJpaRepository.findTop1ByToken(token)
        Product p = productJpaRepository.findOne(productId)
        Order order = new Order()
        order.product = p
        p.orders << order
        order.user = u
        u.orders << order
        orderJpaRepository.save(order)
        order = orderJpaRepository.findOne(order.id)
        addMessage(order, "订单建立成功")
        order.status = "待指派"
        orderJpaRepository.save(order)
    }

    Order assign(Integer orderId, String workerHint) {
        Order order = orderJpaRepository.findOne(orderId)
        Worker worker = workerService.findByHint(workerHint)
        if(!worker){
            throw new RuntimeException("员工不存在")
        }
        order.worker = worker
        worker.orders << order
        addMessage(order, "指派员工成功，员工姓名：$worker.name 联系方式： $worker.telephone ")
        order.status = "已指派"
        orderJpaRepository.save(order)
    }

    Order afterAssign(Integer orderId) {
        Order order = orderJpaRepository.findOne(orderId)
        addMessage(order, "员工服务完毕")
        order.status = "服务完成"
        orderJpaRepository.save(order)
    }

    Order finService(Integer orderId) {
        Order order = orderJpaRepository.findOne(orderId)
        order.status = "已完成"
        addMessage(order, "本次服务完成，欢迎下次再来！")
    }

    Order revalulate(Integer orderId, String message) {
        Order order = orderJpaRepository.findOne(orderId)
        addMessage(order, message)
        orderJpaRepository.save(order)
    }

    Order evalulate(Integer orderId, String message) {
        Order order = orderJpaRepository.findOne(orderId)
        addMessage(order, "反馈信息：$message ")
        order.status="待处理反馈"
        orderJpaRepository.save(order)
    }

    private Order addMessage(Order order, String message) {
        OrderMessage orderMessage = new OrderMessage()
        orderMessage.message = message
        orderMessage.order = order
        order.orderMessages << orderMessage
        orderMessageJpaRepository.save(orderMessage)
        order
    }

}

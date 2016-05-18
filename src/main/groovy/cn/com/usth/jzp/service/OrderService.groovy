package cn.com.usth.jzp.service

import cn.com.usth.jzp.entity.Order
import cn.com.usth.jzp.entity.OrderMessage
import cn.com.usth.jzp.entity.User
import cn.com.usth.jzp.entity.Worker
import cn.com.usth.jzp.entity.jpa.OrderJpaRepository
import cn.com.usth.jzp.entity.jpa.UserJpaRepository
import cn.com.usth.jzp.entity.jpa.WorkerJpaRepository
import org.springframework.beans.factory.annotation.Autowired
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

    Order flush(Order order) {
        if (order?.id == null) {
            return null
        }
        Order o = orderJpaRepository.findOne(order.id)
        o.createAt = order.createAt
        o.dealPrice = order.dealPrice
        o.status = order.status
        o
    }

    Order deal(Order order, Integer userId) {
        User u = userJpaRepository.findOne(userId)
        addMessage(order, "订单建立成功")
        order.status = "待指派"
        orderJpaRepository.save(order)
    }

    Order assign(Integer orderId, Integer workerId) {
        Order order = orderJpaRepository.findOne(orderId)
        Worker worker = workerJpaRepository.findOne(workerId)
        order.worker = worker
        worker.orders << order
        addMessage(order, "指派员工成功，员工姓名：$worker.name 联系方式： $worker.telephone ")
        order.status = "已指派"
        orderJpaRepository.save(order)
    }

    Order finService(Integer orderId){
        Order order = orderJpaRepository.findOne(orderId)
        addMessage(order,"本次服务结束，请您进行评价")
    }

    Order addTip(Integer orderId, String message) {
        Order order = orderJpaRepository.findOne(orderId)
        addMessage(order,message)
        orderJpaRepository.save(order)
    }

    Order userEvalulate(Integer orderId, String message,Integer level) {
        Order order = orderJpaRepository.findOne(orderId)
        addMessage(order,"评价完成，星级为：$level 评价信息：$message ")
        orderJpaRepository.save(order)
    }

    private Order addMessage(Order order, String message, Boolean isUsual = true) {
        OrderMessage orderMessage = new OrderMessage()
        orderMessage.message = message
        orderMessage.order = order
        orderMessage.isUsual = isUsual
        order.orderMessages << orderMessage
        order
    }

}

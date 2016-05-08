package cn.com.usth.jzp.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import javax.persistence.*

/**
 * Created by Administrator on 2016/5/3.
 */
@Entity
@Table(name = "worker")
@JsonIgnoreProperties(["hibernateLazyInitializer", "handler"])
class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id
    String name
    String birthday
    String wechat
    String telephone
    @Column(name = "create_at")
    Date createAt
}

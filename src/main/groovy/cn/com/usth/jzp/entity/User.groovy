package cn.com.usth.jzp.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

/**
 * Created by Administrator on 2016/5/3.
 */
@Entity
@Table(name = "user")
@JsonIgnoreProperties(["hibernateLazyInitializer", "handler"])
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id
    String token
    String loginname
    String password
    String nickname
    String gender
    String birthday
    String telephone
    String wechat
    @Column(name = "create_at",columnDefinition="DEFAULT CURRENT_TIMESTAMP")
    Date createAt
}
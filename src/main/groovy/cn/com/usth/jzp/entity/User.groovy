package cn.com.usth.jzp.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import javax.persistence.*
import java.sql.Timestamp

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
    @Column(name = "create_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    Timestamp createAt
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    List<Order> orders = []
}

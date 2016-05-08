package cn.com.usth.jzp.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import javax.persistence.*

/**
 * Created by Administrator on 2016/5/3.
 */
@Entity
@Table(name = "product")
@JsonIgnoreProperties(["hibernateLazyInitializer", "handler"])
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id
    String name
    String price
    String description
    @Column(name = "create_at", columnDefinition = "DEFAULT CURRENT_TIMESTAMP")
    Date createAt

}

package cn.com.usth.jzp.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

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

}

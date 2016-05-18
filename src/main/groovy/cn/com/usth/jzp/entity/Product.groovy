package cn.com.usth.jzp.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import javax.persistence.*
import java.sql.Timestamp

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
    Double price
    String description

    @Column(name = "on_sale", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    Boolean onSale
//    @Column(name = "image_name")
//    String imageName
    @Column(name = "create_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    Timestamp createAt

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    List<Order> orders = []


}

package cn.com.usth.jzp.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import javax.persistence.*
import java.sql.Timestamp

/**
 * Created by Administrator on 2016/5/3.
 */
@Entity
@Table(name = "orders_message")
@JsonIgnoreProperties(["hibernateLazyInitializer", "handler"])
class OrderMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id
    @Column(name = "create_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    Timestamp createAt

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    Order order

    String message
}

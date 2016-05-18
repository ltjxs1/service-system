package cn.com.usth.jzp.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import javax.persistence.*
import javax.validation.constraints.NotNull
import java.sql.Timestamp

/**
 * Created by Administrator on 2016/5/3.
 */
@Entity
@Table(name = "orders")
@JsonIgnoreProperties(["hibernateLazyInitializer", "handler"])
class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id
    @Column(name = "create_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    Timestamp createAt

    @ManyToOne
    @JoinColumn(name = "worker_id")
    Worker worker

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", columnDefinition = "INT NOT NULL")
    User user

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product

    String status
    Double dealPrice

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    List<OrderMessage> orderMessages = []

}

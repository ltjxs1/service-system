package cn.com.usth.jzp.entity.jpa

import cn.com.usth.jzp.entity.Worker
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by Administrator on 2016/5/5.
 */

@Repository
public interface WorkerJpaRepository extends JpaRepository<Worker, Integer> {

    List<Worker> findByNameLike(String name)
}

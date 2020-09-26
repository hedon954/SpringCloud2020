package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Hedon Wang
 * @create 2020-04-22 21:12
 */

//推荐用Mybatis的@Mapper，而不用Spring的@Repository
@Mapper
public interface PaymentDao {

    /**
     * 写操作
     * @param payment
     * @return
     */
    public int create(Payment payment);


    /**
     * 读操作
     * @param id
     * @return
     */
    public Payment getPaymentById(@Param("id") Long id);
}

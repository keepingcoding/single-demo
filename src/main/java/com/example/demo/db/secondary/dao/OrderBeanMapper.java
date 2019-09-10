package com.example.demo.db.secondary.dao;

import com.example.demo.db.secondary.model.OrderBean;
import com.example.demo.db.secondary.model.example.OrderBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderBeanMapper {
    long countByExample(OrderBeanExample example);

    int deleteByExample(OrderBeanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderBean record);

    int insertSelective(OrderBean record);

    List<OrderBean> selectByExample(OrderBeanExample example);

    OrderBean selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderBean record, @Param("example") OrderBeanExample example);

    int updateByExample(@Param("record") OrderBean record, @Param("example") OrderBeanExample example);

    int updateByPrimaryKeySelective(OrderBean record);

    int updateByPrimaryKey(OrderBean record);
}
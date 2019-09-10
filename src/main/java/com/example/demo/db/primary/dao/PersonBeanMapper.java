package com.example.demo.db.primary.dao;

import com.example.demo.db.primary.model.PersonBean;
import com.example.demo.db.primary.model.example.PersonBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PersonBeanMapper {
    long countByExample(PersonBeanExample example);

    int deleteByExample(PersonBeanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PersonBean record);

    int insertSelective(PersonBean record);

    List<PersonBean> selectByExample(PersonBeanExample example);

    PersonBean selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PersonBean record, @Param("example") PersonBeanExample example);

    int updateByExample(@Param("record") PersonBean record, @Param("example") PersonBeanExample example);

    int updateByPrimaryKeySelective(PersonBean record);

    int updateByPrimaryKey(PersonBean record);
}
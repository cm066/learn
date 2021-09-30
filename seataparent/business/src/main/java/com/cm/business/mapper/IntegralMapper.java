package com.cm.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cm.business.entity.Integrals;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegralMapper extends BaseMapper<Integrals> {

    /**
     * mybatis的流式处理方法
     * @param limit
     * @return
     */
    @Select("select * from integrals limit #{limit}")
    Cursor<Integrals> scan(@Param("limit") int limit);
}

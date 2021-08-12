package com.cm.storage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cm.storage.entity.Ticket;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketMapper extends BaseMapper<Ticket> {

    boolean toReduceSurplus(@Param("id") Long userId, @Param("frozens") Integer frozens);
    boolean addUse(@Param("id") Long userId, @Param("frozens") Integer frozens);
    boolean addSur(@Param("id") Long userId, @Param("frozens") Integer frozens);
}

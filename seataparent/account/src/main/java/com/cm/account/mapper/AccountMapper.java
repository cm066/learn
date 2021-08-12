package com.cm.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cm.account.entity.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper extends BaseMapper<Account> {
    boolean reduceAccount(@Param("userId") Long userId, @Param("frozens") Integer frozens);
    boolean addAccount(@Param("userId") Long userId, @Param("frozens") Integer frozens);
    boolean reduceFrozens(@Param("userId") Long userId, @Param("frozens") Integer frozens);
}

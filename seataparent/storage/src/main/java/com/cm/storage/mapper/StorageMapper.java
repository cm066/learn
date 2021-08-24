package com.cm.storage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cm.storage.entity.Storage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageMapper extends BaseMapper<Storage> {

    int reduceStorage(Long productId, Integer use);

    @Update("UPDATE STORAGE SET total=total-#{currentUse},used=used+#{currentUse} WHERE id=#{productId}")
    int updateUsed(@Param("productId") long productId, @Param("currentUse") int currentUse);
}

package com.lufei.producer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lufei.producer.bean.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper extends BaseMapper<Product> {
}

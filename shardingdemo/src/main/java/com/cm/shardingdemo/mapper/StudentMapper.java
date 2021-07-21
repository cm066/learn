package com.cm.shardingdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cm.shardingdemo.entity.Student;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentMapper extends BaseMapper<Student> {
}

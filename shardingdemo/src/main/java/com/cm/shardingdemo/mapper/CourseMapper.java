package com.cm.shardingdemo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cm.shardingdemo.entity.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMapper extends BaseMapper<Course> {

}

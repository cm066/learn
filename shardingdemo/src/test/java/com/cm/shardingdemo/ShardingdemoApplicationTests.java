package com.cm.shardingdemo;

//import com.cm.shardingdemo.entity.Course;
//import com.cm.shardingdemo.mapper.CourseMapper;

import com.cm.shardingdemo.entity.Course;
import com.cm.shardingdemo.mapper.CourseMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ShardingdemoApplicationTests {

    //注入mapper
    @Autowired
    private CourseMapper courseMapper;

//    @Autowired
//    StringRedisTemplate stringRedisTemplate;


    //=======================测试水平分表===================
    //添加课程的方法
    @Test
    public void test1() {
        Course course = new Course();
        course.setCname("java");
        course.setUserId(100L);
        course.setCstatus("normal");
        courseMapper.insert(course);
//        stringRedisTemplate.boundValueOps("ll").setIfAbsent("11");
//        boolean
    }

    @Test
    public void addCourse() {
//        for(int i=1;i<=10;i++) {
//            Course course = new Course();
//            course.setCname("java"+i);
//            course.setUserId(100L);
//            course.setCstatus("Normal"+i);
//            courseMapper.insert(course);
//        }
    }
}

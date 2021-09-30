package com.cm.business.controller;

import com.cm.business.entity.Integrals;
import com.cm.business.mapper.IntegralMapper;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cursor")
public class CursorController {

    @Autowired
    IntegralMapper integralMapper;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    /**
     * 但是这样是有问题的，因为这样查询一次以后就会把数据库链接给关闭了，所以就不行，要一直
     *
     * 在取数据的过程中需要保持数据库连接，而 Mapper 方法通常在执行完后连接就关闭了，因此 Cusor 也一并关闭了。
     * Cursor 是可关闭的；
     * Cursor 是可遍历的。
     * 除此之外，Cursor 还提供了三个方法：
     * isOpen()：用于在取数据之前判断 Cursor 对象是否是打开状态。只有当打开时 Cursor 才能取数据；
     * isConsumed()：用于判断查询结果是否全部取完。
     * getCurrentIndex()：返回已经获取了多少条数据
     * @param limit
     * @throws Exception
     */
    @GetMapping("foo/scan/0/{limit}")
    public void scanFoo0(@PathVariable("limit") int limit) throws Exception {
        try (Cursor<Integrals> cursor = integralMapper.scan(limit)) {  // 1
            cursor.forEach(foo -> {});                      // 2
        }
    }

    /**
     * 针对以上的问题的解放方法
     * 自己获取链接，然后等用完的时候在释放连接
     */
    @GetMapping("foo/scan/1/{limit}")
    public void scanFoo1(@PathVariable("limit") int limit) throws Exception {
        try (SqlSession sqlSession = sqlSessionFactory.openSession();
                Cursor<Integrals> cursor = sqlSession.getMapper(IntegralMapper.class).scan(limit)) {
            cursor.forEach(foo -> { });
        }
//        try {
//            SqlSession sqlSession = sqlSessionFactory.openSession();
//            Cursor<Integrals> cursor = sqlSession.getMapper(IntegralMapper.class).scan(limit);
//            cursor.forEach(foo1 -> {});
//            sqlSession.close();
//        }
    }
}

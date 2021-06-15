package com.sour.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sour.mybatisplus.entity.User;
import com.sour.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void testInsertUser() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName(String.valueOf(UUID.randomUUID()));
            user.setAge(100);
            user.setEmail(UUID.randomUUID() + "@gh.comg");
            userMapper.insert(user);
        }
    }

    /**
     * 测试乐观锁 (成功)
     *
     * @author xgl
     * @date 2021/6/13 15:08
     **/
    @Test
    void optimisticLocker() {
        User user = userMapper.selectById(1);
        user.setAge(44);
        userMapper.updateById(user);
    }


    /**
     * 测试乐观锁 (多线程下的失败)
     *
     * @author xgl
     * @date 2021/6/13 15:08
     **/
    @Test
    void optimisticLockerFail() {
        // 线程1
        User user = userMapper.selectById(1);
        user.setAge(44);

        // 线程2
        User user2 = userMapper.selectById(1);
        user2.setAge(33);

        // 插队更新 (插入成功, 插入后改变了版本号, 所以后面更新不成功)
        userMapper.updateById(user2);

        // 插队更新 (插入不成功)
        userMapper.updateById(user);
    }


    @Test
    void select() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    @Test
    void select1() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Jone");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }


    /**
     * 分页查找
     *
     * @author xgl
     * @date 2021/6/13 17:24
     **/
    @Test
    void page() {
        Page<User> userPage = new Page<>(2 , 2);
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.ge("age", 10);
        Page page = userMapper.selectPage(userPage, wrapper);
        List records = page.getRecords();
        records.forEach(System.out::println);
    }

    /**
     * 删除
     *
     * @author xgl
     * @date 2021/6/13 17:32
     **/
    @Test
    void delete() {
        userMapper.delete(
                new QueryWrapper<User>().eq("age", 100)
        );
    }
}

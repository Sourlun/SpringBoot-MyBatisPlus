package com.sour.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sour.mybatisplus.entity.User;
import org.springframework.stereotype.Repository;

/**
 *  在对应的Mapper继承基本的接口
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}

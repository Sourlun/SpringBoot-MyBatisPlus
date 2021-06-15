package com.sour.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     *  主键设置自增
     */
    @TableId(type = IdType.AUTO)
    public Long id;
    public String name;
    public Integer age;
    public String email;

    /**
     * 自动填充策略
     */
    @TableField(fill = FieldFill.INSERT)
    public Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    public Date updateTime;

    /**
     * 乐观锁
     * @Version 乐观锁注解
     *  1, 注册组件 MyBatisPlusConfig
     */
    @Version
    public Integer version;

    /**
     * 逻辑删除
     */
    @TableLogic
    public Integer deleted;
}

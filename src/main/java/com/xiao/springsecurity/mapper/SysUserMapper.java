package com.xiao.springsecurity.mapper;

import com.xiao.springsecurity.entity.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysUserMapper extends Mapper {
    @Select("select * from sys_user where username = #{username}")
    @Results({
            @Result(id=true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
            many = @Many(select = "com.xiao.springsecurity.mapper.SysRoleMapper.findRolesByUid"))
    })
    SysUser findUserByName(String username);
}

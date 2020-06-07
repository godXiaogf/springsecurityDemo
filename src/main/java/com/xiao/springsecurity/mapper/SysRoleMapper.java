package com.xiao.springsecurity.mapper;

import com.xiao.springsecurity.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysRoleMapper extends Mapper {
    @Select("select sr.id, sr.role_name roleName, sr.role_desc roleDesc " +
            "from sys_user_role sur, sys_role sr " +
            "where sur.rid=sr.id and sur.uid = #{uid}")
    List<SysRole> findRolesByUid(Integer uid);
}

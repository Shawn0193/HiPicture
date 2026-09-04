package com.baidu.hipicture.module.mapper;

import com.baidu.hipicture.module.entity.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.math.BigInteger;
import java.util.List;

@Mapper
public interface PictureMapper {

    @Select("select * from picture where id = #{id} and is_deleted = 0")
    Picture getById(@Param("id") Long id);

    @Select("select * from picture where is_deleted = 0")
    List<Picture> getList();

    int update(@Param("picture") Picture picture);

    int insert(@Param("picture") Picture picture);

    @Update("update picture set is_deleted=1, update_time=#{time} where id=#{id} limit 1")
    int delete(@Param("id") Long id, @Param("time") Integer time);
}
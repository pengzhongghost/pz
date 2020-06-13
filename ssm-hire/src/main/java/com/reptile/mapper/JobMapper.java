package com.reptile.mapper;

import com.reptile.pojo.Job;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface JobMapper {
    //@Insert("insert into job values(#{id},#{jobName},#{companyName},#{money},#{location});")
    int insert(Job job);
    //@Select("select * from job limit #{param1},#{param2}")
    List<Job> select(int startRow,int endRow);
    //@Select("select count(*) from job;")
    int selectCount();
    //@Select("select * from job;")
    List<Job> selectAll();
}

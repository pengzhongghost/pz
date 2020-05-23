package com.reptile.mapper;

import com.reptile.pojo.Job;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface JobMapper {

    int insert(Job job);

    List<Job> select(int startRow,int endRow);

    int selectCount();

    List<Job> selectAll();
}

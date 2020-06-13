package com.reptile.service;

import com.reptile.pojo.Job;
import com.reptile.util.PageBean;

import java.util.List;

public interface JobService {

    PageBean<Job> select(int index);

    void insert();

    List<Job> selectAll();

}

package com.reptile.service.impl;

import com.reptile.mapper.JobMapper;
import com.reptile.pojo.Job;
import com.reptile.search.LagouSearch;
import com.reptile.search.LiepinSearch;
import com.reptile.service.JobService;
import com.reptile.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobMapper jobMapper;
    @Override
    public PageBean<Job> select(int index) {
        PageBean<Job> pageBean=new PageBean<>();

        pageBean.setIndex(index);

        pageBean.setTotalCount(jobMapper.selectCount());
        int startRow = pageBean.getStartRow();
        int sendRow = pageBean.getEndRow();
        int endRow=sendRow-startRow;

        List<Job> jobList = jobMapper.select(startRow,endRow);
        pageBean.setList(jobList);

        return pageBean;
    }

    @Transactional
    public void insert(){
        List<Job> jobs = LagouSearch.getJobs();
        for (Job job : jobs) {
            jobMapper.insert(job);
        }
        List<Job> jobList = LiepinSearch.getJobs();
        for (Job job : jobList) {
            jobMapper.insert(job);
        }

    }

    @Override
    public List<Job> selectAll() {
        return jobMapper.selectAll();
    }


}

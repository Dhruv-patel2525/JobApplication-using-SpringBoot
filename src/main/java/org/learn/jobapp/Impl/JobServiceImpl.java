package org.learn.jobapp.Impl;

import org.learn.jobapp.jobs.Job;
import org.learn.jobapp.jobs.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class JobServiceImpl implements JobService {
    public List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;



    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId);
        jobs.add(job);
        nextId++;
    }

    @Override
    public Job getJobById(Long id)
    {
        for(Job job:jobs)
        {
            if(job.getId().equals(id))
            {
                return job;
            }
        }
        return null;
    }
}

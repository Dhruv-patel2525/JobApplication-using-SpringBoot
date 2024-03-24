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

    @Override
    public boolean deleteJobById(Long id) {
        for(Job job:jobs)
        {
            if(job.getId().equals(id))
            {
                jobs.remove(job);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateJobById(Long id, Job updatedJob) {
        for(Job job:jobs)
        {
            if(job.getId().equals(id))
            {
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                return true;
            }
        }
        return false;
    }
}

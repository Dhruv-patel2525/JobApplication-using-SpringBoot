package org.learn.jobapp.Impl;

import org.learn.jobapp.jobs.Job;
import org.learn.jobapp.jobs.JobRepository;
import org.learn.jobapp.jobs.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }
    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }
    @Override
    public Job getJobById(Long id)
    {
        return jobRepository.findById(id).orElse(null);
    }
    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }
    @Override
    public boolean updateJobById(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent())
        {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}

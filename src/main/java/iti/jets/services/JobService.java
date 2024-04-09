package iti.jets.services;

import iti.jets.daos.JobDao;
import iti.jets.dtos.JobDto;

import iti.jets.entities.Job;
import jakarta.persistence.EntityManager;
import iti.jets.configuration.EntityManagerFactoryProvider;
import jakarta.persistence.EntityTransaction;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class JobService {
    private final EntityManager em = EntityManagerFactoryProvider.getEMF().createEntityManager();
    public boolean createJob(JobDto jobDto) {
        JobDao jobDao = JobDao.getInstance();
        Job job = new Job();

        EntityTransaction transaction = em.getTransaction();

        populateJob(job, jobDto);
        System.out.println(job);

        try {
            transaction.begin();
            jobDao.create(em, job);
            System.out.println(job);
            transaction.commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                System.out.println("Rolling back");
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    public JobDto findJob(int id){
        JobDao jobDao = JobDao.getInstance();
        return jobDao.findOneById(em, id).get().toDto();
    }

    private void populateJob(Job job, JobDto jobDto) {
        Optional.ofNullable(jobDto.getId()).ifPresent(job::setId);
        Optional.ofNullable(jobDto.getName()).ifPresent(job::setName);
        Optional.ofNullable(jobDto.getJobDescription()).ifPresent(job::setJobDescription);
        Optional.ofNullable(jobDto.getMinSalary()).ifPresent(job::setMinSalary);
        Optional.ofNullable(jobDto.getMaxSalary()).ifPresent(job::setMaxSalary);
    }


    public List<JobDto> findAllJobs() {
        JobDao jobDao = JobDao.getInstance();
        List<Job> jobs = jobDao.findAll(em);
        return Job.toDtoList(jobs);
    }

    public boolean updateJob(JobDto jobDto) {
        JobDao jobDao = JobDao.getInstance();
        Job job = new Job();
        populateJob(job, jobDto);
        try {
            em.getTransaction().begin();
            jobDao.update(em, job);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    public boolean deleteJob(int id) {
        JobDao jobDao = JobDao.getInstance();
        try {
            em.getTransaction().begin();
            if(!jobDao.deleteById(em, id)) return false;
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    public boolean deleteAllJobs() {
        JobDao jobDao = JobDao.getInstance();
        try {
            em.getTransaction().begin();
            jobDao.deleteAll(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
        return true;
    }
}

package iti.jets.daos;

import iti.jets.entities.Job;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class JobDao extends AbstractCRUDDao<Job>{
    private static final JobDao INSTANCE = new JobDao();
    protected JobDao() {
        super(Job.class);
    }
    public static JobDao getInstance() {
        return INSTANCE;
    }

    // get job name by id
    public Optional<String> getJobName(EntityManager entityManager, Integer jobId) {
        return Optional.ofNullable(entityManager.createQuery("SELECT j.name FROM Job j WHERE j.id = :jobId", String.class)
                .setParameter("jobId", jobId)
                .getSingleResult());
    }

    // get job description by job name method which is case insensitivity
    public Optional<String> getJobDescription(EntityManager entityManager, String jobName) {
        return Optional.ofNullable(entityManager.createQuery("SELECT j.jobDescription FROM Job j WHERE LOWER(j.name) = :jobName", String.class)
                .setParameter("jobName", jobName.toLowerCase())
                .getSingleResult());
    }

    // get min salary by job id
    public Optional<Double> getMinSalary(EntityManager entityManager, Integer jobId) {
        return Optional.ofNullable(entityManager.createQuery("SELECT j.minSalary FROM Job j WHERE j.id = :jobId", Double.class)
                .setParameter("jobId", jobId)
                .getSingleResult());
    }

    // get max salary by job id
    public Optional<Double> getMaxSalary(EntityManager entityManager, Integer jobId) {
        return Optional.ofNullable(entityManager.createQuery("SELECT j.maxSalary FROM Job j WHERE j.id = :jobId", Double.class)
                .setParameter("jobId", jobId)
                .getSingleResult());
    }
}

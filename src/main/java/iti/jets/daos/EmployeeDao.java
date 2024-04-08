package iti.jets.daos;

import iti.jets.entities.Employee;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class EmployeeDao extends AbstractCRUDDao<Employee>{
    private static final EmployeeDao INSTANCE = new EmployeeDao();

    protected EmployeeDao() {
        super(Employee.class);
    }

    public static EmployeeDao getInstance() {
        return INSTANCE;
    }

    // Find all Employees by first and last name so if u dont have the id
    public List<Employee> findByName (EntityManager entityManager, String firstName, String LastName) {
        return entityManager.createQuery("SELECT e FROM Employee e WHERE e.firstName = :firstName AND e.lastName = :lastName", Employee.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", LastName)
                .getResultList();
    }

    // get salary of an employee by his ID and if the ID does not exist will return an empty optional
    public Optional<Double> getSalaryByEmployeeID(EntityManager entityManager, int employeeID) {
        List<Double> resultList = entityManager.createQuery("SELECT e.salary FROM Employee e WHERE e.id = :employeeID", Double.class)
                .setParameter("employeeID", employeeID)
                .getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    // get an employee job name and description by his ID and if the ID does not exist will return an empty optional
    public Optional<String> getJobTitleByEmployeeID(EntityManager entityManager, int employeeID) {
        return Optional.ofNullable(entityManager.createQuery("SELECT CONCAT(e.job.name, ': ', e.job.jobDescription) FROM Employee e WHERE e.id = :employeeID", String.class)
                .setParameter("employeeID", employeeID)
                .getSingleResult());
    }
}

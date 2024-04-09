package iti.jets.services;

import iti.jets.configuration.EntityManagerFactoryProvider;
import iti.jets.daos.DepartmentDao;
import iti.jets.daos.EmployeeDao;
import iti.jets.daos.JobDao;
import iti.jets.dtos.EmployeeDto;
import iti.jets.entities.Employee;
import iti.jets.entities.Job;
import jakarta.persistence.EntityManager;

import java.util.List;


public class EmployeeService {
    private final EntityManager em = EntityManagerFactoryProvider.getEMF().createEntityManager();
    public Boolean createEmployee(EmployeeDto employeeDto){
        EmployeeDao employeeDao = EmployeeDao.getInstance();
        Employee employee = new Employee();

        populateEmployee(employee, employeeDto);

        try {
            em.getTransaction().begin();
            employeeDao.create(em,employee);
            System.out.println(employee);
            em.getTransaction().commit();
        }catch (Exception e){
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }finally {
            em.close();
        }
        return true;
    }

    private void populateEmployee(Employee employee, EmployeeDto employeeDto) {
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setSalary(employeeDto.getSalary());

        JobDao.getInstance().findOneById(em, employeeDto.getJobId()).ifPresent(employee::setJob);

        employee.setAge(employeeDto.getAge());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setBirthdate(employeeDto.getBirthdate());
        employee.setHireDate(employeeDto.getHireDate());
        employee.setVacations(employeeDto.getVacations());

        DepartmentDao.getInstance().findOneById(em, employeeDto.getDepartmentID()).ifPresent(employee::setDepartment);
        EmployeeDao.getInstance().findOneById(em, employeeDto.getManagerID()).ifPresent(employee::setManager);

        employee.setDeduction(employeeDto.getDeduction());
        employee.setBonus(employeeDto.getBonus());

    }

    public Employee findEmployee(int id) {
        EmployeeDao employeeDao = EmployeeDao.getInstance();
        return employeeDao.findOneById(em, id).orElse(null);
    }

    public List<Employee> findAllEmployees() {
        EmployeeDao employeeDao = EmployeeDao.getInstance();
        return employeeDao.findAll(em);
    }


    public boolean updateEmployee(EmployeeDto employeeDto) {
        EmployeeDao employeeDao = EmployeeDao.getInstance();
        Employee employee = new Employee();
        populateEmployee(employee, employeeDto);
        try {
            em.getTransaction().begin();
            employeeDao.update(em, employee);
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

    public boolean deleteEmployee(int id) {
        EmployeeDao employeeDao = EmployeeDao.getInstance();
        try {
            em.getTransaction().begin();
            if(!employeeDao.deleteById(em, id)) return false;
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

    public boolean deleteAllEmployees() {
        EmployeeDao employeeDao = EmployeeDao.getInstance();
        try {
            em.getTransaction().begin();
            employeeDao.deleteAll(em);
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

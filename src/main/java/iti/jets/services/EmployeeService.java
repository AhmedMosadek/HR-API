package iti.jets.services;

import iti.jets.configuration.EntityManagerFactoryProvider;
import iti.jets.daos.DepartmentDao;
import iti.jets.daos.EmployeeDao;
import iti.jets.daos.JobDao;
import iti.jets.dtos.EmployeeDto;
import iti.jets.entities.Employee;
import iti.jets.entities.Job;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.Null;

import java.util.List;
import java.util.Optional;


public class EmployeeService {
    private final EntityManager em = EntityManagerFactoryProvider.getEMF().createEntityManager();
    public Boolean createEmployee(EmployeeDto employeeDto){
        EmployeeDao employeeDao = EmployeeDao.getInstance();
        Employee employee = new Employee();

        populateEmployee(employee, employeeDto);

        try {
            em.getTransaction().begin();
            employeeDao.create(em,employee);
//            System.out.println(employee);
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
//        Optional.of(employeeDto.getId()).ifPresent(employee::setId);
        Optional.ofNullable(employeeDto.getFirstName()).ifPresent(employee::setFirstName);
        Optional.ofNullable(employeeDto.getLastName()).ifPresent(employee::setLastName);
        Optional.ofNullable(employeeDto.getSalary()).ifPresent(employee::setSalary);
        Optional.of(employeeDto.getJobID()).flatMap(jobId -> JobDao.getInstance().findOneById(em, jobId)).ifPresent(employee::setJob);
        Optional.of(employeeDto.getAge()).ifPresent(employee::setAge);
        Optional.ofNullable(employeeDto.getPhoneNumber()).ifPresent(employee::setPhoneNumber);
        Optional.ofNullable(employeeDto.getBirthdate()).ifPresent(employee::setBirthdate);
        Optional.ofNullable(employeeDto.getHireDate()).ifPresent(employee::setHireDate);
        Optional.of(employeeDto.getVacations()).ifPresent(employee::setVacations);
        Optional.of(employeeDto.getDepartmentID()).flatMap(departmentID -> DepartmentDao.getInstance().findOneById(em, departmentID)).ifPresent(employee::setDepartment);
        Optional.of(employeeDto.getManagerID()).flatMap(managerID -> EmployeeDao.getInstance().findOneById(em, managerID)).ifPresent(employee::setManager);
        Optional.ofNullable(employeeDto.getDeduction()).ifPresent(employee::setDeduction);
        Optional.ofNullable(employeeDto.getBonus()).ifPresent(employee::setBonus);
    }

    public EmployeeDto findEmployee(int id) {
        EmployeeDao employeeDao = EmployeeDao.getInstance();
        return employeeDao.findOneById(em, id).get().toDto();
    }

    public List<EmployeeDto> findAllEmployees() {
        EmployeeDao employeeDao = EmployeeDao.getInstance();
        List<Employee> employees = employeeDao.findAll(em);
        return Employee.toDtoList(employees);
    }


    public boolean updateEmployee(EmployeeDto employeeDto, int id) {
        EmployeeDao employeeDao = EmployeeDao.getInstance();
        Employee employee = null;
        employeeDto.setId(id);


        try {
            em.getTransaction().begin();
            employee = employeeDao.findOneById(em, id).orElseGet(()->null);
            populateEmployee(employee, employeeDto);

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

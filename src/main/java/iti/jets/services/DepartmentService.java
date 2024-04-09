package iti.jets.services;

import iti.jets.daos.DepartmentDao;
import iti.jets.daos.EmployeeDao;
import iti.jets.dtos.DepartmentDto;
import iti.jets.dtos.EmployeeDto;
import iti.jets.entities.Department;
import iti.jets.entities.Employee;
import jakarta.persistence.EntityManager;
import iti.jets.configuration.EntityManagerFactoryProvider;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Set;

public class DepartmentService {
    private final EntityManager em = EntityManagerFactoryProvider.getEMF().createEntityManager();

    public boolean createDepartment(DepartmentDto departmentDto) {
        DepartmentDao departmentDao = DepartmentDao.getInstance();
        Department department = new Department();

        EntityTransaction transaction = em.getTransaction();

        populateDepartment(department, departmentDto);

        try {
            transaction.begin();
            departmentDao.create(em, department);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    private void populateDepartment(Department department, DepartmentDto departmentDto) {
        department.setName(departmentDto.getName());
        EmployeeDao.getInstance().findOneById(em, departmentDto.getManagerID()).ifPresent(department::setManager);
    }

    public DepartmentDto findDepartment(int id) {
        DepartmentDao departmentDao = DepartmentDao.getInstance();
        return departmentDao.findOneById(em, id).get().toDto();
    }

    public List<DepartmentDto> findAllDepartments() {
        DepartmentDao departmentDao = DepartmentDao.getInstance();
        List<Department> departments = departmentDao.findAll(em);
        return Department.toDtoList(departments);
    }

    public boolean updateDepartment(DepartmentDto departmentDto, int id) {
        DepartmentDao departmentDao = DepartmentDao.getInstance();
        Department department = null;
        departmentDto.setId(id);

        try {
            em.getTransaction().begin();
            department = departmentDao.findOneById(em, id).orElseGet(()->null);
            populateDepartment(department, departmentDto);

            departmentDao.update(em, department);
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

    public boolean deleteDepartment(int id) {
        DepartmentDao departmentDao = DepartmentDao.getInstance();

        try {
            em.getTransaction().begin();
            if (!departmentDao.deleteById(em, id)) return false;
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

    public boolean deleteAllDepartments() {
        DepartmentDao departmentDao = DepartmentDao.getInstance();

        try {
            em.getTransaction().begin();
            departmentDao.deleteAll(em);
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

    public Set<EmployeeDto> findDepartmentEmployees(int id) {
        DepartmentDao departmentDao = DepartmentDao.getInstance();
        Department department = departmentDao.findOneById(em, id).get();
        return Employee.toDtoSet(department.getEmployees());
    }
}

package iti.jets.daos;

import iti.jets.entities.Department;
import jakarta.persistence.EntityManager;

public class DepartmentDao extends AbstractCRUDDao<Department>{
    private static final DepartmentDao INSTANCE = new DepartmentDao();
    protected DepartmentDao() {
        super(Department.class);
    }
    public static DepartmentDao getInstance() {
        return INSTANCE;
    }

    // get department name by id
    public String getDepartmentName(EntityManager entityManager, Integer departmentId){
        return entityManager.createQuery("SELECT d.name FROM Department d WHERE d.id = :departmentId", String.class)
                .setParameter("departmentId", departmentId)
                .getSingleResult();
    }

    // get department manager id by department name
    public Integer getDepartmentManagerId(EntityManager entityManager, String departmentName){
        return entityManager.createQuery("SELECT d.manager.id FROM Department d WHERE d.name = :departmentName", Integer.class)
                .setParameter("departmentName", departmentName)
                .getSingleResult();
    }
}

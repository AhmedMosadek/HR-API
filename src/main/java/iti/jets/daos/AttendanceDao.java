package iti.jets.daos;

import iti.jets.entities.Attendance;
import jakarta.persistence.EntityManager;

import java.util.Date;

public class AttendanceDao extends AbstractCRUDDao<Attendance>{
    private static final AttendanceDao INSTANCE = new AttendanceDao(Attendance.class);
    protected AttendanceDao(Class<Attendance> clazz) {
        super(clazz);
    }
    public synchronized static AttendanceDao getInstance() {
        if(INSTANCE == null){
            return new AttendanceDao(Attendance.class);
        }
        return INSTANCE;
    }

    // get attendance status by employee id and date formatted as "yyyy-MM-dd"
    public String getAttendanceStatus(EntityManager entityManager, Integer employeeId, Date date){
        return entityManager.createQuery("SELECT a.status FROM Attendance a WHERE a.employee.id = :employeeId AND a.date = :date", String.class)
                .setParameter("employeeId", employeeId)
                .setParameter("date", date)
                .getSingleResult();
    }

    // return true if late and false if on time
    // Note: if he is absent don't check on late or not cus doesn't matter (will be false)
    public Boolean isLateOrAbsent(EntityManager entityManager, Integer employeeId, Date date){
        return entityManager.createQuery("SELECT a.late FROM Attendance a WHERE a.employee.id = :employeeId AND a.date = :date", Boolean.class)
                .setParameter("employeeId", employeeId)
                .setParameter("date", date)
                .getSingleResult();
    }

    // get number of absence days by employee id
    // logically this table should be cleared every year so the count returned is in the current year
    public Integer getAbsenceDays(EntityManager entityManager, Integer employeeId){
        return entityManager.createQuery("SELECT COUNT(a) FROM Attendance a WHERE a.employee.id = :employeeId AND a.status = 'absent'", Integer.class)
                .setParameter("employeeId", employeeId)
                .getSingleResult();
    }
}

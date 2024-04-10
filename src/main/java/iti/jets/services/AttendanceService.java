package iti.jets.services;

import iti.jets.configuration.EntityManagerFactoryProvider;
import iti.jets.daos.AttendanceDao;
import iti.jets.daos.EmployeeDao;
import iti.jets.dtos.AttendanceDto;
import iti.jets.dtos.EmployeeDto;
import iti.jets.entities.Attendance;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class AttendanceService {
    private final EntityManager em = EntityManagerFactoryProvider.getEMF().createEntityManager();

    public boolean createAttendance(AttendanceDto attendanceDto) {
        AttendanceDao attendanceDao = AttendanceDao.getInstance();
        Attendance attendance = new Attendance();

        populateAttendance(attendance, attendanceDto);

        try {
            em.getTransaction().begin();
            attendanceDao.create(em, attendance);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    private void populateAttendance(Attendance attendance, AttendanceDto attendanceDto) {
//        Optional.ofNullable(attendanceDto.getId()).ifPresent(attendance::setId);
        Optional.of(attendanceDto.getEmployeeId()).flatMap(employeeId -> EmployeeDao.getInstance().findOneById(em, employeeId)).ifPresent(attendance::setEmployee);
        Optional.ofNullable(attendanceDto.getStatus()).ifPresent(attendance::setStatus);
        Optional.ofNullable(attendanceDto.getLate()).ifPresent(attendance::setLate);
        Optional.ofNullable(attendanceDto.getDate()).ifPresent(attendance::setDate);
    }

    public AttendanceDto findAttendance(int id) {
        AttendanceDao attendanceDao = AttendanceDao.getInstance();
        return attendanceDao.findOneById(em, id).get().toDto();
    }

    public List<AttendanceDto> findAllAttendances() {
        AttendanceDao attendanceDao = AttendanceDao.getInstance();
        List<Attendance> attendances = attendanceDao.findAll(em);
        return Attendance.toDtoList(attendances);
    }

    public boolean updateAttendance(AttendanceDto attendanceDto, int id) {
        AttendanceDao attendanceDao = AttendanceDao.getInstance();
        Attendance attendance = null;
        attendanceDto.setId(id);

        try {
            em.getTransaction().begin();
            attendance = attendanceDao.findOneById(em, id).orElseGet(()->null);
            populateAttendance(attendance, attendanceDto);
            attendanceDao.update(em, attendance);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    public boolean deleteAttendance(int id) {
        AttendanceDao attendanceDao = AttendanceDao.getInstance();

        try {
            em.getTransaction().begin();
            if(!attendanceDao.deleteById(em, id)) return false;
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    public boolean deleteAllAttendances() {
        AttendanceDao attendanceDao = AttendanceDao.getInstance();

        try {
            em.getTransaction().begin();
            attendanceDao.deleteAll(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
        return true;
    }
}

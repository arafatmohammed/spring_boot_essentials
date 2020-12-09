package com.amdcloudguru.demo.service;

import com.amdcloudguru.demo.dao.StudentDao;
import com.amdcloudguru.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentDao studentDao;

    @Autowired // Spring automatically autowires FakeStudentDaoImpl to this Class

    // Specify the qualifier so Spring knows what Class it is serving from.
    public StudentService(@Qualifier("mongoDbDao") StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public int persistNewStudent(UUID studentId, Student student) {
        UUID studentUid = studentId == null ? UUID.randomUUID() : studentId;
        student.setId(studentId);
        return studentDao.insertNewStudent(studentUid, student);

    }

    public Student getStudentById(UUID studentId) {
        return studentDao.selectStudentById(studentId);
    }

    public List<Student> getAllStudents() {
        return studentDao.selectAllStudents();

    }

    public int updateStudentById(UUID studentId, Student studentUpdate) {
        return studentDao.updateStudentById(studentId, studentUpdate);

    }

    public int deleteStudentById(UUID studentId) {
        Student student = getStudentById(studentId);
        if(student == null) {
            throw new IllegalStateException();
        } else {
            return studentDao.deleteStudentById(studentId);
        }

    }
}

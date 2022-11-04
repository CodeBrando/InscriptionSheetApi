package com.brandocode.inscriptionsheetapi.services;

import com.brandocode.inscriptionsheetapi.enums.StudentStatusEnum;
import com.brandocode.inscriptionsheetapi.models.bo.StudentBO;
import com.brandocode.inscriptionsheetapi.models.mappers.StudentMapper;
import com.brandocode.inscriptionsheetapi.repo.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    IStudentRepository repository;

    public void saveStudent(StudentBO studentBO) throws EntityExistsException {
        studentBO.setStudentCode(UUID.randomUUID().toString());
        if(repository.existsByStudentCode(studentBO.getStudentCode())){
            throw new EntityExistsException("Student with student code " + studentBO.getStudentCode() + " already exists");
        }
        studentBO.setStudentStatus(StudentStatusEnum.ENROLLED.name());
        repository.save(StudentMapper.convertBOToDE(studentBO));
    }

    public void findStudentByStudentCode(String studentCode){
        repository.findByStudentCode(studentCode);
    }

    public List<StudentBO> findAllStudents(){
       return StudentMapper.convertDEListToBOList(repository.findAll());
    }

}

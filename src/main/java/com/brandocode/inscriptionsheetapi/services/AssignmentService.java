package com.brandocode.inscriptionsheetapi.services;

import com.brandocode.inscriptionsheetapi.models.bo.AssignmentBO;
import com.brandocode.inscriptionsheetapi.models.mappers.AssignmentMapper;
import com.brandocode.inscriptionsheetapi.repo.IAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AssignmentService {
    @Autowired
    IAssignmentRepository repository;

    public void saveAssignment(AssignmentBO assignmentBO) throws EntityExistsException {
        if(repository.existsByAssignmentCode(assignmentBO.getAssignmentCode())){
            throw new EntityExistsException("Assignment with assignment code " + assignmentBO.getAssignmentCode() + " already exists");
        }
        repository.save(AssignmentMapper.convertBOToDE(assignmentBO));
    }
     public List<AssignmentBO> findAllAssignments(){
        return AssignmentMapper.convertDEListToBOList(repository.findAll());
     }

     public AssignmentBO findAssignment(String name){
        return AssignmentMapper.convertDEToBO(repository.findAssignmentByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Assignment with name " + name+ "does not exist.")));
     }

     public AssignmentBO findAssignmentByAssignmentCode(String assignmentCode){
        return AssignmentMapper.convertDEToBO(repository.findByAssignmentCode(assignmentCode)
                .orElseThrow(() -> new EntityNotFoundException("Assignment with name " + assignmentCode + "does not exist")));
     }

     public void updateAssignment(AssignmentBO assignmentBO, String assignmentCode){
        AssignmentBO assignmentToUpdate = findAssignmentByAssignmentCode(assignmentCode);
        assignmentBO.setId(assignmentToUpdate.getId());
        assignmentBO.setAssignmentCode(assignmentCode);
        repository.save(AssignmentMapper.convertBOToDE(assignmentBO));
     }

     public void deleteAssignment(String assignmentCode){
        repository.delete(AssignmentMapper.convertBOToDE(findAssignmentByAssignmentCode(assignmentCode)));
     }


}

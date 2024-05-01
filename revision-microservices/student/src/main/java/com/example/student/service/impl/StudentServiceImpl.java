package com.example.student.service.impl;

import com.example.student.dto.AddressDto;
import com.example.student.dto.StudentDtoRequest;
import com.example.student.dto.StudentDtoResponse;
import com.example.student.exceptions.ResourceNotFoundException;
import com.example.student.feignclients.AddressFeign;
import com.example.student.model.Student;
import com.example.student.respository.StudentRepository;
import com.example.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AddressFeign addressFeign;

    @Override
    public StudentDtoRequest addStudent(StudentDtoRequest studentDto) {
        Student student = Student.builder()
                .sudentName(studentDto.getStudentName())
                .addressId(studentDto.getAddressId())
                .build();
        Student savedStudent = studentRepository.save(student);
        return StudentDtoRequest.builder()
                .studentName(savedStudent.getSudentName())
                .addressId(savedStudent.getAddressId())
                .build();
    }

    @Override
    public StudentDtoResponse findStudent(int studentId) {
        Student savedStudent = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("not found"));

        log.info("inside the service call of finding student");
        AddressDto addressDto = addressFeign.findAddress(savedStudent.getAddressId()).getBody();
        log.info("data recieved");
        return StudentDtoResponse.builder()
                .studentName(savedStudent.getSudentName())
                .addressDto(addressDto)
                .build();
    }

}

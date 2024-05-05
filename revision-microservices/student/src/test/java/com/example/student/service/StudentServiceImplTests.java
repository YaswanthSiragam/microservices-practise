package com.example.student.service;

import com.example.student.dto.AddressDto;
import com.example.student.dto.StudentDtoRequest;
import com.example.student.dto.StudentDtoResponse;
import com.example.student.exceptions.ResourceNotFoundException;
import com.example.student.feignclients.AddressFeign;
import com.example.student.model.Student;
import com.example.student.respository.StudentRepository;
import com.example.student.service.impl.StudentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.Option;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class StudentServiceImplTests {

    @Mock
    StudentRepository studentRepository;
    @Mock
    AddressFeign addressFeign;
    @InjectMocks
    StudentServiceImpl studentService;

    Student student;
    StudentDtoRequest studentDtoRequest;

    @BeforeEach
    public void setup() {

        student = Student.builder()
                .sudentName("Yash")
                .addressId(1)
                .build();

        studentDtoRequest = StudentDtoRequest.builder()
                .studentName("Yash")
                .addressId(1)
                .build();

    }

    @Test
    public void givenStudentObject_whenAskedToSave_thenSaveStudent() {

        Mockito.when(studentRepository.save(student)).thenReturn(student);
        StudentDtoRequest savedStudentDto = studentService.addStudent(studentDtoRequest);

        log.info(savedStudentDto.toString());
        Assertions.assertThat(savedStudentDto.getStudentName()).isEqualTo(student.getSudentName());

    }

    @Test
    public void givenStudentId_whenStudentNotFound_thenThrowException() {

        Mockito.when(studentRepository.findById(0)).thenReturn(Optional.empty());
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> studentService.findStudent(0));

    }

    @Test
    public void givenStudentId_whenStudentExists_thenReturnStudent() {

        Mockito.when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        Mockito.when(addressFeign.findAddress(1)).thenReturn(new ResponseEntity<>(new AddressDto(), HttpStatus.OK));

        StudentDtoResponse studentDtoResponse = studentService.findStudent(1);

        Assertions.assertThat(studentDtoResponse.getAddressDto()).isNotNull();
        Assertions.assertThat(studentDtoResponse.getStudentName()).isEqualTo(student.getSudentName());

    }

}

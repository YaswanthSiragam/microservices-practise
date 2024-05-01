package com.example.student.controller;

import com.example.student.dto.StudentDtoRequest;
import com.example.student.dto.StudentDtoResponse;
import com.example.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apis/student/")
@Slf4j
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("add")
    public ResponseEntity<StudentDtoRequest> addStudent(@RequestBody StudentDtoRequest studentDtoRequest) {
        StudentDtoRequest studentDto = studentService.addStudent(studentDtoRequest);
        return new ResponseEntity<>(studentDto, HttpStatus.CREATED);
    }

    @GetMapping("find/{studentId}")
    public ResponseEntity<StudentDtoResponse> findStudent(@PathVariable int studentId) {
        StudentDtoResponse studentDto = studentService.findStudent(studentId);
        log.info("student information is all ready");
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

}

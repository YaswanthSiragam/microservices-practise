package com.example.student.service;

import com.example.student.dto.StudentDtoRequest;
import com.example.student.dto.StudentDtoResponse;

public interface StudentService {

    public StudentDtoRequest addStudent(StudentDtoRequest studentDto);
    public StudentDtoResponse findStudent(int studentId);

}

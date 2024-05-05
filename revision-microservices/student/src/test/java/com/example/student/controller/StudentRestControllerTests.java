package com.example.student.controller;

import com.example.student.dto.AddressDto;
import com.example.student.dto.StudentDtoRequest;
import com.example.student.dto.StudentDtoResponse;
import com.example.student.service.StudentService;
import com.example.student.service.impl.StudentServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentRestControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentServiceImpl studentService;

    StudentDtoRequest studentDtoRequest;
    StudentDtoResponse studentDtoResponse;
    AddressDto addressDto;

    @BeforeEach
    public void setup() {
        studentDtoRequest = StudentDtoRequest.builder()
                .studentName("yash")
                .addressId(1)
                .build();
        studentDtoResponse = StudentDtoResponse.builder()
                .studentName("yash")
                .addressDto(new AddressDto())
                .build();
    }

    @Test
    public void givenStudentObject_whenAdded_thenAddStudent() throws JsonProcessingException, Exception {

        Mockito.when(studentService.addStudent(studentDtoRequest)).thenReturn(studentDtoRequest);

        mockMvc.perform(post("/apis/student/add").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(studentDtoRequest)))
                .andExpect(status().isCreated());

        Mockito.verify(studentService).addStudent(studentDtoRequest);

    }

    @Test
    public void givenStudentId_whenAskedToFind_thenReturnStudent() throws Exception {

        Mockito.when(studentService.findStudent(1)).thenReturn(studentDtoResponse);

        mockMvc.perform(get("/apis/student/find/{studentId}", "1")).andExpect(status().isOk());

        Mockito.verify(studentService).findStudent(1);

    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hieutran.hsm.controller;

import com.hieutran.hsm.dao.IDoctorDAO;
import com.hieutran.hsm.model.Doctor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**         @author Hieu Tran           */
@Controller
public class DoctorController {
    @Autowired
    IDoctorDAO doctorDAO;
    
    @GetMapping(value = "/list-doctor", headers = "Accept=application/json")
    public ResponseEntity<List<Doctor>> getAllDoctor(){
        List<Doctor> list = doctorDAO.getAllDoctors();
        
        return ResponseEntity.ok(list);
    }
    
    @PostMapping(value = "admin/add-doctor", headers = "Accept=application/json")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor){
        System.out.println("POST add doctor");
        return ResponseEntity.ok().body(doctorDAO.addDoctor(doctor));
    }

}

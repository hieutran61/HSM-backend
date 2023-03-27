/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hieutran.hsm.controller;

import com.hieutran.hsm.dao.IDoctorDAO;
import com.hieutran.hsm.model.Doctor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**         @author Hieu Tran           */
@Controller
public class TestController {
    @Autowired
    IDoctorDAO doctorDAO;
    
    @RequestMapping("/test")
    public ResponseEntity test(@RequestParam int id){
        Doctor doctor = doctorDAO.getDoctorById(id);
        System.out.println("getAll ne`````````````````");
        
        return ResponseEntity.ok(doctor);
    }

}

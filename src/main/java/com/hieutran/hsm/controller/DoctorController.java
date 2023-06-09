/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hieutran.hsm.controller;

import com.hieutran.hsm.dao.IDoctorDAO;
import com.hieutran.hsm.model.Doctor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    
    @GetMapping("/list-doctor/page")
    public @ResponseBody ResponseEntity<Object> getAllDoctorPage(@RequestParam int pageSize,@RequestParam int pageNum, @RequestParam String searchText, 
                                                @RequestParam String orderColumn, @RequestParam String sort){
        List<Doctor> list = doctorDAO.getFilterDoctors(pageSize, pageNum, searchText, orderColumn, sort);
        int totalRecord = doctorDAO.countFilterDoctors(searchText);
        
        Map<String, Object> map = new HashMap<>();
        map.put("filterDoctor", list);
        map.put("totalRecord", totalRecord);
        
        return ResponseEntity.ok(map);
    }
    
    @PostMapping(value = "admin/add-doctor", headers = "Accept=application/json")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor){
        System.out.println("POST add doctor");
        return ResponseEntity.ok().body(doctorDAO.addDoctor(doctor));
    }
    
    @GetMapping("/list-doctor/delete/{id}")
    public ResponseEntity<Doctor> deleteDoctor(@PathVariable int id)
    {
        if (doctorDAO.getDoctor(id)==null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        doctorDAO.deleteDoctor(id);
        
        return ResponseEntity.ok(doctorDAO.getDoctor(id));   
    }
    
    @GetMapping("/list-doctor/")
    public ResponseEntity<Object> getUserByUsername(@RequestParam String username){
        Doctor doc = doctorDAO.getDoctor(username);
        
        return ResponseEntity.ok(doc);
    }
    
    @GetMapping("/list-doctor/{id}")
    public ResponseEntity<Object> getDoctorById(@PathVariable int id){
        Doctor doc = doctorDAO.getDoctor(id);
        if (doc == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
     
        return ResponseEntity.ok(doc);
    }
    
    @PostMapping(value = "/list-doctor/update", headers = "Accept=application/json")
    public ResponseEntity updateDoctor(@RequestBody Doctor updatedDoctor){
        System.out.println("BEGIN updateDoctor");
        Doctor doc = doctorDAO.getDoctor(updatedDoctor.getDocId());
        if (doc == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        doctorDAO.updateDoctor(updatedDoctor);
    
        System.out.println("updated done");
        return ResponseEntity.ok(null);
    }
    
    @PostMapping(value = "/list-doctor/update-by-username", headers = "Accept=application/json")
    public ResponseEntity updateDoctorByUsername(@RequestBody Doctor updatedDoctor){
        doctorDAO.updateDoctor(updatedDoctor);

        return ResponseEntity.ok(null);
    }
    
    @PostMapping(value = "admin/add-doctor-excel", headers = "Accept=application/json")
    public ResponseEntity<Doctor> addDoctorFromExcel(@RequestBody Doctor[] excelData){
        for (Doctor doc: excelData){
            if (doctorDAO.getDoctor(doc.getUsername()) != null)
            {
                System.out.println("found doctor trung` username");
                doctorDAO.updateDoctorByUsername(doc);
            }
            else 
            {
                System.out.println("add doctor from excel");
                doctorDAO.addDoctor(doc);
            }
        }
        return ResponseEntity.ok().body(null);
    }
    
    @GetMapping("list-doctor-by-search/")
    public @ResponseBody ResponseEntity getListBySearch(@RequestParam String searchText)
    {
        List<Doctor> list = doctorDAO.getListBySearch(searchText);
        
        System.out.println((list.size()));
        
        return ResponseEntity.ok(list);
    }
    
}

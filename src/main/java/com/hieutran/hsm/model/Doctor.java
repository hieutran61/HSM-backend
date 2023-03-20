/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hieutran.hsm.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Doctor {
    private int docId;
    private String name;
    private String username;
    private String password;
    private String department;
    private String specialization;
    private String phone;
    private String address;
    private String email;
    private Timestamp modifyTime;
    private boolean isActive;

    public Doctor() {
    }

    public Doctor(int docId, String name, String username, String password, String department, String specialization, String phone, String address, String email, Timestamp modifyTime, boolean isActive) {
        this.docId = docId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.department = department;
        this.specialization = specialization;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.modifyTime = modifyTime;
        this.isActive = isActive;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }





    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    
    
    
}

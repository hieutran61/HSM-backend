/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hieutran.hsm.dao;

import com.hieutran.hsm.model.Doctor;
import java.util.List;

/**
 *
 * @author Hieu Tran
 */
public interface IDoctorDAO {
    public List<Doctor> getAllDoctors();
    public Doctor addDoctor(Doctor doctor);
}

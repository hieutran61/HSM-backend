/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hieutran.hsm.dao.impl;

import com.hieutran.hsm.dao.IDoctorDAO;
import com.hieutran.hsm.model.Doctor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author Hieu Tran
 */

@Repository
public class DoctorDAOImpl implements IDoctorDAO{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Doctor> list() {
        String sql = "select * from Doctors";
        List<Doctor> list = jdbcTemplate.query(sql, new RowMapper<Doctor>() {
            public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException{
                Doctor doctor = new Doctor();
                doctor.setDocId(rs.getInt("docId"));
                doctor.setName(rs.getString("name"));
                doctor.setUsername(rs.getString("username"));
                doctor.setPassword(rs.getString("password"));
                doctor.setDepartment(rs.getString("department"));
                doctor.setPhone(rs.getString("phone"));
                doctor.setAddress(rs.getString("address"));
                doctor.setEmail(rs.getString("email"));
                doctor.setIsActive(rs.getBoolean("isActive"));
                doctor.setModifyTime(rs.getTimestamp("modifyTime"));
                return doctor;
            }
        });
        
        return list;
    }
    

}

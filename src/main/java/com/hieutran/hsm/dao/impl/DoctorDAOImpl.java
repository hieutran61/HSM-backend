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
import org.springframework.dao.EmptyResultDataAccessException;
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

    private static final class DoctorMapper implements RowMapper<Doctor>{
        @Override
        public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
            Doctor doctor = new Doctor();
                doctor.setDocId(rs.getInt("docId"));
                doctor.setName(rs.getString("name"));
                doctor.setUsername(rs.getString("username"));
                doctor.setPassword(rs.getString("password"));
                doctor.setDepartment(rs.getString("department"));
                doctor.setSpecialization(rs.getString("specialization"));
                doctor.setPhone(rs.getString("phone"));
                doctor.setAddress(rs.getString("address"));
                doctor.setEmail(rs.getString("email"));
                doctor.setIsActive(rs.getBoolean("isActive"));
                doctor.setModifyTime(rs.getTimestamp("modifyTime"));
                return doctor;
        }
    }

    @Override
    public List<Doctor> getAllDoctors() {
        String sql = "select * from Doctors where isActive=1";
        List<Doctor> list = jdbcTemplate.query(sql, new DoctorMapper());
        
        return list;
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        String sql = "INSERT INTO Doctors VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        Object[] doc = {doctor.getName(), doctor.getUsername(), doctor.getPassword(), doctor.getDepartment(), doctor.getSpecialization(),
//                            doctor.getPhone(), doctor.getAddress(), doctor.getEmail(), doctor.isIsActive(), doctor.getModifyTime()};
        jdbcTemplate.update(sql, doctor.getName(), doctor.getUsername(), doctor.getPassword(), doctor.getDepartment(), doctor.getSpecialization(),
                            doctor.getPhone(), doctor.getAddress(), doctor.getEmail(), doctor.isIsActive(), doctor.getModifyTime());
        
        return doctor;
    }

    @Override
    public List<Doctor> getFilterDoctors(int pageSize, int pageNum, String searchText, String orderColumn, String sort) {
        String sql = "select * from Doctors where ([name] like '%" + searchText + "%'\n" +
"						or [username] like '%" + searchText + "%'\n" +
"						or [department] like '%" + searchText + "%'\n" +
"						or [specialization] like '%" + searchText + "%'\n" +
"						or [phone] like '%" + searchText + "%'\n" +
"						or [address] like '%" + searchText + "%'\n" +
"						or [email] like '%" + searchText + "%')\n" +
"						and [isActive]=1\n" +
"                       order by " + orderColumn + " " + sort + "  offset " + ((pageNum-1)*pageSize) +" rows fetch next " + pageSize + " rows only";
        
        List<Doctor> list = jdbcTemplate.query(sql, new DoctorMapper());

        return list;
    }

    @Override
    public int countFilterDoctors(String searchText) {
        String sql = "select count(docId) as totalRecord \n" +
                    "from Doctors \n" +
                    "where ([name] like '%" + searchText + "%'\n" +
"                   or [username] like '%" + searchText + "%'\n" +
"                   or [department] like '%" + searchText + "%'\n" +
"                   or [specialization] like '%" + searchText + "%'\n" +
"                   or [phone] like '%" + searchText + "%'\n" +
"                   or [address] like '%" + searchText + "%'\n" +
"                   or [email] like '%" + searchText + "%')\n" +
"                   and [isActive]=1\n";
        
        int totalRecord = jdbcTemplate.queryForObject(sql, Integer.class);
        return totalRecord;
    }
    
    @Override
    public boolean deleteDoctor(int doctorId) {
        
        String sql = "UPDATE Doctors\n" +
"                    SET isActive = 0\n" +
"                    WHERE [docId] = ?;";
        jdbcTemplate.update(sql, doctorId);
        return true;
    }
    
    @Override
    public Doctor getDoctor(int doctorId) {
        try {
            String sql = "select * from Doctors where [docId] = ? and isActive = 1";
            Doctor doc = jdbcTemplate.queryForObject(sql, new DoctorMapper(), doctorId);
            return doc;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    @Override
    public Doctor getDoctor(String username) {
        try {
            String sql = "select * from Doctors where [username] = ? and isActive = 1";
            Doctor doc = jdbcTemplate.queryForObject(sql, new DoctorMapper(), username);
            return doc;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    @Override
    public void updateDoctor(Doctor doctor) {
        String sql = "UPDATE Doctors\n" +
"                   SET [name] = ?, [username] = ?, [password] = ?,\n" +
"                   [department] = ?, [specialization] = ?, [phone] = ?,\n" +
"                   [address] = ?, [email] = ?, [modifyTime] = ?\n" +
"                   WHERE [docId]= ? ";
        jdbcTemplate.update(sql, doctor.getName(), doctor.getUsername(), doctor.getPassword(), doctor.getDepartment(), doctor.getSpecialization(),
                            doctor.getPhone(), doctor.getAddress(), doctor.getEmail(), doctor.getModifyTime(), doctor.getDocId());
    }
    
    @Override
    public List<Doctor> getListBySearch(String searchText){
        String sql = "select * from Doctors where ([name] like '%" + searchText + "%'\n" +
"						or [username] like '%" + searchText + "%'\n" +
"						or [department] like '%" + searchText + "%'\n" +
"						or [specialization] like '%" + searchText + "%'\n" +
"						or [phone] like '%" + searchText + "%'\n" +
"						or [address] like '%" + searchText + "%'\n" +
"						or [email] like '%" + searchText + "%')\n" +
"						and [isActive]=1";
        List<Doctor> list = jdbcTemplate.query(sql, new DoctorMapper());

        return list;
    }
  

    

}

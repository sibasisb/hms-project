package com.project.hospitalmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.hospitalmanagementbackend.model.HospitalAdmin;

@Repository
public interface HospitalAdminRepository extends JpaRepository<HospitalAdmin, String> {
	
	@Query("select ha.hospital.hospitalId from HospitalAdmin ha where ha.hospitalAdminId=:hospitalAdminId")
	public String getHospitalIdByAdminId(@Param("hospitalAdminId") String hospitalAdminId); 
	
}

package com.project.hospitalmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.hospitalmanagementbackend.model.HospitalFacility;

@Repository
public interface HospitalFacilityRepository extends JpaRepository<HospitalFacility, Long> {

	@Query("from HospitalFacility hf where hf.hospital.hospitalId=:hospitalId")
	public List<HospitalFacility> getFacilitiesByHospitalId(@Param("hospitalId") String hospitalId);

}

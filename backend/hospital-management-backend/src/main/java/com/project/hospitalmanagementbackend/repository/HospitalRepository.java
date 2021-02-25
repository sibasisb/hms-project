package com.project.hospitalmanagementbackend.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.hospitalmanagementbackend.model.Hospital;


@Repository
public interface HospitalRepository extends JpaRepository<Hospital, String> {
	
	@Query("SELECT h FROM Hospital h JOIN FETCH h.hospitalFacilities hf join fetch hf.facility f join fetch f.baselines JOIN FETCH h.doctors d Join fetch d.user")
	public Set<Hospital> getAllHospitals();
	

}

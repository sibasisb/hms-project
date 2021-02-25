package com.project.hospitalmanagementbackend.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.hospitalmanagementbackend.model.Hospital;


@Repository
public interface HospitalRepository extends JpaRepository<Hospital, String> {
	
	@Query("SELECT h FROM Hospital h left JOIN FETCH h.hospitalFacilities hf left join fetch hf.facility f left join fetch f.baselines left JOIN FETCH h.doctors d left Join fetch d.user")
	public Set<Hospital> getAllHospitals();

	@Query("SELECT h FROM Hospital h left JOIN FETCH h.hospitalFacilities hf left join fetch hf.facility f left join fetch f.baselines left JOIN FETCH h.doctors d left Join fetch d.user where h.hospitalId=?1")
	public Optional<Hospital> getHospitalById(String hospitalId);
	

}

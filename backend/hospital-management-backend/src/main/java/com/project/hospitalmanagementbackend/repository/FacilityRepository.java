package com.project.hospitalmanagementbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.hospitalmanagementbackend.model.Facility;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {

	@Query("Select f from Facility f")
	List<Facility> getAllFacilities();

	@Query("Select f from Facility f join fetch f.baselines where f.facilityId=?1")
	Optional<Facility> getFacilityById(long facilityId);

}

package com.project.hospitalmanagementbackend.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.hospitalmanagementbackend.model.TieUp;

@Repository
public interface TieUpRepository extends JpaRepository<TieUp, Long> {

	@Query("Select t from TieUp t where t.hospital1.hospitalId=?1 and t.hospital2.hospitalId=?2")
	public TieUp checkTieUp(String hospitalId, String hospitalId2);

	@Query("Select t from TieUp t where t.hospital1.hospitalId=?1 or t.hospital2.hospitalId=?1")
	public Set<TieUp> getTieUpOfHospital(String hospitalId);

}

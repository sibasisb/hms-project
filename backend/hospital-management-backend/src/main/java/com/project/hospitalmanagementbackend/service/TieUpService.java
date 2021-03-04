package com.project.hospitalmanagementbackend.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.dto.TieUpInfo;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.TieUp;
import com.project.hospitalmanagementbackend.repository.HospitalRepository;
import com.project.hospitalmanagementbackend.repository.TieUpRepository;

@Service
public class TieUpService {

	@Autowired
	TieUpRepository tieUpRespository;
	
	@Autowired
	HospitalRepository hospitalRepository;
	
	public Set<TieUpInfo> getTieUps() {
		// TODO Auto-generated method stub
		Set<TieUp> tieUpSet = tieUpRespository.findAll().stream().collect(Collectors.toSet());
		Set<TieUpInfo> tieUpInfoSet = new HashSet<TieUpInfo>();
		tieUpSet.forEach((tieUp)->{
			TieUpInfo tieUpInfo = new TieUpInfo();
			tieUpInfo.setTieUpId(tieUp.getTieUpId());
			tieUpInfo.setHospital1Id(tieUp.getHospital1().getHospitalId());
			tieUpInfo.setHospital1Name(tieUp.getHospital1().getName());
			tieUpInfo.setHospital2Id(tieUp.getHospital2().getHospitalId());
			tieUpInfo.setHospital2Name(tieUp.getHospital2().getName());
			tieUpInfoSet.add(tieUpInfo);
		});
		return tieUpInfoSet;
	}
	
	public String newTieUp(TieUp tieUp)
	{
		if(tieUp.getTieUpId()!=0)
		{
			TieUp tieUpFound = tieUpRespository.findById(tieUp.getTieUpId()).get();
			Hospital hospital1 = hospitalRepository.findById(tieUp.getHospital1().getHospitalId()).get();
			tieUpFound.setHospital1(hospital1);
			Hospital hospital2 = hospitalRepository.findById(tieUp.getHospital2().getHospitalId()).get();
			tieUpFound.setHospital2(hospital2);
			tieUpRespository.save(tieUpFound);
		}
		else
		tieUpRespository.save(tieUp);
		return "New Tie Up has been set";
	}
	
	public boolean checkTieUp(TieUp tieUp) 
	{
		TieUp checkTieUp1 = tieUpRespository.checkTieUp(tieUp.getHospital1().getHospitalId(),tieUp.getHospital2().getHospitalId());
		TieUp checkTieUp2 = tieUpRespository.checkTieUp(tieUp.getHospital2().getHospitalId(),tieUp.getHospital1().getHospitalId());
		if(checkTieUp1!=null || checkTieUp2!=null)
			return true;
			else
				return false;
	}

	public Set<TieUpInfo> getTieUpsOfHospital(String hospitalId)
	{
		Set<TieUp> tieUpSet = tieUpRespository.getTieUpOfHospital(hospitalId);
		Set<TieUpInfo> tieUpInfoSet = new HashSet<TieUpInfo>();
		tieUpSet.forEach((tieUp)->{
			TieUpInfo tieUpInfo = new TieUpInfo();
			tieUpInfo.setTieUpId(tieUp.getTieUpId());
			Hospital tiedUpHospital=tieUp.getHospital1().getHospitalId().equals(hospitalId)?tieUp.getHospital2():tieUp.getHospital1();
			tieUpInfo.setHospital2Id(tiedUpHospital.getHospitalId());
			tieUpInfo.setHospital2Name(tiedUpHospital.getName());
			tieUpInfoSet.add(tieUpInfo);
		});
		return tieUpInfoSet;
	}
}

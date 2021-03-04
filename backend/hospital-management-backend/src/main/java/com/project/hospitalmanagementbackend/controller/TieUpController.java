package com.project.hospitalmanagementbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalmanagementbackend.model.TieUp;
import com.project.hospitalmanagementbackend.service.TieUpService;

@RestController
@RequestMapping("/tieups")
public class TieUpController {

	@Autowired
	TieUpService tieUpService;
	
	@GetMapping("/")
	public ResponseEntity<?> viewTieUps()
	{
		return new ResponseEntity<>(tieUpService.getTieUps(),HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> setNewTieUps(@RequestBody TieUp tieUp)
	{
	
	return new ResponseEntity<>(tieUpService.newTieUp(tieUp),HttpStatus.CREATED);
}
	
	@PostMapping("/check")
	public ResponseEntity<?> CheckTieUp(@RequestBody TieUp tieUp)
	{
		return new ResponseEntity<>(tieUpService.checkTieUp(tieUp),HttpStatus.OK);
	}
	
	@GetMapping("/{hospitalId}")
	public ResponseEntity<?> viewTieUps(@PathVariable String hospitalId)
	{
		return new ResponseEntity<>(tieUpService.getTieUpsOfHospital(hospitalId),HttpStatus.OK);
	}

}
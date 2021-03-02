-- insert into sys_Admins table
INSERT INTO sys_admins VALUES ('admin', 'root');
INSERT INTO sys_admins VALUES ('su', 'root');

-- insert into user table
insert into user values(1,'8545635212','1998-03-26','abc@gmail.com','John','male','Smith','pass12$','patient');
insert into user values(2,'8545635212','1998-03-26','abc@gmail.com','Patty','female','Watson','pass12$','hospital admin');
insert into user values(3,'8545635212','1998-03-26','abc@gmail.com','Kenny','male','','pass12$','hospital admin');
insert into user values(4,'8545635212','1998-03-26','abc@gmail.com','Victor','male','Strange','pass12$','doctor');
insert into user values(7,'8545635212','1998-03-26','abc@gmail.com','Stephen','male','Strange','pass12$','doctor');
insert into user values(8,'8545635212','1998-03-26','abc@gmail.com','Bruce','male','Banner','pass12$','doctor');
insert into user values(9,'8545635212','1998-03-26','abc@gmail.com','Pitt','male','Chak','pass12$','doctor');
insert into user values(10,'8545635212','1998-03-26','abc@gmail.com','George','male','Parui','pass12$','doctor');
insert into user values(11,'8545635212','1998-03-26','abc@gmail.com','John','male','Bean','pass12$','doctor');
insert into user values(12,'8545635212','1998-03-26','abc@gmail.com','Meredith','male','House','pass12$','doctor');
insert into user values(13,'8545635212','1998-03-26','abc@gmail.com','Devi','male','Shetty','pass12$','doctor');
insert into user values(5,'8545635212','1998-03-26','abc@gmail.com','Peter','male','Fernandez','pass12$','patient');
insert into user values(6,'8545635212','1998-03-26','abc@gmail.com','Bobby','female','Denver','pass12$','patient');

--insert into security_questions
insert into security_questions values(1,'What is the name of your pet?');
insert into security_questions values(2,'What is the name of your favourite movie?');
insert into security_questions values(3,'what is your favourite food?');

-- insert into doctor table
insert into doctor values('DOC0999','Mon-Tue','2P.M.-5P.M.',500,5,'M.D.','Cardiology',4);
insert into doctor values('DOC0991','Mon-Tue','2P.M.-5P.M.',500,5,'M.D.','Orthopaedics',7);
insert into doctor values('DOC0992','Mon-Tue','2P.M.-5P.M.',500,5,'M.D.','Dermatalogy',8);
insert into doctor values('DOC0993','Mon-Tue','2P.M.-5P.M.',500,5,'M.D.','Gastroentology',9);
insert into doctor values('DOC0994','Mon-Tue','2P.M.-5P.M.',500,5,'M.D.','Gynaecologist',10);
insert into doctor values('DOC0995','Mon-Tue','2P.M.-5P.M.',500,5,'M.D.','Dentist',11);
insert into doctor values('DOC0996','Mon-Tue','2P.M.-5P.M.',500,5,'M.D.','Oncologist',13);

-- insert into hospital table
insert into hospital values('HOS0995','111A, Rash Behari Ave, Dover Terrace, Gariahat, Kolkata, West Bengal 700029','Fortis Hospital','033-6628-4444','https://www.fortishealthcare.com/');
insert into hospital values('HOS0597','9, Brahmachari Street (Formerly Loudon Street), Elgin, Kolkata, West Bengal 700017','Belle Vue Clinic','098361 93420','http://www.bellevueclinic.com/');
insert into hospital values('HOS0590','Dr. U. N. Brahmachari Street, Elgin, Kolkata, West Bengal 700016','City Hospital','098361 93420','http://www.bellevueclinic.com/');
insert into hospital values('HOS0591','Elgin, Kolkata, West Bengal 700017','AMRI Hospital','098361 93420','http://www.bellevueclinic.com/');
insert into hospital values('HOS0592','10,U. N. Road, Elgin, Kolkata, West Bengal 700017','CMRI Hospital','098361 93420','http://www.bellevueclinic.com/');
insert into hospital values('HOS0593','Dr. U. N. Brahmachari Street (Formerly Loudon Street), Elgin, Kolkata, West Bengal 700017','Apollo Hospitals','098361 93420','http://www.bellevueclinic.com/');
insert into hospital values('HOS0594','Loudon Street, Elgin, Kolkata, West Bengal 700017','Sum','098361 93420','http://www.bellevueclinic.com/');
insert into hospital values('HOS0595','S.K.Bose Raod,(Formerly Loudon Street), Elgin, Kolkata, West Bengal 700017','Narayana Hospital','098361 93420','http://www.bellevueclinic.com/');
insert into hospital values('HOS0596','Behala,Elgin, Kolkata, West Bengal 700017','Disha Eye Hospital','098361 93420','http://www.bellevueclinic.com/');
insert into hospital values('HOS0598','Camac Street, Elgin, Kolkata, West Bengal 700017','B.M.Birla Hospital','098361 93420','http://www.bellevueclinic.com/');

--insert into doctor_hospital
INSERT INTO DOCTOR_HOSPITAL (DOCTOR_ID , HOSPITAL_ID ) VALUES ('DOC0999', 'HOS0595');
INSERT INTO DOCTOR_HOSPITAL (DOCTOR_ID , HOSPITAL_ID ) VALUES ('DOC0991', 'HOS0591');
INSERT INTO DOCTOR_HOSPITAL (DOCTOR_ID , HOSPITAL_ID ) VALUES ('DOC0992', 'HOS0592');
INSERT INTO DOCTOR_HOSPITAL (DOCTOR_ID , HOSPITAL_ID ) VALUES ('DOC0993', 'HOS0595');
INSERT INTO DOCTOR_HOSPITAL (DOCTOR_ID , HOSPITAL_ID ) VALUES ('DOC0994', 'HOS0595');
INSERT INTO DOCTOR_HOSPITAL (DOCTOR_ID , HOSPITAL_ID ) VALUES ('DOC0993', 'HOS0596');
INSERT INTO DOCTOR_HOSPITAL (DOCTOR_ID , HOSPITAL_ID ) VALUES ('DOC0996', 'HOS0595');
INSERT INTO DOCTOR_HOSPITAL (DOCTOR_ID , HOSPITAL_ID ) VALUES ('DOC0992', 'HOS0595');
INSERT INTO DOCTOR_HOSPITAL (DOCTOR_ID , HOSPITAL_ID ) VALUES ('DOC0995', 'HOS0595');
INSERT INTO DOCTOR_HOSPITAL (DOCTOR_ID , HOSPITAL_ID ) VALUES ('DOC0991', 'HOS0595');


-- insert into hospital_admin table
insert into hospital_admin values('HAD0998','HOS0595',2);
insert into hospital_admin values('HAD0987','HOS0597',3);

-- insert into review_questionnaire table
insert into review_questionnaire values(1, 'On a scale of 1-5,how good was your doctor/facility?');
insert into review_questionnaire values(2, 'On a scale of 1-5,how good was the hospital');

-- insert into facility table
insert into facility values(1,'Thyroid Test');
insert into facility values(2,'Dental facility');
insert into facility values(3,'Ward/ In Patient facility');
insert into facility values(4,'Minor OT/ Dressing Room');
insert into facility values(5,'Physiotherapy');
insert into facility values(6,'ECG Services');
insert into facility values(7,'Blood Sugar Test');
insert into facility values(8,'Radiology/X-ray facility');
insert into facility values(9,'Lipid Profile Test');


-- insert into baselines table
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(1,'TSH','0.40 - 4.50 mIU/mL',1);
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(2,'T3','100 - 200 ng/dL',1);
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(3,'T4','5.0 – 11.0 ug/dL',1);
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(4,'Fasting','< 100 mg/dl',7);
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(5,'PP','< 140 mg/dl',7);
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(6,'LDL','< 70 mg/dL',9);
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(7,'HDL','> 60 mg/dL',9);
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(8,'Triglyceride','< 150 mg/dL',9);
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(9,'Remarks','None',2);
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(10,'Remarks','None',3);
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(11,'Remarks','None',4);
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(12,'Remarks','None',5);
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(13,'Remarks','None',6);
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(14,'Remarks','None',8);

--insert into hospital_facility
INSERT INTO HOSPITAL_FACILITY  (HOSPITAL_FACILITY_ID, CHARGES, DESCRIPTION, FACILITY_ID , HOSPITAL_ID) VALUES (1, 50, 'This', 1, 'HOS0595');

-- insert into patient table
insert into patient(patient_id,user_id) values('PAT99996',1);
insert into patient(patient_id,user_id) values('PAT99997',5);
insert into patient(patient_id,user_id) values('PAT99998',6);

-- insert into treatment_history table
insert into treatment_history(treatment_id,prescription,treatment_patient_id,treatment_doctor_id) values(1, 'Exercise and eat well','PAT99996','DOC0999');
insert into treatment_history(treatment_id,prescription,treatment_patient_id,treatment_doctor_id) values(2, 'Eat and sleep well','PAT99997','DOC0999');

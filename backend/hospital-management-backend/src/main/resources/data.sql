-- insert into sys_Admins table
INSERT INTO sys_admins VALUES ('admin', 'root');
INSERT INTO sys_admins VALUES ('su', 'root');

-- insert into user table
insert into user values(1,'8545635212','1998-03-26','abc@gmail.com','john','male','smith','pass12$','patient');
insert into user values(2,'8545635212','1998-03-26','abc@gmail.com','patty','female','smith','pass12$','hospital admin');
insert into user values(3,'8545635212','1998-03-26','abc@gmail.com','kenny','male','smith','pass12$','hospital admin');
insert into user values(4,'8545635212','1998-03-26','abc@gmail.com','billy','male','smith','pass12$','doctor');
insert into user values(5,'8545635212','1998-03-26','abc@gmail.com','peter','male','fernandez','pass12$','patient');
insert into user values(6,'8545635212','1998-03-26','abc@gmail.com','bobby','female','denver','pass12$','patient');

--insert into security_questions
insert into security_questions values(1,'What is the name of your pet?');
insert into security_questions values(2,'What is the name of your favourite movie?');
insert into security_questions values(3,'what is your favourite food?');

-- insert into doctor table
insert into doctor values('DOC0999','Mon-Tue','2P.M.-5P.M.',500,5,'M.D.','Heart',4);

-- insert into hospital table
insert into hospital values('HOS0995','111A, Rash Behari Ave, Dover Terrace, Gariahat, Kolkata, West Bengal 700029','Fortis','033-6628-4444','https://www.fortishealthcare.com/');
insert into hospital values('HOS0597','9, Dr. U. N. Brahmachari Street (Formerly Loudon Street), Elgin, Kolkata, West Bengal 700017','Belle Vue Clinic','098361 93420','http://www.bellevueclinic.com/');

-- insert into hospital_admin table
insert into hospital_admin values('HAD0998','HOS0995',2);
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
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(9,'remarks','none',2);
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(10,'remarks','none',3);
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(11,'remarks','none',4);
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(12,'remarks','none',5);
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(13,'remarks','none',6);
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(14,'remarks','none',8);

-- insert into patient table
insert into patient(patient_id,user_id) values('PAT99996',1);
insert into patient(patient_id,user_id) values('PAT99997',5);
insert into patient(patient_id,user_id) values('PAT99998',6);

-- insert into treatment_history table
insert into treatment_history(treatment_id,prescription,treatment_patient_id,treatment_doctor_id) values(1, 'Exercise and eat well','PAT99996','DOC0999');
insert into treatment_history(treatment_id,prescription,treatment_patient_id,treatment_doctor_id) values(2, 'Eat and sleep well','PAT99997','DOC0999');

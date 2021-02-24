INSERT INTO sys_admins VALUES ('admin', 'root');
INSERT INTO sys_admins VALUES ('su', 'root');

insert into user values(1,'8545635212','1998-03-26','abc@gmail.com','john','male','smith','pass12$','patient','');
insert into user values(2,'8545635212','1998-03-26','abc@gmail.com','patty','female','smith','pass12$','hospital admin','');
insert into user values(3,'8545635212','1998-03-26','abc@gmail.com','kenny','male','smith','pass12$','hospital admin','');
insert into user values(4,'8545635212','1998-03-26','abc@gmail.com','billy','male','smith','pass12$','doctor','');
insert into user values(5,'8545635212','1998-03-26','abc@gmail.com','peter','male','fernandez','pass12$','patient','');
insert into user values(6,'8545635212','1998-03-26','abc@gmail.com','bobby','female','denver','pass12$','patient','');

insert into doctor values('DOC999','Mon-Tue','2P.M.-5P.M.',500,5,'M.D.','Heart',4);

insert into hospital values('HOS0995','111A, Rash Behari Ave, Dover Terrace, Gariahat, Kolkata, West Bengal 700029','Fortis','033-6628-4444','https://www.fortishealthcare.com/');
insert into hospital values('HOS0597','9, Dr. U. N. Brahmachari Street (Formerly Loudon Street), Elgin, Kolkata, West Bengal 700017','Belle Vue Clinic','098361 93420','http://www.bellevueclinic.com/');

insert into hospital_admin values(1,'HOS0995',2);
insert into hospital_admin values(2,'HOS0597',3);

insert into review_questionnaire values(1, 'On a scale of 1-5,how good was your doctor/facility?');
insert into review_questionnaire values(2, 'On a scale of 1-5,how good was the hospital');

-- insert into patient table
insert into patient(patient_id,user_id) values('PAT99996',1);
insert into patient(patient_id,user_id) values('PAT99997',5);
insert into patient(patient_id,user_id) values('PAT99998',6);

-- insert into treatment_history table
insert into treatment_history(treatment_id,prescription,treatment_patient_id,treatment_doctor_id) values(1, 'Exercise and eat well','PAT99996','DOC999');
insert into treatment_history(treatment_id,prescription,treatment_patient_id,treatment_doctor_id) values(2, 'Eat and sleep well','PAT99997','DOC999');

INSERT INTO sys_admins VALUES ('admin', 'root');
INSERT INTO sys_admins VALUES ('su', 'root');
insert into review_questionnaire values(1,"On a scale of 1-5,how good was your doctor/facility?");
insert into review_questionnaire values(1,"On a scale of 1-5,how good was the hospital");
INSERT INTO patient(patient_id) values("PAT99996");
INSERT INTO patient(patient_id) values("PAT99997");
INSERT INTO patient(patient_id) values("PAT99998");

INSERT INTO treatment_history(prescription) values("Exercise and eat well");
INSERT INTO treatment_history(prescription) values("Eat and sleep well");

INSERT INTO test_result(test_name,results) values("Blood Test",null);
INSERT INTO test_result(test_name,results) values("Pressure Test",null);
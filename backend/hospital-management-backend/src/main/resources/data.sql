INSERT INTO sys_admins VALUES ('admin', 'root');
INSERT INTO sys_admins VALUES ('su', 'root');

insert into review_questionnaire values(1, 'On a scale of 1-5,how good was your doctor/facility?');
insert into review_questionnaire values(2, 'On a scale of 1-5,how good was the hospital');

insert into facility values(1,'Thyroid Test');
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(1,'TSH','0.40 - 4.50 mIU/mL',1);
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(2,'T3','100 - 200 ng/dL',1);
insert into baselines(baseline_id,baseline_name,baseline_value,facility_id) values(3,'T4','5.0 – 11.0 ug/dL',1);

-- INSERT INTO patient(patient_id) values('PAT99996');
-- INSERT INTO patient(patient_id) values('PAT99997');
-- INSERT INTO patient(patient_id) values('PAT99998');

-- INSERT INTO treatment_history values(1, 'Exercise and eat well');
-- INSERT INTO treatment_history values(2, 'Eat and sleep well');

-- INSERT INTO test_result values(1, 'Blood Test',null);
-- INSERT INTO test_result values(2, 'Pressure Test',null);
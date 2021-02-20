import { useState } from "react";
import { useParams } from "react-router-dom";

const hopitalList = [
	{ hospital_id: "H001", name: "some hospital" },
	{ hospital_id: "H002", name: "that hospital" },
];

const doctorList = [
	{ user_id: "D001", first_name: "foo" },
	{ user_id: "D002", first_name: "bar" },
];

const facilityList = [
	{ facility_id: "F001", facility_name: "more" },
	{ facility_id: "F002", facility_name: "wow" },
];

const Appointment = () => {
	const [hospitals] = useState(hopitalList);
	const [doctors] = useState(doctorList);
	const [facilities] = useState(facilityList);

	const [type, setType] = useState("");
	const [hospital, setHospital] = useState("");
	const [appointmentDate, setAppointmentDate] = useState("");
	const [appointmentTime, setAppointmentTime] = useState("");
	const [remarks, setRemarks] = useState("");
	const [report, setReport] = useState();

	const [doctor, setDoctor] = useState("");
	const [facility, setFacility] = useState("");

	const { patientId } = useParams();

    const handleSubmit = (event) => {
        event.preventDefault();
        // TODO: do later
        console.log({type, hospital, appointmentDate, appointmentTime, remarks, report, doctor, facility});
    }

	return (
		<form className="container mt-3" onSubmit={handleSubmit}>
			<h2> Book an appointment </h2>
			<div className="form-group">
				<label htmlFor="type">Type of appointment</label>
				<br />
				<div className="form-check form-check-inline">
					<input
						className="form-check-input"
						type="radio"
						name="type"
						id="doctor"
						value="doctor"
						onChange={(event) => setType(event.target.value)}
					/>
					<label className="form-check-label" htmlFor="doctor">
						Doctor
					</label>
				</div>

				<div className="form-check form-check-inline">
					<input
						className="form-check-input"
						type="radio"
						name="type"
						id="appointment"
						value="appointment"
						onChange={(event) => setType(event.target.value)}
					/>
					<label className="form-check-label" htmlFor="appointment">
						Facility
					</label>
				</div>
			</div>

			<div className="form-group">
				<label htmlFor="hospital_name">Hospital</label>
				<select
					className="form-control"
					id="hospital_name"
					value={hospital}
					onChange={(event) => setHospital(event.target.value)}
					required
				>
					<option value=""> choose an hospital </option>
					{hospitals.map((hopital) => (
						<option
							key={hopital.hospital_id}
							value={hopital.hospital_id}
						>
							{hopital.name}
						</option>
					))}
				</select>
			</div>
			<div className="form-group">
				<label htmlFor="appointment_date">Date of appointment</label>
				<input
					type="date"
					className="form-control"
					id="appointment_date"
					value={appointmentDate}
					onChange={(event) => setAppointmentDate(event.target.value)}
				/>
			</div>
			<div className="form-group">
				<label htmlFor="appointment_time">Time of appointment</label>
				<input
					type="time"
					className="form-control"
					id="appointment_time"
					value={appointmentTime}
					onChange={(event) => setAppointmentTime(event.target.value)}
				/>
			</div>
			<div className="form-group">
				<label htmlFor="remarks">Remarks</label>
				<input
					type="text"
					className="form-control"
					id="remarks"
					value={remarks}
					onChange={(event) => setRemarks(event.target.value)}
				/>
			</div>
			<div className="form-group">
				<label htmlFor="remarks">Medical Records</label>
				<div className="custom-file">
					<input
						type="file"
						className="custom-file-input"
						id="records"
						value={report}
						onChange={(event) => setReport(event.target.value)}
					/>
					<label className="custom-file-label">Choose file</label>
				</div>
			</div>
			{type === "doctor" ? (
				<div className="form-group">
					<label htmlFor="doctor_name">Doctor</label>
					<select
						className="form-control"
						id="doctor_name"
						value={doctor}
						onChange={(event) => setDoctor(event.target.value)}
						required
					>
						<option value=""> choose a doctor </option>
						{doctors.map((doctor) => (
							<option key={doctor.user_id} value={doctor.user_id}>
								{doctor.first_name}
							</option>
						))}
					</select>
				</div>
			) : (
				<div className="form-group">
					<label htmlFor="facility_name">Facilities</label>
					<select
						className="form-control"
						id="facility_name"
						value={facility}
						onChange={(event) => setFacility(event.target.value)}
						required
					>
						<option value=""> choose an facility </option>
						{facilities.map((facility) => (
							<option
								key={facility.facility_id}
								value={facility.facility_id}
							>
								{facility.facility_name}
							</option>
						))}
					</select>
				</div>
			)}
			<input type="submit" className="btn btn-primary" />
		</form>
	);
};

export default Appointment;

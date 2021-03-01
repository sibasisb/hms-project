import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import ApproveAppointmentCard from "./ApproveAppointmentCard";

const appointments = [
	{
		appointmentId: 1,
		appointmentDate: "0218-05-31",
		appointmentTime: "20:04",
		patientName: "Zer0",
		doctorName: "Doctor Strange",
		facilityName: null,
		hospitalName: "St. Peters",
		remarks: "meh",
		medicalRecords: null,
		approved: false,
	},
	{
		appointmentId: 2,
		appointmentDate: "2018-05-31",
		appointmentTime: "02:04",
		patientName: "Zer0",
		doctorName: "Watson",
		facilityName: null,
		hospitalName: "Thomas",
		remarks: "grr",
		medicalRecords: null,
		approved: true,
	},
];

const ApproveAppointments = () => {
	const [appointments, setAppointments] = useState([]);

	useEffect(() => {
		let url = "";
		const serviceId = localStorage.getItem("userId");

		if (localStorage.getItem("role") == "doctor") {
			url = `http://localhost:8080/appointments/view/doctor/${serviceId}`;
		} else {
			url = `http://localhost:8080/appointments/view/facility/${serviceId}`;
		}

		axios
			.get(url)
			.then((res) => {
				setAppointments(res.data);
			})
			.catch((error) => console.log(error));
	}, []);

	const handleApprove = (appointmentId) => {
		axios
			.get(`http://localhost:8080/appointments/approve/${appointmentId}`)
			.then((res) => {
				res.status === 200
					? console.log("approved")
					: console.log("rejected");
			})
			.catch((error) => console.log(error));
	};

	const handleReject = (appointmentId) => {
		axios
			.get(`http://localhost:8080/appointments/reject/${appointmentId}`)
			.then((res) => {
				res.status === 200
					? console.log("rejected")
					: console.log("rejected");
			})
			.catch((error) => console.log(error));
	};

	return (
		<div>
			<div className="container mt-5">
				<div className="card">
					<h4 className="card-header">
						List of appointments related to you
					</h4>
					<div className="card-body">
						<div className="card-deck">
							{appointments.map((appointment) => (
								<ApproveAppointmentCard
									appointment={appointment}
									key={appointment.appointmentId}
									handleApprove={handleApprove}
									handleReject={handleReject}
								/>
							))}
						</div>
					</div>
				</div>
			</div>
		</div>
	);
};

export default ApproveAppointments;

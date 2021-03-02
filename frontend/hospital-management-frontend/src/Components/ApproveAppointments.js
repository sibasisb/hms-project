import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import ApproveAppointmentCard from "./ApproveAppointmentCard";

const ApproveAppointments = () => {
	const [appointments, setAppointments] = useState([]);
	const [showError, setShowError] = useState(false);
	const [showReject, setShowReject] = useState(false);
	const [showApprove, setShowApprove] = useState(false);

	useEffect(() => {
		setShowError(false);
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
				if (res.data.length == 0)
					setShowError(true)
			})
			.catch((error) => console.log(error));
	}, []);

	const handleApprove = (appointmentId) => {
		setShowApprove(false);
		setShowReject(false);
		axios
			.get(`http://localhost:8080/appointments/approve/${appointmentId}`)
			.then((res) => {
				res.status === 200
					? setShowApprove(true)
					: console.log("not approved");
			})
			.catch((error) => console.log(error));
	};

	const handleReject = (appointmentId) => {
		setShowApprove(false);
		setShowReject(false);
		axios
			.get(`http://localhost:8080/appointments/reject/${appointmentId}`)
			.then((res) => {
				res.status === 200
					? setShowReject(true)
					: console.log("not rejected");
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
						{showApprove ? (
							<div className="alert alert-success"> Appointment approved successfully</div>
						) : ""}
						{showReject ? (
							<div className="alert alert-danger">Appointment rejected successfully</div>
						) : ""}
						{
							showError ?
								(<div className="alert alert-danger">
									<h5><strong>No appointments to view!!!</strong></h5>
								</div>) :
								(
									<div className="row">
										{appointments.map((appointment) => (
											<ApproveAppointmentCard
												appointment={appointment}
												key={appointment.appointmentId}
												handleApprove={handleApprove}
												handleReject={handleReject}
											/>
										))}
									</div>
								)
						}
					</div>
				</div>
			</div>
		</div>
	);
};

export default ApproveAppointments;

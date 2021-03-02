import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

const appointmentList = [
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

const ViewAppointment = () => {
	const [appointments, setAppointments] = useState([]);
	const [showError, setShowError] = useState(false)

	useEffect(() => {
		setShowError(false)
		const patientId = localStorage.getItem("userId");
		axios
			.get(`http://localhost:8080/appointments/${patientId}`)
			.then((res) => {
				setAppointments(res.data);
				if (res.data.length == 0)
					setShowError(true)
			})
			.catch((error) => console.log(error));
	}, []);

	return (
		<div className="container mt-5">
			<div className="card">
				<h4 className="card-header"> List of your appointments </h4>
				<div className="card-body">
					
						{
							showError ?
								(<div className="alert alert-danger">
									<h5><strong>No appointments to view yet!!!</strong></h5>
								</div>) :
								(<div className="card-deck">
									{appointments.map((appointment) => (
										<div
											className={
												appointment.approved
													? "card bg-light border-success mb-3"
													: "card bg-light border-secondary mb-3"
											}
											key={appointment.appointmentId}
										>
											<div className="card-body">
												<h5
													className={
														appointment.approved
															? "card-title text-success"
															: "card-title"
													}
												>
													Appointment Id:{" "}
													{appointment.appointmentId}
												</h5>
												<h6 className="card-subtitle mb-2 text-muted">
													{appointment.appointmentTime} ,{" "}
													{appointment.appointmentDate}{" "}
												</h6>
												<div className="card-text">
													{appointment.doctorName != null ? (
														<div className="row">
															<div className="col">
																Doctor Name:
												</div>
															<div className="col">
																{appointment.doctorName}
															</div>
														</div>
													) : (
															<div className="row">
																<div className="col">
																	Facility Name:
												</div>
																<div className="col">
																	{appointment.facilityName}
																</div>
															</div>
														)}
													<div className="row">
														<div className="col">
															Hospital Name:
											</div>
														<div className="col">
															{appointment.hospitalName}
														</div>
													</div>

													<div className="row">
														<div className="col">
															Patient Name:
											</div>
														<div className="col">
															{appointment.patientName}
														</div>
													</div>

													<div className="row">
														<div className="col">Remarks</div>
														<div className="col">
															{appointment.remarks}
														</div>
													</div>

													{appointment.medicalRecords != null ? (
														<div className="row">
															<div className="col">
																Medical Records
												</div>
															<div className="col">
																{
																	appointment.remarks
																	// TODO: Add BLOB support for viewd
																}
															</div>
															<div className="col">
																{appointment.remarks}
															</div>
														</div>
													) : (
															" "
														)}
												</div>
											</div>
											<div className="card-footer text-muted">
												<small>
													{appointment.approved
														? "approved"
														: "not yet approved"}
												</small>
												<small className="float-right">
													{appointment.paid ? (
														<Link
															to={`/patientreview/${appointment.appointmentId}`}
														>
															<button className="btn btn-primary">
																Provide Feedback{" "}
															</button>
														</Link>
													) : (
															""
														)}
												</small>
											</div>
										</div>
									))}
								</div>)
						}
				</div>
			</div>
		</div>
	);
};

export default ViewAppointment;

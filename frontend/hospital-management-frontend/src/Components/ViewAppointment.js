import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getHeader } from "../helpers/AuthorizationHeader";

const ViewAppointment = () => {
	const [appointments, setAppointments] = useState([]);
	const [showError, setShowError] = useState(false)

	useEffect(() => {
		setShowError(false)
		const patientId = localStorage.getItem("userId");
		axios
			.get(`http://localhost:8080/appointments/${patientId}`,getHeader())
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
							(<div className="row">
								{appointments.map((appointment) => (
								
										<div
											className={
												appointment.approved
													? "cols-md-4 card bg-light border-success m-2"
													: "cols-md-4 card bg-light border-secondary m-2"
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
													{`On ${appointment.appointmentDate[2]}-${appointment.appointmentDate[1]}-${appointment.appointmentDate[0]} at ${appointment.appointmentTime[0]}:${appointment.appointmentTime[1]}`}
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

import {
	faCheck,
	faCross,
	faSign,
	faTimes,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useState } from "react";

const ApproveAppointmentCard = ({
	appointment,
	handleApprove,
	handleReject,
}) => {
	const [approved, setApproved] = useState(appointment.approved);

	return (
		<div className="col-md-4 mb-4">
			<div
				className={
					appointment.approved
						? "card bg-light border-success"
						: "card bg-light border-secondary"
				}
				style={{ width: "100%", height: "100%" }}
				key={appointment.appointmentId}
			>
				<div className="card-body">
					<h5
						className={
							approved
								? "card-title text-success"
								: "card-title"
						}
					>
						Appointment Id: {appointment.appointmentId}
					</h5>
					<h6 className="card-subtitle mb-2 text-muted">
						{`On ${appointment.appointmentDate[2]}-${appointment.appointmentDate[1]}-${appointment.appointmentDate[0]} at ${(appointment.appointmentTime[1] < 10) ? ("0" + appointment.appointmentTime[1]) : appointment.appointmentTime[1]}:${(appointment.appointmentTime[1] < 10) ? ("0" + appointment.appointmentTime[1]) : appointment.appointmentTime[1]}`}
					</h6>
					<div className="card-text">
						{appointment.doctorName != null ? (
							<div className="row">
								<div className="col">Doctor Name:</div>
								<div className="col">{appointment.doctorName}</div>
							</div>
						) : (
								<div className="row">
									<div className="col">Facility Name:</div>
									<div className="col">
										{appointment.facilityName}
									</div>
								</div>
							)}
						<div className="row">
							<div className="col">Hospital Name:</div>
							<div className="col">{appointment.hospitalName}</div>
						</div>

						<div className="row">
							<div className="col">Patient Name:</div>
							<div className="col">{appointment.patientName}</div>
						</div>

						<div className="row">
							<div className="col">Remarks</div>
							<div className="col">{appointment.remarks}</div>
						</div>

						{appointment.medicalRecords != null ? (
							<div className="row">
								<div className="col">Medical Records</div>
								<div className="col">
									{
										appointment.remarks
										// TODO: Add BLOB support for viewd
									}
								</div>
								<div className="col">{appointment.remarks}</div>
							</div>
						) : (
								" "
							)}

						<br />

						<div className="row">
							<div className="col">
								<button
									className="btn btn-danger"
									title="Reject"
									onClick={() => {
										handleReject(appointment.appointmentId);
										setApproved(false);
									}
									}
								>
									<FontAwesomeIcon icon={faTimes} />
									{"   "}
									<span>Reject</span>
								</button>
							</div>
							<div className="col">
								<button
									className={
										approved
											? "btn btn-secondary"
											: "btn btn-primary"
									}
									title="Approve"
									onClick={() => {
										handleApprove(appointment.appointmentId);
										setApproved(true);
									}
									}
									disabled={approved ? true : false}
								>
									<FontAwesomeIcon icon={faCheck} />
									{"   "}
									<span>Approve</span>
								</button>
							</div>
						</div>
					</div>
				</div>

				<div className="card-footer text-muted">
					<small>
						{approved ? "approved" : "not yet approved"}
					</small>
				</div>
			</div>
		</div>
	);
};

export default ApproveAppointmentCard;

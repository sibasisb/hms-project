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
	const [approved, setApproved] = useState(false);

	return (
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
					Appointment Id: {appointment.appointmentId}
				</h5>
				<h6 className="card-subtitle mb-2 text-muted">
					{appointment.appointmentTime} ,{" "}
					{appointment.appointmentDate}{" "}
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
									appointment.approved
										? "btn btn-secondary"
										: "btn btn-primary"
								}
								title="Approve"
								onClick={() => {
									handleApprove(appointment.appointmentId);
									setApproved(true);
								}
								}
								disabled={appointment.approved ? true : false}
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
					{appointment.approved ? "approved" : "not yet approved"}
				</small>
			</div>
		</div>
	);
};

export default ApproveAppointmentCard;

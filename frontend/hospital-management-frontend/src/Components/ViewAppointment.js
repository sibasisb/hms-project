import { faList } from "@fortawesome/free-solid-svg-icons";

const { FontAwesomeIcon } = require("@fortawesome/react-fontawesome");

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

const ViewAppointment = () => {
	return (
		<div className="container mt-5">
			<div className="card">
				<h4 className="card-header"> List of your appointments <FontAwesomeIcon icon={faList} /> </h4>
				<div className="card-body">
					<div className="card-deck">
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
										{appointment.appointmentId}{" "}
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
								</div>
							</div>
						))}
					</div>
				</div>
			</div>
		</div>
	);
};

export default ViewAppointment;

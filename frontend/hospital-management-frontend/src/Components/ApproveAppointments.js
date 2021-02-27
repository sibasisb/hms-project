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
	const handleApprove = (appointmentId) => {
		// TODO: call /approve
	};

	const handleReject = (appointmentId) => {
		// TODO: call /reject
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

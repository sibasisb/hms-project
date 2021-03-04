import axios from "axios";
import { useEffect, useState } from "react";
import { getHeader } from "../helpers/AuthorizationHeader";

const Notification = () => {
	const [appointments, setAppointments] = useState([]);

	useEffect(() => {
		const patientId = localStorage.getItem("userId");
		axios
			.get(`http://localhost:8080/appointments/${patientId}`,getHeader())
			.then((res) => {
				setAppointments(res.data);
			})
			.catch((error) => console.log(error));
	}, []);

	return (
		<div className="container">
			
				{appointments.map((appointment) => {
				const appointDate = Date.parse(appointment.appointmentDate);
				const oneday = 60 * 60 * 24 * 1000;
				const prinet =
					appointDate - Date.now() <= oneday ? (
						<div
							key={appointment.appointmentId}
							className="alert alert-primary mt-4"
						>
							{`You have an appointment on ${appointment.appointmentDate[2]}-${appointment.appointmentDate[1]}-${appointment.appointmentDate[0]} at ${(appointment.appointmentTime[1] < 10) ? ("0" + appointment.appointmentTime[1]) : appointment.appointmentTime[1]}:${(appointment.appointmentTime[1] < 10) ? ("0" + appointment.appointmentTime[1]) : appointment.appointmentTime[1]}`}
						</div>
					) : (
						<div key={appointment.appointmentId}> </div>
					);

				return prinet;
			})}
		</div>
	);
};

export default Notification;

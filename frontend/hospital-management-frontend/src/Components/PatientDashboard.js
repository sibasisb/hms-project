import React from "react";
import hospital from "../images/hospital.png";
import appointment from "../images/appointment.png";
import status from "../images/status.png";
import billing from "../images/billing.png";
import treatment from "../images/treatment.png";
import "../styles/mystyle.css";
import testresult from "../images/testresult.png";
import { Link } from "react-router-dom";
import notification from '../images/notification.png'

function PatientDashboard() {
	const imageStyle = {
		height: "18vh",
	};
	return (
		<div className="container ">
			<h1 className="mt-5 mb-5"> Patient Dashboard</h1>
			<div className="row">
				<Link to={"/hospitals"} className="col-md-4">
					<div className="card m-4">
						<img
							className="card-img-top  embed-responsive-item"
							src={hospital}
							alt="Card image cap"
							style={imageStyle}
						/>
						<div className="card-body">
							<p className="card-text font-weight-bold">
								View Hospitals
							</p>
						</div>
					</div>
				</Link>
				<Link
					to={"/appointment/" + localStorage.getItem("userId")}
					className="col-md-4"
				>
					<div className="card m-4">
						<img
							className="card-img-top  embed-responsive-item"
							src={appointment}
							alt="Card image cap"
							style={imageStyle}
						/>
						<div className="card-body">
							<p className="card-text font-weight-bold">
								Book Appointment
							</p>
						</div>
					</div>
				</Link>
				<Link
					to={"/view-appointment/" + localStorage.getItem("userId")}
					className="col-md-4"
				>
					<div className="card m-4">
						<img
							className="card-img-top  embed-responsive-item"
							src={status}
							alt="Card image cap"
							style={imageStyle}
						/>
						<div className="card-body">
							<p className="card-text font-weight-bold">
								View Appointment Status
							</p>
						</div>
					</div>
				</Link>
			</div>

			<div className="row">
				<Link
					to={"/treatmenthistory/" + localStorage.getItem("userId")}
					className="col-md-4"
				>
					<div className="card m-4">
						<img
							className="card-img-top  embed-responsive-item"
							src={treatment}
							alt="Card image cap"
							style={imageStyle}
						/>
						<div className="card-body">
							<p className="card-text font-weight-bold">
								View Treatment History
							</p>
						</div>
					</div>
				</Link>
				<Link
					to={"/patientInfoDoc/" + localStorage.getItem("userId")}
					className="col-md-4"
				>
					<div className="card m-4">
						<img
							className="card-img-top  embed-responsive-item"
							src={testresult}
							alt="Card image cap"
							style={imageStyle}
						/>
						<div className="card-body">
							<p className="card-text font-weight-bold">
								View Test Results
							</p>
						</div>
					</div>
				</Link>
				<Link
					to={`/notifications/${localStorage.getItem("userId")}`}
					className="col-md-4"
				>
					<div className="card m-4">
						<img
							className="card-img-top  embed-responsive-item"
							src={notification}
							alt="Card image cap"
							style={imageStyle}
						/>
						<div className="card-body">
							<p className="card-text font-weight-bold">
								Notification
							</p>
						</div>
					</div>
				</Link>
			</div>
		</div>
	);
}

export default PatientDashboard;

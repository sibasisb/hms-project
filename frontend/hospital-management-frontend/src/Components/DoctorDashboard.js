import React from 'react'
import {Link} from 'react-router-dom'
import addFacility from '../images/add-facility.jpg'
import hospital from "../images/hospital.png";
import appointment from "../images/appointment.png";
import status from "../images/status.png";
import billing from "../images/billing.png";
import treatment from "../images/treatment.png";
import result from "../images/testresult.png";
import facility from "../images/facility.png";
import viewAllFacilities from '../images/view-all-facilities.jpg';
import inpatient from "../images/inpatient.png";
import viewInPatients from '../images/view-inpatients.jpg';

function DoctorDashboard() {
    const imageStyle={
        height:"18vh"
    }
    return (
        <div className="container ">
			<h1 className="mt-5 mb-5"> Doctor Dashboard</h1>
			<div className="row">
				<Link to={`/approve-appointment/${localStorage.getItem("userId")}`} className="col-md-4">
					<div className="card m-4">
						<img
							className="card-img-top  embed-responsive-item"
							src={status}
							alt="Image by Arseny Togulev"
							style={imageStyle}
						/>
						<div className="card-body">
							<p className="card-text font-weight-bold">
								View Appointments
							</p>
						</div>
					</div>
				</Link>
				<Link to={"/doctorviewpatients"} className="col-md-4">
					<div className="card m-4">
						<img
							className="card-img-top  embed-responsive-item"
							src={status}
							alt="Card image cap"
							style={imageStyle}
						/>
						<div className="card-body">
							<p className="card-text font-weight-bold">
								View Patient Info
							</p>
						</div>
					</div>
				</Link>
			</div>
		</div>
    )
}

export default DoctorDashboard

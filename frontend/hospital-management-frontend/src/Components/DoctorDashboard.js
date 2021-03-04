import React from 'react'
import {Link} from 'react-router-dom'
import status from "../images/status.png";
import patient from '../images/patient.png'

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
							src={patient}
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

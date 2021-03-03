import React, { useState,useEffect } from "react";
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
import "../styles/mystyle.css";
import { Link } from "react-router-dom";
import axios from "axios";
import { getHeader } from "../helpers/AuthorizationHeader";

function HospitalAdminDashboard() {
	const imageStyle = {
		height: "18vh",
	};

	const[name,setName]=useState("");

	useEffect(()=>{

		axios.get(`http://localhost:8080/hospitals/${localStorage.getItem("hospitalId")}`,getHeader())
		.then(res=>{
			console.log(res.data);
			setName(res.data.name);
		})
		.catch(err=>console.log(err));

	},[]);
	return (
		<div className="container ">
			<h1 className="mt-5 mb-5"> Hospital Admin Dashboard, {name}</h1>
			<div className="row">
				<Link to={"/addfacility"} className="col-md-4">
					<div className="card m-4">
						<img
							className="card-img-top  embed-responsive-item"
							src={addFacility}
							alt="Image by Arseny Togulev"
							style={imageStyle}
						/>
						<div className="card-body">
							<p className="card-text font-weight-bold">
								Add Facilities
							</p>
						</div>
					</div>
				</Link>
				<Link to={"/updatefacility"} className="col-md-4">
					<div className="card m-4">
						<img
							className="card-img-top  embed-responsive-item"
							src={facility}
							alt="Card image cap"
							style={imageStyle}
						/>
						<div className="card-body">
							<p className="card-text font-weight-bold">
								Update Facilities
							</p>
						</div>
					</div>
				</Link>
				<Link to={"/approve-appointment/" + localStorage.getItem("userId")} className="col-md-4">
					<div className="card m-4">
						<img
							className="card-img-top  embed-responsive-item"
							src={status}
							alt="Card image cap"
							style={imageStyle}
						/>
						<div className="card-body">
							<p className="card-text font-weight-bold">
								View Appointments
							</p>
						</div>
					</div>
				</Link>
			</div>
			<div className="row">
				<Link to={"/testresults"} className="col-md-4">
					<div className="card m-4">
						<img
							className="card-img-top  embed-responsive-item"
							src={result}
							alt="Card image cap"
							style={imageStyle}
						/>
						<div className="card-body">
							<p className="card-text font-weight-bold">
								Update Test Results
							</p>
						</div>
					</div>
				</Link>
				<Link to={"/testsinformation"} className="col-md-4">
					<div className="card m-4">
						<img
							className="card-img-top  embed-responsive-item"
							src={viewAllFacilities}
							alt="Image by 🇮🇩 Irwan"
							style={imageStyle}
						/>
						<div className="card-body">
							<p className="card-text font-weight-bold">
								View all facilities
							</p>
						</div>
					</div>
				</Link>
				<Link to={"/inpatientform"} className="col-md-4">
					<div className="card m-4">
						<img
							className="card-img-top  embed-responsive-item"
							src={inpatient}
							alt="Card image cap"
							style={imageStyle}
						/>
						<div className="card-body">
							<p className="card-text font-weight-bold">
								Admit Inpatients
							</p>
						</div>
					</div>
				</Link>
			</div>

			<div className="row">
				<Link to={"/inpatientlist"} className="col-md-4">
					<div className="card m-4">
						<img
							className="card-img-top  embed-responsive-item"
							src={viewInPatients}
							alt="Image By National Cancer Institute"
							style={imageStyle}
						/>
						<div className="card-body">
							<p className="card-text font-weight-bold">
								View/Update Inpatients
							</p>
						</div>
					</div>
				</Link>
				<Link to={"/billing"} className="col-md-4">
					<div className="card m-4">
						<img
							className="card-img-top  embed-responsive-item"
							src={billing}
							alt="Card image cap"
							style={imageStyle}
						/>
						<div className="card-body">
							<p className="card-text font-weight-bold">
								Billing
							</p>
						</div>
					</div>
				</Link>
			</div>
		</div>
	);
}

export default HospitalAdminDashboard;

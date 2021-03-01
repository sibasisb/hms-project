import React from 'react'
import hospital from '../images/hospital.png'
import appointment from '../images/appointment.png'
import status from '../images/status.png'
import billing from '../images/billing.png'
import treatment from '../images/treatment.png'
import '../styles/mystyle.css'
import testresult from '../images/testresult.png'
import {Link} from 'react-router-dom'

function PatientDashboard() {

    const imageStyle = {
        height: "18vh"
    }
    return (
        <div className="container ">
            <h1 className="mt-5 mb-5"> Patient Dashboard</h1>
            <div class="row">
                <Link to={"/hospitals"}>
                    <div class="card col-md-4  m-4" >
                        <img class="card-img-top  embed-responsive-item" src={hospital} alt="Card image cap" style={imageStyle} />
                        <div class="card-body">
                            <p class="card-text font-weight-bold">View Hospitals</p>
                        </div>
                    </div>
                </Link>
                <div className="col-md-4">
                    <div class="card   m-4" >
                        <img class="card-img-top embed-responsive-item" src={appointment} alt="Card image cap" style={imageStyle} />
                        <div class="card-body">
                            <p class="card-text font-weight-bold">Book Appointment</p>
                        </div>
                    </div>
                </div>
                <Link to={"/view-appointment/" + localStorage.getItem("userId")}>
                    <div class="card col-md-4  m-4" >
                        <img class="card-img-top  embed-responsive-item" src={status} alt="Card image cap" style={imageStyle} />
                        <div class="card-body">
                            <p class="card-text font-weight-bold">View Appointment Status</p>
                        </div>
                    </div>
                </Link>
            </div>

            <div class="row">
                <div className="col-md-4">
                <Link to={"/treatmenthistory/" + localStorage.getItem("userId")}>
                    <div class="card    m-4">
                        <img class="card-img-top  embed-responsive-item" src={treatment} alt="Card image cap" style={imageStyle} />
                        <div class="card-body">
                            <p class="card-text font-weight-bold">View Treatment History</p>
                        </div>
                    </div>
                </Link>
                </div>

            </div>
            <Link to={"/patientInfoDoc/" + localStorage.getItem("userId")}>
                <div class="card col-md-4  m-4" >
                    <img class="card-img-top  embed-responsive-item" src={testresult} alt="Card image cap" style={imageStyle} />
                    <div class="card-body">
                        <p class="card-text font-weight-bold">View Test Results</p>
                    </div>
                </div>
            </Link>
        </div >
    )
}

export default PatientDashboard

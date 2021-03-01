import React from 'react'
import hospital from '../images/hospital.png'
import appointment from '../images/appointment.png'
import status from '../images/status.png'
import billing from '../images/billing.png'
import treatment from '../images/treatment.png'
import result from '../images/testresult.png'
import facility from '../images/facility.png'
import inpatient from '../images/inpatient.png'
import '../styles/mystyle.css'

function HospitalAdminDashboard() {
    const imageStyle={
        height:"18vh"
    }
    return (
        <div className="container ">
            <h1 className="mt-5 mb-5"> Hospital Admin Dashboard</h1>
            <div class="row">
                <div className="col-md-4">
                <div class="card    m-4">
                    <img class="card-img-top  embed-responsive-item" src={facility} alt="Card image cap" style={imageStyle}/>
                    <div class="card-body">
                        <p class="card-text font-weight-bold">Add/Update Facilities</p>
                    </div>
                </div>
                </div>
                <div className="col-md-4">
                <div class="card   m-4" >
                    <img class="card-img-top embed-responsive-item" src={status} alt="Card image cap" style={imageStyle} />
                    <div class="card-body">
                        <p class="card-text font-weight-bold">View Appointments</p>
                    </div>
                </div>
                </div>
                <div className="col-md-4">
                <div class="card    m-4" >
                    <img class="card-img-top  embed-responsive-item" src={result} alt="Card image cap" style={imageStyle}/>
                    <div class="card-body">
                        <p class="card-text font-weight-bold">View Test Results</p>
                    </div>
                </div>
                </div>
               
            </div>

            <div class="row">
                <div className="col-md-4">
                <div class="card    m-4">
                    <img class="card-img-top  embed-responsive-item" src={inpatient} alt="Card image cap" style={imageStyle}/>
                    <div class="card-body">
                        <p class="card-text font-weight-bold">View Inpatients</p>
                    </div>
                </div>
                </div>
                <div className="col-md-4">
                <div class="card   m-4" >
                    <img class="card-img-top  embed-responsive-item" src={billing} alt="Card image cap" style={imageStyle}/>
                    <div class="card-body">
                        <p class="card-text font-weight-bold">Billing</p>
                    </div>
                </div>
                </div> 
                </div>
        </div >
    )
}

export default HospitalAdminDashboard

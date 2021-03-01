import React from 'react'
import hospital from '../images/hospital.png'
import appointment from '../images/appointment.png'
import status from '../images/status.png'
import billing from '../images/billing.png'
import treatment from '../images/treatment.png'
import '../styles/mystyle.css'

function PatientDashboard() {

    const imageStyle={
        height:"18vh"
    }
    return (
        <div className="container ">
            <h1 className="mt-5 mb-5"> Patient Dashboard</h1>
            <div class="row">
                <div className="col-md-4">
                <div class="card    m-4">
                    <img class="card-img-top  embed-responsive-item" src={hospital} alt="Card image cap" style={imageStyle}/>
                    <div class="card-body">
                        <p class="card-text font-weight-bold">View Hospitals</p>
                    </div>
                </div>
                </div>
                <div className="col-md-4">
                <div class="card   m-4" >
                    <img class="card-img-top embed-responsive-item" src={appointment} alt="Card image cap" style={imageStyle} />
                    <div class="card-body">
                        <p class="card-text font-weight-bold">Book Appointment</p>
                    </div>
                </div>
                </div>
                <div className="col-md-4">
                <div class="card    m-4" >
                    <img class="card-img-top  embed-responsive-item" src={status} alt="Card image cap" style={imageStyle}/>
                    <div class="card-body">
                        <p class="card-text font-weight-bold">View Appointment Status</p>
                    </div>
                </div>
                </div>
               
            </div>

            <div class="row">
                <div className="col-md-4">
                <div class="card    m-4">
                    <img class="card-img-top  embed-responsive-item" src={treatment} alt="Card image cap" style={imageStyle}/>
                    <div class="card-body">
                        <p class="card-text font-weight-bold">View Treatment History</p>
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

export default PatientDashboard

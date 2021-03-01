import React from 'react'
import {Link} from 'react-router-dom'
function DoctorDashboard() {
    const imageStyle={
        height:"18vh"
    }
    return (
        <div className="container">
            Doctor dashboard
            <div className="col-md-4">
                <Link to={"/doctorviewpatients"}>
                <div class="card    m-4" >
                    <img class="card-img-top  embed-responsive-item" src={""} alt="Card image cap" style={imageStyle}/>
                    <div class="card-body">
                        <p class="card-text font-weight-bold">View Test Results</p>
                    </div>
                </div>
                </Link>
            </div>
        </div>
    )
}

export default DoctorDashboard

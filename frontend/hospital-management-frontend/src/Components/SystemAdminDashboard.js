import React from 'react'
import {Link} from 'react-router-dom'
function SystemAdminDashboard() {
    const imageStyle={
        height:"18vh"
    }
    return (
        <div className="container">
            System admin dashboard.

            <div className="col-md-4">
                <Link to={"/addreviewquestion"}>
                <div class="card    m-4" >
                    <img class="card-img-top  embed-responsive-item" src={""} alt="Card image cap" style={imageStyle}/>
                    <div class="card-body">
                        <p class="card-text font-weight-bold">Add  review questions</p>
                    </div>
                </div>
                </Link>
            </div>
        </div>
    )
}

export default SystemAdminDashboard

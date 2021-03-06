import React from 'react'
import {Link} from 'react-router-dom'
import feedback from '../images/feedback.png'
import report from '../images/reports.png'
import tieup from '../images/tieup.png'
function SystemAdminDashboard() {
    const imageStyle={
        height:"18vh"
    }
    return (
        <div className="container ">
			<h1 className="mt-5 mb-5"> System Admin Dashboard</h1>
			<div className="row">
				<Link to="/addreviewquestion" className="col-md-4">
					<div className="card m-4">
						<img
							className="card-img-top  embed-responsive-item"
							src={feedback}
							alt="Image by Arseny Togulev"
							style={imageStyle}
						/>
						<div className="card-body">
							<p className="card-text font-weight-bold">
								Add review question
							</p>
						</div>
					</div>
				</Link>
				<Link to="/reports" className="col-md-4">
					<div className="card m-4">
						<img
							className="card-img-top  embed-responsive-item"
							src={report}
							alt="Image by Arseny Togulev"
							style={imageStyle}
						/>
						<div className="card-body">
							<p className="card-text font-weight-bold">
								View Reports
							</p>
						</div>
					</div>
				</Link>
				<Link to="/tieup" className="col-md-4">
					<div className="card m-4">
						<img
							className="card-img-top  embed-responsive-item"
							src={tieup}
							alt="Image by Arseny Togulev"
							style={imageStyle}
						/>
						<div className="card-body">
							<p className="card-text font-weight-bold">
								View Tie Ups
							</p>
						</div>
					</div>
				</Link>
                </div>
                </div>
    )
}

export default SystemAdminDashboard

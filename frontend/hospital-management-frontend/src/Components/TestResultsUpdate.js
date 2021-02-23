import React from 'react';
import 'bootstrap/dist/css/bootstrap.css'
import {Link} from 'react-router-dom'

const TestResultsUpdate=()=>{

    const displayPatientRecords=()=>{
        const patientIds=[
            "PAT000001","PAT000002","PAT000003","PAT000004","PAT000005"
        ]
        return (
            patientIds.map((patientId,index)=>{
                let testResultUrl="/testresults/"+patientId
                return (
                    <div className="list-group-item" key={index}>
                        {patientId}
                        <Link to={testResultUrl} key={index} style={{color:"black",textDecoration:"none"}}>
                        <span className="material-icons float-right" style={{color:"black"}}>visibility</span>
                        </Link>
                    </div>
                )
            })
        )
    }

    return (
        <div className="container">
            <div className="card mt-5 mx-auto" style={{width:"60%"}}>
                <div className="card-header">
                <h1>Requests for facilities</h1>
                </div>
                <div className="card-body">
                <div className="list-group">
                {displayPatientRecords()}
                </div>
                </div>   
            </div>
            
        </div>
    )
}

export default TestResultsUpdate;
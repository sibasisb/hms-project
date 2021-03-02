import React, { useEffect, useState} from 'react';
import 'bootstrap/dist/css/bootstrap.css'
import {Link} from 'react-router-dom'
import axios from 'axios'

const TestResultsUpdate=()=>{

    const [appointmentInfoList,setAppointmentInfoList]=useState([])
    const [showError,setShowError]=useState(false)
    useEffect(()=>{
        setShowError(false)
        const hospitalAdminId=localStorage.getItem("userId");
        axios.get('http://localhost:8080/appointments/pending/' + hospitalAdminId)
        .then(res=>{    
            console.log(res)
            setAppointmentInfoList(res.data);
            if(res.data.length==0)
                setShowError(true)
        })
        .catch(error=>{
            console.log(error);
        });

    },[])

    const displayPatientRecords=()=>{

        return (
            appointmentInfoList.map((appointmentInfo,index)=>{
                let testResultUrl="/testresults/" + appointmentInfo.patientId + "/" + appointmentInfo.appointmentId 
                return (
                    <div className="list-group-item" key={index}>
                        {appointmentInfo.patientId} - {appointmentInfo.facilityName} 
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
                <h3>Update test results</h3>
                </div>
                <div className="card-body">
                {
                    showError?
                    (<div className="alert alert-danger">
                        <h3><strong>No test result found to update!!!</strong></h3>
                    </div>):
                    (<></>)
                }
                <div className="list-group">
                {displayPatientRecords()}
                </div>
                </div>   
            </div>
            
        </div>
    )
}

export default TestResultsUpdate;
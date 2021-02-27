import React, { useEffect, useState} from 'react';
import 'bootstrap/dist/css/bootstrap.css'
import {Link} from 'react-router-dom'
import axios from 'axios'

const TestResultsUpdate=()=>{

    const [patientIds,setPatientIds]=useState([])

    useEffect(()=>{
        const hospitalAdminId="HAD0998";
        axios.get('http://localhost:8080/appointments/pending/' + hospitalAdminId)
        .then(res=>{    
            const appointmentInfoList=[]
            appointmentInfoList=res.data
            console.log(res)
            const patientIdsNew=[]
            appointmentInfoList.forEach(appointmentInfo=>{
                patientIdsNew.push(appointmentInfo.patientId)
            })
            setPatientIds(patientIdsNew)
        })
        .catch(error=>{
            console.log(error);
        });

    },[])

    const displayPatientRecords=()=>{

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
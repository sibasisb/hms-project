import React, { useEffect, useState} from 'react';
import 'bootstrap/dist/css/bootstrap.css'
import {Link} from 'react-router-dom'
import axios from 'axios'
import { faEye } from "@fortawesome/free-solid-svg-icons";

const { FontAwesomeIcon } = require("@fortawesome/react-fontawesome");

const DoctorViewingPatients=()=>{

    const [patientInfoList,setPatientInfoList]=useState([])

    useEffect(()=>{
        const doctorId="DOC0999";
        axios.get(`http://localhost:8080/patients/doc/${doctorId}`)
        .then(res=>{    
            console.log(res)
            setPatientInfoList(res.data);
        })
        .catch(error=>{
            console.log(error);
        });
    },[])

    const displayPatientRecords=()=>{

        return (
            patientInfoList.map((patientInfo,index)=>{
                let testResultUri="/patientInfoDoc/" + patientInfo.patientId 
                let testResultUrl={
                    pathname:"/patientInfoDoc",
                    state:patientInfo
                }
                return (
                    <div className="list-group-item" key={index}>
                        {patientInfo.patientId}
                        <Link to={testResultUri} key={index} style={{color:"black",textDecoration:"none"}}>
                        <FontAwesomeIcon icon={faEye} className="float-right" style={{color:"black"}}/>
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
                <h1>My Patients</h1>
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

export default DoctorViewingPatients;
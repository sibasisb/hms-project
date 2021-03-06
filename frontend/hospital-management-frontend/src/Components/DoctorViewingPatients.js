import React, { useEffect, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css'
import { Link } from 'react-router-dom'
import axios from 'axios'
import { faEye } from "@fortawesome/free-solid-svg-icons";
import { getHeader } from '../helpers/AuthorizationHeader';

const { FontAwesomeIcon } = require("@fortawesome/react-fontawesome");

const DoctorViewingPatients = () => {

    const [patientInfoList, setPatientInfoList] = useState([])
    const [showError, setShowError] = useState(false)

    useEffect(() => {
        setShowError(false)
        const doctorId = localStorage.getItem("userId");
        axios.get(`http://localhost:8080/patients/doc/${doctorId}`,getHeader())
            .then(res => {
                console.log(res)
                let nlist=res.data
                let newInfoList=[]
                let idSet=[]
                for(let obj of nlist){
                    if(!idSet.includes(obj.patientId)){
                        newInfoList.push(obj)
                    }
                    idSet.push(obj.patientId)
                }
                if (newInfoList.length == 0)
                    setShowError(true)
                setPatientInfoList(newInfoList)
            })
            .catch(error => {
                console.log(error);
            });
    }, [])

    const displayPatientRecords = () => {

        return (
            patientInfoList.map((patientInfo, index) => {
                let testResultUri = "/patientInfoDoc/" + patientInfo.patientId
                let testResultUrl = {
                    pathname: "/patientInfoDoc",
                    state: patientInfo
                }
                return (
                    <div className="list-group-item" key={index}>
                        <div className="row">
                            <div className="col-xs-12 col-sm-4 col-md-4">
                                {patientInfo.patientId}
                            </div>
                            <div className="col-xs-12 col-sm-4 col-md-4">
                                <Link to={{
                                    pathname: `/addtreatmenthistory/${patientInfo.patientId}/${localStorage.getItem("userId")}`,
                                    state: patientInfo
                                }} className="my-auto"><button className="btn btn-sm btn-info">Medical History</button></Link>
                            </div>
                            <div className="col-xs-12 col-sm-4 col-md-4">
                                <Link to={testResultUri} key={index} className="my-auto" style={{ color: "black", textDecoration: "none" }}>
                                    <button className="btn btn-sm btn-info">Patient Info</button>
                                </Link>
                            </div>
                        </div>
                    </div>
                )
            })
        )
    }

    return (
        <div className="container">
            <div className="card mt-5 mx-auto" style={{ width: "60%" }}>
                <div className="card-header">
                    <h3>My Patients</h3>
                </div>
                <div className="card-body">
                    {
                        showError ?
                            (<div className="alert alert-danger">
                                <h3><strong>No patient found!!!</strong></h3>
                            </div>) :
                            (
                                <div className="list-group">
                                    {displayPatientRecords()}
                                </div>
                            )
                    }
                </div>
            </div>
        </div>
    )

}

export default DoctorViewingPatients;
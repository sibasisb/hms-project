import React, { useEffect, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css'
import { Link } from 'react-router-dom'
import axios from 'axios'
import { faEye } from "@fortawesome/free-solid-svg-icons";

const { FontAwesomeIcon } = require("@fortawesome/react-fontawesome");

const DoctorViewingPatients = () => {

    const [patientInfoList, setPatientInfoList] = useState([])

    useEffect(()=>{
        const doctorId=localStorage.getItem("userId");
        axios.get(`http://localhost:8080/patients/doc/${doctorId}`)
            .then(res => {
                console.log(res)
                setPatientInfoList(res.data);
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
                    patientInfoList.length==0?
                    (<div className="alert alert-danger">
                        <strong>No patient found!!!</strong>
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

export default DoctorViewingPatients;
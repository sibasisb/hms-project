import React, { useEffect, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css'
import { Link, useParams } from 'react-router-dom'
import axios from 'axios'
import { faEye } from "@fortawesome/free-solid-svg-icons";
import { getHeader } from '../helpers/AuthorizationHeader';

const { FontAwesomeIcon } = require("@fortawesome/react-fontawesome");


const PatientInfoDocView = (props) => {
    const [testResultList, setTestResultList] = useState([])
    const [patientInfo, setPatientInfo] = useState(null)
    const { patientId } = useParams()
    const [showError, setShowError] = useState(false)
    useEffect(() => {
        setShowError(false)
        //fetch all test result records from test results table for this patient
        console.log(patientId)
        axios.get("http://localhost:8080/testresults/" + patientId,getHeader())
            .then(res => {
                console.log(res)
                setTestResultList(res.data)
                if (res.data.length == 0)
                    setShowError(true)
            })
            .catch(error => {
                console.log(error)
            })

        //fetch patient info for this patient
        axios.get("http://localhost:8080/patients/pat/" + patientId,getHeader())
            .then(res => {
                console.log(res)
                setPatientInfo(res.data)
            })
            .catch(error => {
                console.log(error)
            })
    }, [])



    const fetchResults = () => {
        return testResultList.map((testResult, index) => {
            return (
                <tr key={index}>
                    <td scope="row">
                        <Link to="">
                            <button type="button" class="btn btn-sm btn-info" style={{ width: "100%" }}>{testResult.resultId}</button>
                        </Link>
                    </td>
                    <td>{testResult.testName}</td>
                    <table className="table table-condensed table-bordered">

                    </table>
                    <td className="text-center">
                        <Link to={"/"}>
                            <FontAwesomeIcon icon={faEye} style={{ color: "black" }} size="lg" />
                        </Link>
                    </td>
                </tr>
            )
        })
    }

    const displayTestResultIds = () => {

        return (
            testResultList.map((testResult, index) => {
                let testResultUrl = "/testinfodocview/" + testResult.resultId
                return (
                    <div className="list-group-item" key={index}>
                        <Link to={testResultUrl}><button className="btn btn-md btn-info mx-auto">Test Result Id {testResult.resultId}</button></Link>
                    </div>
                )
            })
        )
    }

    return (
        <div className="container">
            <h3 className="text-center mb-4 mt-5">Patient {patientInfo?.patientId}</h3>
            <form className="card mt-2">
                <div className="card-header"><h3>Personal Details</h3></div>
                {
                    patientInfo !== null ?
                        (<div className="card-body">
                            <div className="form-row">
                                <div className="form-group col-xs-12 col-md-4">
                                    <label htmlFor="pfname">First Name</label><br />
                                    <input id="pfname" type="text" value={patientInfo.firstName} onChange={() => { }} disabled />
                                </div>
                                <div className="form-group col-xs-12 col-md-4">
                                    <label htmlFor="plname">Last Name</label><br />
                                    <input id="plname" type="text" value={patientInfo.lastName} onChange={() => { }} disabled />
                                </div>
                                <div className="form-group col-xs-12 col-md-4">
                                    <label htmlFor="dob">Date of Birth</label><br />
                                    <input id="dob" type="text" value={patientInfo.dateOfBirth[1] + "/" + patientInfo.dateOfBirth[2] + "/" + patientInfo.dateOfBirth[0]} onChange={() => { }} disabled />
                                </div>
                            </div>
                            <div className="form-row">
                                <div className="form-group col-xs-12 col-md-4">
                                    <label htmlFor="pgender">Gender</label><br />
                                    <input id="pgender" type="text" value={patientInfo.gender} onChange={() => { }} disabled />
                                </div>
                                <div className="form-group col-xs-12 col-md-4">
                                    <label htmlFor="pcontact">Contact</label><br />
                                    <input id="pcontact" type="text" value={patientInfo.contact} onChange={() => { }} disabled />
                                </div>
                            </div>
                        </div>) :
                        (<></>)
                }
            </form>
            <div className="card mt-5" style={{ width: "50%" }}>
                <div className="card-header">
                    <h3>Test results of patient</h3>
                </div>
                <div className="card-body">
                    {
                        showError ?
                            (<div className="alert alert-danger">
                                <h3><strong>No tests found!!!</strong></h3>
                            </div>) :
                            (<div className="list-group">
                                {displayTestResultIds()}
                            </div>)
                    }
                </div>
            </div>
        </div>
    )

}

export default PatientInfoDocView
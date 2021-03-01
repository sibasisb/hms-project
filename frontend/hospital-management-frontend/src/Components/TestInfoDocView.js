import React, { useEffect, useState} from 'react';
import 'bootstrap/dist/css/bootstrap.css'
import {Link,useParams} from 'react-router-dom'
import axios from 'axios'

const { FontAwesomeIcon } = require("@fortawesome/react-fontawesome");


const TestInfoDocView=()=>{

    const {testResultId}=useParams()
    const [testResultInfo,setTestResultInfo]=useState(null)
    const [result,setResult]=useState([])
    useEffect(() => {
        axios.get('http://localhost:8080/testresults/getbyid/' + testResultId)
        .then(res=>{
            let testResult=res.data
            setTestResultInfo(testResult)
            setResult(testResult.infos)
            console.log(testResult)
        })
        .catch(err=>{
            console.log(err)
        })

    }, [])


    const displayTestResults = () => {
        let li = []
        for (let i in result) {
            li.push([i, result[i]])
        }

        return li.map((item, index) => {
            return (
                <div className="form-group col-xs-12 col-md-4" key={index}>
                    <label htmlFor={item[0]}>{item[0]}</label><br />
                    <input id={item[0]} type="text" value={item[1]} onChange={()=>{}} disabled/>
                </div>
            )
        })
    }

    return (
        <div className="container">
            <form className="card mt-5">
                <div className="card-header">
                <h3>Test result of {testResultInfo?.patientId} {testResultInfo?.patientName?.toUpperCase()}</h3>
                </div>
                <div className="card-body">
                <div className="form-row">
                    <div className="form-group col-xs-12 col-md-4">
                        <label htmlFor="resultid">Test Result Id</label><br />
                        <input id="resultid" type="text" value={testResultInfo?.resultId} onChange={()=>{}} disabled />
                    </div>
                    <div className="form-group col-xs-12 col-md-4">
                        <label htmlFor="tname">Test Name</label><br />
                        <input id="tname" type="text" value={testResultInfo?.testName} onChange={()=>{}} disabled />
                    </div>
                    <div className="form-group col-xs-12 col-md-4">
                        <label htmlFor="aid">Appointment Id</label><br />
                        <input id="aid" type="text" value={testResultInfo?.appointmentId} onChange={()=>{}} disabled />
                    </div>
                </div>
                <div className="form-row">
                    {displayTestResults()}
                </div>
                </div>
            </form>
        </div>
    )
}

export default TestInfoDocView;
import React, { useEffect, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css'
import { Link, useParams } from 'react-router-dom'
import axios from 'axios';
import { faList } from "@fortawesome/free-solid-svg-icons";
const { FontAwesomeIcon } = require("@fortawesome/react-fontawesome");

const EditTestResult = () => {
    const { testResultId } = useParams()
    const [patientId, setPatientId] = useState("")
    const [appointmentId,setAppointmentId]=useState("")
    const [testName, setTestName] = useState("")
    const [result, setResult] = useState([])
    const [showAlert, setShowAlert] = useState(false)

    useEffect(() => {
        axios.get('http://localhost:8080/testresults/getbyid/' + testResultId)
        .then(res=>{
            let testResultInfo=res.data
            setPatientId(testResultInfo.patientId)
            setTestName(testResultInfo.testName)
            setResult(testResultInfo.infos)
            setAppointmentId(testResultInfo.appointmentId)
            setShowAlert(false)
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

        const handleChangeResult = (e) => {
            let newResult = { ...result }
            newResult[e.target.id] = e.target.value
            setResult(newResult)
        }

        return li.map((item, index) => {
            return (
                <div className="form-group col-xs-12 col-md-4" key={index}>
                    <label htmlFor={item[0]}>{item[0]}</label><br />
                    <input id={item[0]} type="text" value={item[1]} onChange={handleChangeResult} />
                </div>
            )
        })
    }

    const handleEditSubmit = (e) => {
        e.preventDefault()
        console.log(result)
        console.log(`http://localhost:8080/testresults/update/${appointmentId}/${patientId}/${testResultId}`)
        let infoList=[]
        for(let i in result){
            const infoObj={
                resultInfoName:i,
                resultInfoValue:result[i]
            }
            infoList.push(infoObj)
        } 
        //make changes to db treatment result records
        let obj={
            testName:testName,
            infos:infoList
        }
        console.log(obj)
        axios.put(`http://localhost:8080/testresults/update/${appointmentId}/${patientId}/${testResultId}`,obj)
        .then(res=>{
            console.log(res)
            setShowAlert(true)
        })
        .catch(err=>{
            console.log(err)
            setShowAlert(false)
        })
    }

    return (
        <div className="container">
            <form className="card mt-5" onSubmit={handleEditSubmit}>
                <div className="card-header"><h3>Edit Test Results</h3></div>
                <div className="card-body">
                    {
                        showAlert ?
                            (
                                <div className="alert alert-success">
                                    <strong>Success!</strong> Edited successfully!!!!
                                </div>
                            ) :
                            (<></>)
                    }
                    <div className="form-row">

                        <div className="form-group col-xs-12 col-md-4">
                            <label htmlFor="resultid">Test Result Id</label><br />
                            <input id="resultid" type="text" value={testResultId} onChange={()=>{}} disabled />
                        </div>
                        <div className="form-group col-xs-12 col-md-4">
                            <label htmlFor="patientid">Patient Id</label><br />
                            <input id="patientid" type="text" value={patientId} onChange={()=>{}} disabled />
                        </div>

                        <div className="form-group col-xs-12 col-md-4">
                            <label htmlFor="testname">Test Name</label><br />
                            <input id="testname" type="text" value={testName} onChange={()=>{}} disabled />
                        </div>

                    </div>
                    <div className="form-row">
                        {displayTestResults()}
                    </div>
                    
                    <div className="form-group">
                        <button type="submit" className="btn btn-primary ">Submit</button>   
                        <Link to="/testresults" style={{float:"right"}}><button type="submit" className="btn btn-primary ">Go Back</button></Link>                     
                    </div>
                    
                </div>
            </form>
        </div>
    )
}

export default EditTestResult
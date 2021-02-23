import React, { useEffect, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css'
import { Link, useParams } from 'react-router-dom'

const EditTestResult = () => {
    const { testResultId } = useParams()
    const [patientId, setPatientId] = useState("")
    const [appointmentId, setAppointmentId] = useState("")
    const [result, setResult] = useState()
    const [showAlert, setShowAlert] = useState(false)

    useEffect(() => {
        const newTestResult = {
            resultId: "TEST000001",
            patientId: "PAT000001",
            appointmentId: "AP000001",
            result: {
                HB_Value: "102",
                Pressure_systolic: "120",
                Pressure_dia: "120"
            }
        }
        setPatientId(newTestResult.patientId)
        setAppointmentId(newTestResult.appointmentId)
        setResult(newTestResult.result)
        setShowAlert(false)

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
        //make changes to db treatment result records
        const newTestResult = {
            resultId: testResultId,
            patientId,
            appointmentId,
            result
        }
        console.log(newTestResult)
        setShowAlert(true)
    }

    return (
        <div className="container">
            <form className="card mt-5" onSubmit={handleEditSubmit}>
                <div className="card-header"><h1>Edit Test Results</h1></div>
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
                            <label htmlFor="appointmentid">Appointment Id</label><br />
                            <input id="appointmentid" type="text" value={appointmentId} onChange={()=>{}} disabled />
                        </div>

                    </div>
                    <div className="form-row">
                        {displayTestResults()}
                    </div>
                    <div className="form-group">
                        <button type="submit" className="btn btn-primary ">Submit</button>
                    </div>
                </div>
            </form>
        </div>
    )
}

export default EditTestResult
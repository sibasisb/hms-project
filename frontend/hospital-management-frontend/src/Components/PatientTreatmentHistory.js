import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom';

function PatientTreatmentHistory(props) {

    let patientInfo=props.location.state;
    patientInfo.age=Math.floor((new Date() - new Date(patientInfo.dateOfBirth).getTime()) / 3.15576e+10)
    const {patientId,doctorId}=useParams();

    const [data, setData] = useState({});
    const [prescription, changePrescription] = useState();
    useEffect(() => {

        axios.get(`http://localhost:8080/treatmenthistory/${patientId}/${doctorId}`)
            .then(res => {
                setData(res.data)
                changePrescription(res.data?.prescription)

            }).catch(err => {
                console.log(err)
            })
    }, [])


    function saveTreatmentHistory() {

        let dataToSend = {
            treatmentId: data?.treatmentId || -1,
            prescription: prescription
        }

        console.log(dataToSend)
      
        axios.post(`http://localhost:8080/treatmenthistory/${patientId}/${doctorId}`, dataToSend)
            .then(res => {
                console.log(res)
            }).catch(err => console.log(err))
    }


    return (
        <div className="container mt-5 " style={{width:"100vh"}}>

            <div className="card">
                <div className="card-header">
                    <h1>Treatment History</h1>
                </div>
                <div className="card-body p-5">
                    <h5 className="card-title">
                        <div className="pb-3">Patient Name : {patientInfo.firstName} {patientInfo.lastName}</div>
                        <div className="pb-3">Age : {patientInfo.age}</div>
                        <div className="pb-3">Sex : {patientInfo.gender}</div>
                    </h5>
                    <div className="card-text pb-3"><div><textarea type="text" name="prescription" value={prescription} onChange={(event) => changePrescription(event.target.value)}

                        style={{
                            width:"85vh",
                            height: "30vh"
                        }}></textarea></div></div>
                    <button className="btn btn-primary btn-lg" onClick={saveTreatmentHistory}>Save</button>
                </div>
            </div>




        </div>

    )
}

export default PatientTreatmentHistory

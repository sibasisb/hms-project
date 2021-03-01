import axios from 'axios'
import React, { useEffect, useState } from 'react'

function PatientTreatmentHistory(props) {

    

    console.log(props.location.pathname.split("/"))
    let params = props.location.pathname.split("/");

    const [data, setData] = useState({});
    const [prescription, changePrescription] = useState();
    useEffect(() => {

        console.log(`http://localhost/treatmenthistory/${[params[2], params[3]].join("/")}`);
        axios.get(`http://localhost:8080/treatmenthistory/${[params[2], params[3]].join("/")}`)
            .then(res => {
                //  console.log(res.data.prescription)
                setData(res.data)
                changePrescription(res.data?.prescription)

            }).catch(err => {
                console.log(err)
            })
    }, [])


    useEffect(
        () => {
            console.log(prescription, data)
        }, [prescription, data]
    )


    function saveTreatmentHistory() {

        let dataToSend = {
            treatmentId: data?.treatmentId || -1,
            prescription: prescription
        }

        console.log(dataToSend)
        console.log(`http://localhost:8080/treatmenthistory/${[params[2], params[3]].join("/")}`);
        axios.post(`http://localhost:8080/treatmenthistory/${[params[2], params[3]].join("/")}`, dataToSend)
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
                        <div className="pb-3">Patient Name : {data?.patientName}</div>
                        <div className="pb-3">Age : {data?.age}</div>
                        <div className="pb-3">Sex : {data?.gender}</div>
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

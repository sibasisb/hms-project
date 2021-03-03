import React, { useEffect, useState } from 'react'
import 'bootstrap/dist/css/bootstrap.css'
import { useParams } from 'react-router-dom'
import axios from 'axios'
import { getHeader } from '../helpers/AuthorizationHeader'
import ReactModal from "react-modal";
import { useModal } from "react-modal-hook";

const TreatmentHistoryPatient = () => {
    const [treatmentHistoryInfo, setTreatmentHistoryInfo] = useState([])
    const { patientId } = useParams()
    const [showError, setShowError] = useState(false)
    const [treatment,setTreatment]=useState(null)
    const [showModal,hideModal] = useModal(() => (
        <div className="modal-dialog">
        <ReactModal style={{
            overlay:{
                height:"60%",
                align:"center",
                padding:"0",
                border:"none"
            },
            content : {
                top: '40%',
                left: '50%',
                right: 'auto',
                bottom: 'auto',
                marginRight: '-50%',
                transform: 'translate(-50%, -50%)'
            }
        }} isOpen>
            <div className="card mx-auto pb-4" style={{height:"100%", width:"100%"}}>
                <div className="card-header" ><h3>Prescription of patient {treatment?.patientName}</h3></div>
                <div className="card-body">
                <strong>{treatment?.prescription}</strong>
                </div>
                <button className="btn btn-primary btn-lg mx-auto" onClick={()=>{setTreatment(null)}}>Close</button>
            </div>
        </ReactModal>
        </div>
    ),[treatment]);
    useEffect(()=>{
        if(treatment!=null)
            showModal()
        else
            hideModal()
    },[treatment])

    useEffect(() => {
        setShowError(false)
        axios.get(`http://localhost:8080/treatmenthistory/${patientId}`,getHeader())
            .then(res => {
                console.log(res)
                setTreatmentHistoryInfo(res.data)
                if (res.data.length == 0)
                    setShowError(true)
            })
            .catch(err => {
                console.log(err)
            })
    }, [])

    const fetchResults = () => {

        return treatmentHistoryInfo.map((treatmentHistory, index) => {
            return (
                <tr key={index}>
                    <td className="text-center">{treatmentHistory.doctorName}</td>
                    <td className="text-center">{treatmentHistory.doctorSpecilaity}</td>
                    <td className="text-center">{treatmentHistory.gender}</td>
                    <td className="text-center"><button className="btn btn-primary" onClick={()=>setTreatment(treatmentHistory)}>Show Prescription</button></td>
                </tr>
            )
        })
    }

    return (
        <div className="container">
            <div className="card mx-auto mt-5" style={{ width: "80%" }}>
                <div className="card-header"><h3 className="">Treatment history of patient {patientId}</h3></div>
                <div className="card-body">
                    {
                        showError ?
                            (<div className="alert alert-danger">
                                <h3><strong>No treatment history found!!!</strong></h3>
                            </div>) :
                            (<>
                            <table className="table table-responsive table-bordered mt-3" >
                                <thead>
                                    <tr>
                                        <th className="text-center" scope="col">Doctor name</th>
                                        <th className="text-center" scope="col">Doctor speciality</th>
                                        <th className="text-center" scope="col">Gender</th>
                                        <th className="text-center" scope="col">Prescription</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {fetchResults()}
                                </tbody>
                            </table>
                            </>)
                    }
                </div>
            </div>
        </div>
    )
}

export default TreatmentHistoryPatient
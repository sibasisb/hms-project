import React, { useEffect, useState } from 'react'
import 'bootstrap/dist/css/bootstrap.css'
import { useParams } from 'react-router-dom'
import axios from 'axios'


const TreatmentHistoryPatient = () => {
    const [treatmentHistoryInfo, setTreatmentHistoryInfo] = useState([])
    const { patientId } = useParams()
    const [showError, setShowError] = useState(false)
    useEffect(() => {
        setShowError(false)
        axios.get(`http://localhost:8080/treatmenthistory/${patientId}`)
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
                    <td className="text-center">{treatmentHistory.prescription}</td>
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
                            (<table className="table table-responsive table-bordered mt-3" >
                                <thead>
                                    <tr>
                                        <th className="text-center" scope="col">Doctor name</th>
                                        <th className="text-center" scope="col">Doctor speciality</th>
                                        <th className="text-center" scope="col">Prescription</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {fetchResults()}
                                </tbody>
                            </table>)
                    }
                </div>
            </div>
        </div>
    )
}

export default TreatmentHistoryPatient
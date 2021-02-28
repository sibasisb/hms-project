import React, { useEffect } from 'react'
import 'bootstrap/dist/css/bootstrap.css'
import axios from 'axios'


const TreatmentHistoryPatient=()=>{
    const [treatmentHistoryInfo,setTreatmentHistoryInfo]=useState([])
    const patientId=useParams()
    useEffect(()=>{
        axios.get(`http://localhost:8080/treatmenthistory/${patientId}`)
        .then(res=>{
            console.log(res)
            setTreatmentHistoryInfo(res.data)
        })
        .catch(err=>{
            console.log(err)
        })
    },[])

    const fetchResults=()=>{
        
        return treatmentHistoryInfo.map((treatmentHistory,index)=>{
            return (
                <tr key={index}>
                    <td>treatmentHistory.doctorName</td>
                    <td>treatmentHistory.doctorSpeciality</td>
                    <td>treatmentHistory.prescription</td>    
                </tr>   
            )
        })
    }

    return (
        <div className="container">
            <h1 className="text-center mb-3 mt-5">Treatment history of patient {patientId}</h1>
            <table className="table table-responsive table-condensed mx-auto mt-3" style={{width:"60%"}} >
                <thead>
                    <tr>
                    <th scope="col">Doctor name</th>
                    <th scope="col">Doctor speciality</th>
                    <th scope="col">Prescription</th>
                    </tr>
                </thead>
                {fetchResults()}
            </table>
        </div>
    )
}

export default TreatmentHistoryPatient
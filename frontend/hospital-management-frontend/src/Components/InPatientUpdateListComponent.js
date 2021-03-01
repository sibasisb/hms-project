import React, { useEffect, useState } from 'react';
import {Link} from 'react-router-dom';
import axios from 'axios';
import {faPen } from "@fortawesome/free-solid-svg-icons";

const { FontAwesomeIcon } = require("@fortawesome/react-fontawesome");

const InPatientUpdateListComponent = (props) => {

    const[data,setData] = useState([]);
    const[display,setDisplay]=useState(false);
    useEffect(()=>{

            axios.get("http://localhost:8080/inpatients/HOS0995")
            .then(res=>{
                setData(res.data);
                setDisplay(true);
            })
            .catch(err=>console.log(err));
    },[]);

    if(display===true && data.length===0)
        return (<div className="container">
            <div className="alert alert-danger mt-5" role="alert">
                <h3>No In Patients admitted at your hospital yet!</h3>
                </div>
            </div>);
    else
        return ( 
        <div className="container">

            <br/>
            <div className="card mx-auto mt-5" style={{width:"70%"}}>
                <div className="card-header">
                    <h3>Select an In Patient to update details</h3>
                </div>
                <div className="card-body">
                    <ul className="list-group" >
                        
                        {
                            data.map((inPatient)=>{
                                let link = "/inpatientform/"+inPatient.inPatientId;
                                return(
                                    <li key={inPatient.inPatientId} className="list-group-item">
                                        <span className="h5 text-muted">{inPatient.patientId} - {inPatient.firstName} {inPatient.lastName}</span>
                                        <Link to={{pathname:link,state:{patient : inPatient}}} className="float-right text-black" > <FontAwesomeIcon style={{color:"black"}} icon={faPen} /></Link>
                                       
                                    </li>
                                )})
                        }
                        
                    
                    </ul>
                </div>
            </div>
        </div>
     );
}
 
export default InPatientUpdateListComponent;
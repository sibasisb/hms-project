import React, { useState,useEffect } from 'react';
import axios from 'axios';
import {Link} from 'react-router-dom';
import { getHeader } from '../helpers/AuthorizationHeader';

const HospitalDetailsComponent = (props) => {

    const[hospital,setHospital]=useState({});
    useEffect(() => {
        
        axios.get(`http://localhost:8080/hospitals/${props.match.params.id}`,getHeader())
        .then(res=>{
            console.log(res.data);
            setHospital(res.data);
        })
        .catch(err=>console.log(err));
      },[]);
    return ( 
        <div className="container">

            <br/>
            <div className="card mx-auto mt-5" style={{height:"60vh",width:"80vh"}}>
                <div className="card-header">
                    <h1>{hospital.name}</h1>
                </div>
                <div className="card-body" style={{fontSize:"16pt"}}>
                    <div className="mb-3"><span className="font-weight-bold ">Address : </span>{hospital.address}</div>
                    <div className="mb-3"><span className="font-weight-bold ">Contact No. : </span>{hospital.phone}</div>
                    <div className="mb-3"><span className="font-weight-bold ">Website : </span><a href={hospital.website}>{hospital.website}</a></div>
                </div>
                <div className="card-footer" style={{height:"25%"}} >

                    <Link to={{pathname:`/facilities/${hospital.hospitalId}`,state:{name:hospital.name}}} className="btn btn-primary mt-3 mr-3 btn-lg">View Facilties </Link>
                    <Link to={{pathname:`/doctors/${hospital.hospitalId}`,state:{name:hospital.name}}} className="btn btn-primary mt-3 btn-lg">View Doctors</Link>   
                </div>
                
            </div>
        </div>
     );
}
 
export default HospitalDetailsComponent;
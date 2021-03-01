import React,{useEffect,useState } from 'react';
import axios from 'axios';
import {Link} from 'react-router-dom';
import { faEye } from '@fortawesome/free-solid-svg-icons';

const { FontAwesomeIcon } = require("@fortawesome/react-fontawesome");

const AllHospitalsListComponent = (props) => {

    const [hospitalList,setHospitalList] = useState([]);
    useEffect(() => {
        
        axios.get("http://localhost:8080/hospitals")
        .then(res=>{
            console.log(res.data);
            setHospitalList(res.data)
        })
        .catch(err=>console.log(err));
      },[]);
    return ( 
        <div className="container">

            <br/>
            <div className="card mx-auto mt-5" style={{width:"70%"}}>
                <div className="card-header">
                    <h3>List of Available Hospitals</h3>
                    <h5 className="text-muted">Click on a hospital to view more details..</h5>
                </div>
                <div className="card-body">
                    <ul className="list-group" >
                        
                        {
                            hospitalList.map((hospital)=>{
                                let link = "/viewhospital/"+hospital.hospitalId;
                                console.log(link);
                                return(
                                    <li key={hospital.hospitalId} className="list-group-item">
                                       
                                            <div>{hospital.name}</div>
                                            <Link to={link} style={{color:"black"}} >
                                             
                                            <FontAwesomeIcon className="float-right" icon={faEye} /></Link>
                                     
                                        <span className=" text-muted text-break" >{hospital.address}</span>
                                       
                                        
                                       
                                    </li>
                                )})
                        }
                        
                    
                    </ul>
                </div>
            </div>
        </div>
     );
}
 
export default AllHospitalsListComponent;
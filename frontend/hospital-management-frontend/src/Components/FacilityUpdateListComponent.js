import React, { Component,useEffect,useState } from 'react';
import {Link} from 'react-router-dom';
import axios from 'axios';
import { faList, faPen } from "@fortawesome/free-solid-svg-icons";

const { FontAwesomeIcon } = require("@fortawesome/react-fontawesome");

const FacilityUpdateList = () => {
    
    const [facilityList, setFacilityList] = useState([]);
    const [display, setDisplay] = useState(false);
    useEffect(() => {
        
        axios.get("http://localhost:8080/hospitals/facilities/HOS0995")
        .then(res=>{
            console.log(res.data);
            setFacilityList(res.data);
            setDisplay(true);
        })
        .catch(err=>console.log(err));

      },[]);
    if(display===true && facilityList.length===0)
      return(
        <div className="container">
            <div className="alert alert-danger mt-5" role="alert">
                <h3>No Facilities available at your hospital yet!</h3>
            </div>
        </div>
      );
    else
    return ( 
        <div className="container">

            <br/>
            <div className="card mx-auto mt-5" style={{width:"70%"}}>
                <div className="card-header">
                    <h3>Select a Facility to update</h3>
                </div>
                <div className="card-body">
                    <ul className="list-group" >
                        
                        {
                            facilityList.map((hospitalFacility)=>{
                                let link = "/addfacility/"+hospitalFacility.hospitalFacilityId;
                                console.log(link);
                                return(
                                    <li key={hospitalFacility.hospitalFacilityId} className="list-group-item">
                                        <span className="h5 text-muted">{hospitalFacility.facility.name}</span>
                                        <Link to={link} className="float-right text-black" > <FontAwesomeIcon style={{color:"black"}} icon={faPen} /></Link>
                                       
                                    </li>
                                )})
                        }
                        
                    
                    </ul>
                </div>
            </div>
        </div>
     );
}
 
export default FacilityUpdateList;
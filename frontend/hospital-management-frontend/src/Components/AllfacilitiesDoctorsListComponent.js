import React,{useEffect,useState } from 'react';
import axios from 'axios';
import {Link} from 'react-router-dom';
import { faEye } from '@fortawesome/free-solid-svg-icons';


const { FontAwesomeIcon } = require("@fortawesome/react-fontawesome");

const AllFacilitiesDoctorsListComponent = (props) => {

    const [data,setData] = useState([]);
    const [show,setShow] = useState("");
    
    useEffect(() => {
       
        if(props.match.path.includes("facilities"))
        {
            console.log("fetching facilities");
            axios.get(`http://localhost:8080/hospitals/facilities/${props.match.params.id}`)
            .then(res=>{
                setData(res.data);
                setShow("facilities");
            })
            .catch(err=>console.log(err));
        }
        else
        {
            axios.get(`http://localhost:8080/hospitals/doctors/${props.match.params.id}`)
            .then(res=>{
                setData(res.data);
                setShow("doctors");
            })
            .catch(err=>console.log(err));
        }
    },[]);
        
    const name = props.location.state.name;
    if(show==="facilities")
    {
        if(data.length===0)
            return (<div className="container">
                <div className="alert alert-danger mt-5" role="alert">
                    <h3>No Facilities available at the hospital yet!</h3>
                    </div>
                </div>);
        else
            return ( 
            <div className="container">
                
                    <div className="card mx-auto mt-5" style={{width:"70%"}}>
                    <div className="card-header">
                        <h3>List of Facilities at { props.location.state.name}</h3>
                        <h5 className="text-muted">Click on a facility to view more details..</h5>
                    </div>
                    <div className="card-body">
                        <ul className="list-group" >    
                            {
                                data.map((hospitalFacility)=>{
                                    let link = "/viewfacility/"+hospitalFacility.hospitalFacilityId;
                                    console.log(hospitalFacility);
                                    return(
                                        <li key={hospitalFacility.hospitalFacilityId} className="list-group-item">
                                            <span className="h5 text-muted">{hospitalFacility.facility.name}</span>
                                            <Link to={{pathname: link,state:{facility:hospitalFacility}}} className="float-right text-black mt-2" > <FontAwesomeIcon style={{color:"black"}} icon={faEye} /></Link>
                    
                                        </li>
                                    )})
                            }
                        </ul>
                    </div>
                </div>
            </div>
        );
    }
    else if(show==="doctors"){

        if(data.length===0)
            return (<div className="container">
                        <div className="alert alert-danger mt-5" role="alert">
                            <h3>No Doctors available at the hospital yet!</h3>
                            </div>
                    </div>);
        else          
        return(
            <div className="container">
                
                    <div className="card mx-auto mt-5" style={{width:"70%"}}>
                    <div className="card-header">
                        <h3>List of Doctors at {props.location.state.name}</h3>
                        <h5 className="text-muted">Click on a doctor to view more details..</h5>
                    </div>
                    <div className="card-body">
                        <ul className="list-group" >    
                            {
                                data.map((doctor)=>{
                                    let link = "/viewdoctor/"+doctor.doctorId;
                                    return(
                                        <li key={doctor.doctorId} className="list-group-item">
                                            <div className="h5">Dr. {doctor.name} </div>
                                            <span className="h5 text-muted">{doctor.qualification}, {doctor.speciality} Specialist</span>
                                            <Link to={{pathname: link,state:{doctorDetails : doctor}}} className="float-right text-black" > <FontAwesomeIcon style={{color:"black"}} icon={faEye} /></Link>  
                                        </li>
                                    )})
                            }
                        </ul>
                    </div>
                </div>
            </div>

        );
    }
    else
        return (<p></p>)
}
 
export default AllFacilitiesDoctorsListComponent;
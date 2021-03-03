import React,{useEffect,useState } from 'react';
import axios from 'axios';
import {Link} from 'react-router-dom';
import { faEye } from '@fortawesome/free-solid-svg-icons';
import ReactPaginate from "react-paginate";
import { getHeader } from '../helpers/AuthorizationHeader';


const { FontAwesomeIcon } = require("@fortawesome/react-fontawesome");

const PER_PAGE = 4;

const AllFacilitiesDoctorsListComponent = (props) => {

    const [data,setData] = useState([]);
    const [show,setShow] = useState("");
    const [currentPage, setCurrentPage] = useState(0);
    
    useEffect(() => {
       
        if(props.match.path.includes("facilities"))
        {
            console.log("fetching facilities");
            axios.get(`http://localhost:8080/hospitals/facilities/${props.match.params.id}`,getHeader())
            .then(res=>{
                setData(res.data);
                setShow("facilities");
            })
            .catch(err=>console.log(err));
        }
        else
        {
            axios.get(`http://localhost:8080/hospitals/doctors/${props.match.params.id}`,getHeader())
            .then(res=>{
                setData(res.data);
                setShow("doctors");
            })
            .catch(err=>console.log(err));
        }
    },[]);

    function handlePageClick({ selected: selectedPage }) {
        setCurrentPage(selectedPage);
    }

    const offset = currentPage * PER_PAGE;

    const pageCount = Math.ceil(data.length / PER_PAGE);

        
    //const name = props.location.state.name;
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
                                data
                                .slice(offset, offset + PER_PAGE)
                                .map((hospitalFacility)=>{
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
                        <ReactPaginate
                            previousLabel={"<<"}
                            nextLabel={">>"}
                            pageCount={pageCount}
                            onPageChange={handlePageClick}
                            containerClassName={"pagination justify-content-center mt-2"}
                            pageClassName={"page-item"}
                            pageLinkClassName={"page-link"}
                            activeClassName={"page-item active"}
                            disabledClassName={"page-item disabled"}
                            previousClassName={"page-item"}
                            previousLinkClassName={"page-link"}
                            nextClassName={"page-item"}
                            nextLinkClassName={"page-link"}
                        />
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
                                data
                                .slice(offset, offset + PER_PAGE)
                                .map((doctor)=>{
                                    let link = "/viewdoctor/"+doctor.doctorId;
                                    return(
                                        <li key={doctor.doctorId} className="list-group-item">
                                            <div className="h6">Dr. {doctor.name} </div>
                                            <span className="h6 text-muted">{doctor.qualification}, {doctor.speciality} </span>
                                            <Link to={{pathname: link,state:{doctorDetails : doctor}}} className="float-right text-black" > <FontAwesomeIcon style={{color:"black"}} icon={faEye} /></Link>  
                                        </li>
                                    )})
                            }
                        </ul>
                        <ReactPaginate
                            previousLabel={"<<"}
                            nextLabel={">>"}
                            pageCount={pageCount}
                            onPageChange={handlePageClick}
                            containerClassName={"pagination justify-content-center mt-2"}
                            pageClassName={"page-item"}
                            pageLinkClassName={"page-link"}
                            activeClassName={"page-item active"}
                            disabledClassName={"page-item disabled"}
                            previousClassName={"page-item"}
                            previousLinkClassName={"page-link"}
                            nextClassName={"page-item"}
                            nextLinkClassName={"page-link"}
                        />
                    </div>
                </div>
            </div>

        );
    }
    else
        return (<p></p>)
}
 
export default AllFacilitiesDoctorsListComponent;
import React, { useState,useEffect } from 'react';
import axios from 'axios';
import { getHeader } from '../helpers/AuthorizationHeader';
import ReactPaginate from "react-paginate";


const PER_PAGE = 3;
const CentralDoctorComponent = (props) => {

    const [currentPage, setCurrentPage] = useState(0);
    const[doctors,setDoctors]=useState([]);

    useEffect(() => {
        
        axios.get("http://localhost:8080/doctors",getHeader())
        .then(res=>{
            console.log(res.data);
            setDoctors(res.data)
        })
        .catch(err=>console.log(err));
    },[]);

    function handlePageClick({ selected: selectedPage }) {
        setCurrentPage(selectedPage);
    }

    const offset = currentPage * PER_PAGE;

    const pageCount = Math.ceil(doctors.length / PER_PAGE);

    return (

        <div className="container">
            <div className="card mx-auto mt-5" style={{width:"70%"}}>
                <div className="card-header">
                    <h3>List of Available Doctors</h3>
                </div>
                <div className="card-body">
                    <ul className="list-group" >

                       
                        {
                           doctors
                            .slice(offset, offset + PER_PAGE)
                            .map((doctor)=>{
                                return(
                                    <li key={doctor.doctorId} className="list-group-item">
                                       
                                            <div className="h6">Dr. {doctor.name}</div>
                                            <span className="h6 text-muted" >{doctor.qualification},{doctor.speciality}</span><br/>
                                            <span className="h6 text-muted" >{doctor.experience} years of experience</span><br/>
                                            <span className="h6 text-muted" > Available at : 
                                            {doctor.hospitalNames.map((hospital,i)=>{
                                               return(
                                                   <span className="font-weight-bold" key={i}>  {hospital}</span>
                                               );
                                            })}
                                            </span>
                                            <br/>
                                            <span className="h6 text-muted">Timings: {doctor.availableDays} {doctor.availableTime}</span>
                                       
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
 
export default CentralDoctorComponent;
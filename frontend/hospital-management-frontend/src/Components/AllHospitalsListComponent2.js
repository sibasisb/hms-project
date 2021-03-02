import React,{useEffect,useState } from 'react';
import axios from 'axios';
import {Link} from 'react-router-dom';
import { faEye } from '@fortawesome/free-solid-svg-icons';
import ReactPaginate from "react-paginate";
const { FontAwesomeIcon } = require("@fortawesome/react-fontawesome");

const PER_PAGE = 4;

const AllHospitalsList = (props) => {

    const [currentPage, setCurrentPage] = useState(0);
    const [hospitalList,setHospitalList] = useState([]);

    useEffect(() => {
        
        axios.get("http://localhost:8080/hospitals")
        .then(res=>{
            console.log(res.data);
            setHospitalList(res.data)
        })
        .catch(err=>console.log(err));
    },[]);

    function handlePageClick({ selected: selectedPage }) {
        setCurrentPage(selectedPage);
    }

    const offset = currentPage * PER_PAGE;

    const pageCount = Math.ceil(hospitalList.length / PER_PAGE);

    return (  

        <div className="container">
            <div className="card mx-auto mt-5" style={{width:"70%"}}>
                <div className="card-header">
                    <h3>List of Available Hospitals</h3>
                    <h5 className="text-muted">Click on a hospital to view more details..</h5>
                </div>
                <div className="card-body">
                    <ul className="list-group" >

                       
                        {
                            hospitalList
                            .slice(offset, offset + PER_PAGE)
                            .map((hospital)=>{
                                let link = "/viewhospital/"+hospital.hospitalId;
                                console.log(link);
                                return(
                                    <li key={hospital.hospitalId} className="list-group-item">
                                       
                                            <div className="h6">{hospital.name}</div>
                                            <span className="h6 text-muted" >{hospital.address}</span>
                                            <Link to={link} style={{color:"black"}}><FontAwesomeIcon className="float-right" icon={faEye} /></Link> 
                                       
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
 
export default AllHospitalsList;


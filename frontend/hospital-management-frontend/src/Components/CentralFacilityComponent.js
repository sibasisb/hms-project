import React,{useState,useEffect} from 'react';
import axios from 'axios';
import ReactPaginate from "react-paginate";
import { getHeader } from '../helpers/AuthorizationHeader';



const PER_PAGE = 4;

const CentralFacilityComponent = (props) => {

    const [currentPage, setCurrentPage] = useState(0);
    const [facilityList,setFacilityList] = useState([]);

    useEffect(() => {
        
        axios.get("http://localhost:8080/facilities/", getHeader())
        .then(res=>{
            console.log(res.data);
            setFacilityList(res.data)
        })
        .catch(err=>console.log(err));
    },[]);

    function handlePageClick({ selected: selectedPage }) {
        setCurrentPage(selectedPage);
    }

    const offset = currentPage * PER_PAGE;

    const pageCount = Math.ceil(facilityList.length / PER_PAGE);
   
    return (  

        <div className="container">
            <div className="card mx-auto mt-5" style={{width:"70%"}}>
                <div className="card-header">
                    <h3>List of facilities in the system</h3>
                    <p className="text-muted">* Facility charges vary according to hospitals.Check hospital facilities to view more details.</p>
                </div>
                <div className="card-body">
                    <ul className="list-group" >                      
                        {
                            facilityList
                            .slice(offset, offset + PER_PAGE)
                            .map((facility)=>{
                                return(
                                    <li key={facility.facilityId} className="list-group-item">
                                            <div className="h6">{facility.name}</div>
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
 
export default CentralFacilityComponent;
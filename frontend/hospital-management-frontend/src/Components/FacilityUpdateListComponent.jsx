import React, { Component } from 'react';
import {Link} from 'react-router-dom'

const FacilityUpdateList = () => {
    const facilityList=[
        {facility_id:"F001" ,facility_name:"XRay"},
        {facility_id:"F002",facility_name:"ECG"},
        {facility_id:"F003",facility_name:"Blood Test"},
        {facility_id:"F004",facility_name:"Physiotherapy"}
]
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
                            facilityList.map((facility)=>{
                                let link = "/addfacility/"+facility.facility_id;
                                console.log(link);
                                return(
                                    <li key={facility.facility_id} className="list-group-item">
                                        <Link to={link} className="h5 text-muted" style={{textDecoration:"none"}}>{facility.facility_name}</Link>
                                        <i className="material-icons float-right">create</i>
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
import React, { useState } from 'react';
import AllHospitalsListComponent from './AllHospitalsListComponent';
import CentralFacilityComponent from './CentralFacilityComponent';
import CentralDoctorComponent from './CentralDoctorsComponent';

const SystemAdminReportingComponent = (props) => {

    const [reportName,setReportName]=useState("");
    const [component,setComponent]= useState("");

    const handleChange = (event) => {
        setReportName(event.target.value);
    }

    function getReport(){

        setComponent(reportName);
    }

    function showComponent(){

        if(component==="Hospitals")
            return <AllHospitalsListComponent />
        else if(component==="Facilities")
            return <CentralFacilityComponent />
        else if(component==="Doctors")
           return <CentralDoctorComponent />
        else
            return null;

    }

    return (  
        <div className="container">
       
            <div className="card mt-5">
                <div className="card-header">
                    <h3> Choose an option to view reports </h3>
                </div>
                <div className="card-body">
                    <div className="row">
                        <div className="form-group col-sm-8">
                            <select className="custom-select" name="reportName" onChange={handleChange}>
                                <option  value="choose">choose..</option>
                                <option  value="Hospitals">Hospitals</option>
                                <option  value="Facilities">Facilities</option>
                                <option  value="Doctors">Doctors</option>     
                            </select>
                        </div>
                        <div className="form-group col-sm-4">
                            <button onClick={getReport} className ="form-control btn btn-primary" >Generate Report</button>
                        </div>
                    </div>
                </div>
            </div>
            {showComponent()}
        </div>
    );
}
 
export default SystemAdminReportingComponent;
import React,{useEffect,useState} from 'react';
import axios from 'axios';

const FacilityDetailsComponent = (props) => {
    const[facility,setFacility]=useState({});
    // useEffect(() => {
        
    //     axios.get(`http://localhost:8080/hospitalfacility/${props.match.params.id}`)
    //     .then(res=>{
    //         console.log(res.data);
    //         setFacility(res.data);
    //     })
    //     .catch(err=>console.log(err));
    //   },[]);

    if(props.match.path.includes("facility")) {
        const data = props.location.state.facility;
        console.log(facility);
        return ( 
            <div className="container">

                <br/>
                <div className="card mx-auto mt-5" style={{width:"80vh"}}>
                    <div className="card-header">
                        <h1>{data.facility.name}</h1>
                    </div>
                    <div className="card-body" style={{fontSize:"16pt"}}>
                        <div className="mb-3"><span className="font-weight-bold ">Description : </span>{data.description}</div>
                        <div className="mb-3"><span className="font-weight-bold ">Remarks : </span>{data.remarks}</div>
                        <div className="mb-3"><span className="font-weight-bold ">Charges : </span> Rs. {data.charges}</div>
                    </div>
                    
                    
                </div>
            </div>
        );
    }
    else{

        const data = props.location.state.doctorDetails;
        return(
            <div className="container">

                <br/>
                <div className="card mx-auto mt-5" style={{width:"80vh"}}>
                    <div className="card-header">
                        <h1>Dr. {data.name}</h1>
                    </div>
                    <div className="card-body" style={{fontSize:"16pt"}}>
                        <div className="mb-3"><span className="font-weight-bold ">Qualification : </span>{data.qualification} , {data.speciality}</div>
                        <div className="mb-3"><span className="font-weight-bold ">Experience : </span>{data.experience} years</div>
                        <div className="mb-3"><span className="font-weight-bold ">Available on  : </span>{data.availableDays} {data.availableTime}</div>
                        <div className="mb-3"><span className="font-weight-bold ">Consultation Fees  : </span>Rs. {data.charge}</div>
                    </div>
                    
                    
                </div>
            </div>
        );

    }
}
 
export default FacilityDetailsComponent;
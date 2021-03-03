import React, { useState } from 'react';
import axios from 'axios';
import { getHeader } from '../helpers/AuthorizationHeader';

const BillingComponent = (props) => {

    const initialState={
        tax:0,
        otherCharges:0,
        total:0,
        displayBills:false,
        showPaid:false
    }
    const[bills,setBills]=useState([]);
    const[state,setState]= useState(initialState);
    const[patientId,setPatientId]=useState("");
    const hospitalId = localStorage.getItem("hospitalId");
    const handleChange = (event) => {
        setPatientId(event.target.value);
    }

   function getBills(event){
        event.preventDefault();
            console.log("submit called");
            console.log(`http://localhost:8080/billing/${hospitalId}/${patientId}`)
            axios.get(`http://localhost:8080/billing/${hospitalId}/${patientId}`,getHeader())
            .then(res=>{
                console.log(res.data);
               setBills(res.data);
               setState({...state,displayBills:true});
            })
            .catch(err=>console.log(err));
    

    }

    const showBills=()=>{
        if(state.displayBills)
        {
            if(bills.length===0)
                return (
                <div className="alert alert-info mt-2" role="alert">
                No Pending Bills.
              </div>
            );
            else
                return(
                    <React.Fragment>
                    <div className="card mt-2">
                    <div className="card-header">
                        <h6>Patient ID : {bills[0].patientId}</h6>
                        <h6>Patient Name : {bills[0].patientName}</h6>
                    </div>
                    <div className="card-body">
                        {
                           
                            bills.map((bill,index)=>{
                                let serviceName = bill.facilityName||bill.doctorName||"In Patient Services";
                                let serviceCharge = bill.facilityCharge||bill.doctorCharge||bill.roomCharges;
                                let daysCount=1;
                                if(serviceName==="In Patient Services")
                                {
                                    const d1=Date.parse(bill.admissionDate);
                                    const d2=Date.parse(bill.dischargeDate);
                                    daysCount=(d2-d1)/(60 * 60 * 24 * 1000);
                                    serviceName = serviceName+" : Stay duration : "+daysCount+ " day(s)";

                                }
                                charges+=(daysCount*serviceCharge);

                                return(
                                <React.Fragment key={index}>
                                    <div>
                                        <span className="font-weight-bold">{bill.doctorName?"Doctor" :"Service"} Name :</span>
                                        <span className="float-right">{serviceName}</span>
                                    </div>
                                    <div>
                                        <span className="font-weight-bold">{bill.doctorName?"Doctor Consultation" :"Service"} Charge :</span>
                                        <span className="float-right"> Rs. {serviceCharge} {serviceName.includes("Stay")?"/day" : ""}</span>
                                    </div>
                                    <hr/>
                                </React.Fragment>

                                )
                            })
                        } 
                    </div> 
                    <div className="card-footer">
                        <div>
                            <span className="font-weight-bold">Total Amount : </span>
                            <span className="float-right text-success"> Rs. {charges}</span>
                        </div>

                    </div>

                </div>
                
                <div className="d-flex justify-content-center">
                    <div className="btn btn-primary btn-lg mt-2 " onClick={payBills} style={{width :"50%"}}>Pay Bills</div>
                </div>
               
                   
                
                </React.Fragment>
                );
        }
        else
            return (<p></p>)
    }

    const payBills=()=>{

        axios.put(`http://localhost:8080/billing/pay/${hospitalId}/${patientId}`,{},getHeader())
        .then(res=>{console.log(res);
            setState({...state,showPaid:true});
        })
        .catch(err=>console.log(err));

    }
    const showPaid=()=>{

        if(state.showPaid)
            return (
                <div className="alert alert-success mt-2" role="alert">
               Bills Paid Successfully
              </div>
            );
    }

    let charges = 0;
   

    return(
        <div className="container">
       
            <form onSubmit={getBills} className="card mt-5">
                <div className="card-header">
                    <h3> Enter Patient Id to fetch Bills </h3>
                </div>
                <div className="card-body">
                    <div className="row">
                        <div className="form-group col-sm-8">
                            <input type="text" className="form-control" id="patientId" name="patientId" value={state.patientId} onChange={handleChange} />
                        </div>
                        <div className="form-group col-sm-4">
                            <input type="submit" className ="form-control btn btn-primary" value="Generate Bill"/>
                        </div>
                    </div>
                </div>
            </form>
                {showPaid()}
                {showBills()}
        </div>
    );
}
 
export default BillingComponent;
import React,{useEffect, useState} from 'react';
import axios from 'axios';
import { getHeader } from '../helpers/AuthorizationHeader';

const InPatientFormComponent = (props) => {

    const initialState={
        admissionDate:"",
        admissionTime:"",
        dischargeDate:"",
        dischargeTime:"",
        roomCharges:0,
        patientId:"",
        hospitalId:localStorage.getItem("hospitalId"),
        inPatientId:Object.keys(props.match.params).length === 0?"":props.match.params.id,
        errors:{
            hospitalId:"",
            admissionDate:"",
            admissionTime:"",
            dischargeDate:"",
            dischargeTime:"",
            roomCharges:"",
            patientId:"",
            display_success:false,
            display_error:false
        }
    }

    const[state,setState]= useState(initialState);

    useEffect(()=>{

        if(state.inPatientId !== "")
        {
            const {patient} = props.location.state;
            setState({...state,
                admissionDate:formatDate(patient.admissionDate),
                admissionTime:formatTime(patient.admissionTime),
                dischargeDate:formatDate(patient.dischargeDate),
                dischargeTime:formatTime(patient.dischargeTime),
                roomCharges:patient.roomCharges,
                patientId:patient.patientId,
            })
        }

    },[]);

    const formatDate=(date)=>{
        const [year,month,day]= date;
        let mm="";
        let dd= ""
        if(month<10)
            mm=mm+"0"+month;
        else
            mm=month;
        if(day<10)
            dd=dd+"0"+day;
        else
            dd=day;
        return year+"-"+mm+"-"+dd;

    }

    const formatTime=(time)=>{
        const[hour,minutes]=time;
        let hh="";
        let mm="";
        if(hour<10)
            hh=hh+"0"+hour;
        else
            hh=hour;
        if(minutes<10)
            mm=mm+"0"+minutes;
        else
            mm=minutes;
        return hh+":"+mm;
    }

    const handleChange = (event) => {
        setState({
            ...state,
            [event.target.name]: event.target.value
        })
    }

    function handleSubmit(event){
        event.preventDefault();
        let res = handleValidation();
       // console.log(res);
        if(res){
            
           // console.log(body);

            if(state.inPatientId===""){

                const body={
                    admissionDate:state.admissionDate,
                    dischargeDate:state.dischargeDate,
                    admissionTime:state.dischargeTime,
                    dischargeTime:state.dischargeTime,
                    roomCharges:state.roomCharges,
                    paid:false
                    }
                axios.post(`http://localhost:8080/inpatients/add/${localStorage.getItem("hospitalId")}/${state.patientId}`,body,getHeader())
                .then(res=>{console.log(res)
                setState({...state,errors:{
                    hospitalId:"",
                    admissionDate:"",
                    admissionTime:"",
                    dischargeDate:"",
                    dischargeTime:"",
                    roomCharges:"",
                    patientId:"",
                    display_success:true,
                    display_error:false
                }})
                })
                .catch(err=>{
                    console.log(err);
                    setState({...state,errors:{...state.errors,display_success:false,display_error:true,patientId:"Invalid Patient Id"}});
                });
            }
            else{

                const body={
                    inPatientId:props.match.params.id,
                    admissionDate:state.admissionDate,
                    dischargeDate:state.dischargeDate,
                    admissionTime:state.dischargeTime,
                    dischargeTime:state.dischargeTime,
                    roomCharges:state.roomCharges,
                    paid:false
                    }
                axios.post(`http://localhost:8080/inpatients/update/${localStorage.getItem("hospitalId")}/${state.patientId}`,body,getHeader())
                .then(res=>{console.log(res)
                    setState({...state,errors:{
                        hospitalId:"",
                        admissionDate:"",
                        admissionTime:"",
                        dischargeDate:"",
                        dischargeTime:"",
                        roomCharges:"",
                        patientId:"",
                        display_success:true,
                        display_error:false
                    }})
                    })
                    .catch(err=>{
                        console.log(err);
                        setState({...state,errors:{...state.errors,display_success:false,display_error:true,patientId:"Invalid Patient Id"}});
                    });

            }
        }


    }

    const handleValidation=()=>{
        let valid=true;
        let temp = {
            hospitalId:"",
            admissionDate:"",
            admissionTime:"",
            dischargeDate:"",
            dischargeTime:"",
            roomCharges:"",
            patientId:"",
            display_success:false,
            display_error:false
        };

        if(state.hospitalId==="")
        {
            valid=false;
            temp.hospitalId="Please provide a valid hospital ID";
        }
        if(state.patientId==="")
        {
            valid=false;
            temp.patientId="Please provide a valid inPatient ID";
        }
        if(state.admissionDate==="")
        {
            valid=false;
            temp.admissionDate="Please provide an admission date";
        }
        if(state.admissionTime==="")
        {
            valid=false;
            temp.admissionTime="Please provide an admission time";
        }
        if(state.dischargeDate==="")
        {
            valid=false;
            temp.dischargeDate="Please provide an discharge time";
        }
        if(state.dischargeTime==="")
        {
            valid=false;
            temp.dischargeTime="Please provide an discharge time";
        }
        if(state.roomCharges===0 || state.roomCharges==="")
        {
            valid=false;
            temp.roomCharges="Please provide room charges";
        }
        else
            temp.roomCharges=""; 

        temp.display_error = !valid;
      //  temp.display_success  = valid;
       // console.log(temp);
        setState({...state,errors:temp})
        if(valid)
            return true;
        else
            return false;

    }
    const displayAlert=()=>{
      
        if(state.errors.display_error)
            return(
                <div className="alert alert-danger" role="alert">
                Please update the highlighted mandatory field(s).
                </div>
            )
        else
            return null;
    }
   const displaySuccess=()=>{
      
        if(state.errors.display_success)
            return(
                <div className="alert alert-success" role="alert">
                    Form has been successfully submitted!
                </div>
            )
        else
            return null;
    }
    console.log(state.inPatientId);
    if(state.inPatientId==="")
        return ( 

            <React.Fragment>
                <div className="container">
                    <br/>
                    
                    <form  className="card" onSubmit={handleSubmit}>
                        <div className="card-header">
                            <h3>Admit a new In Patient  </h3>
                        </div>
                        <div className="card-body">
                           {displayAlert()}
                           {displaySuccess()}
                        <div className="row">
                            <div className="form-group col-md-6 col-sm-12">
                                <label >Hospital ID</label>
                                <input type="text" className={state.errors.hospitalId===""?"form-control":"form-control is-invalid"} id="hospitalId" name="hospitalId" value={state.hospitalId} onChange={()=>{}} disabled/>
                            </div>
                            <div className="form-group col-md-6 col-sm-12">
                                <label >Patient ID</label>
                                <input type="text" className={state.errors.patientId===""?"form-control":"form-control is-invalid"} id="patientId" name="patientId" value={state.patientId}  onChange={handleChange}/>
                                <p className="text-danger text-small">{state.errors.patientId==="Invalid Patient Id"?state.errors.patientId:null}</p>
                            </div>
                        </div>

                        <div className="row">
                            <div className="form-group col-md-6 col-sm-12">
                                <label >Admission Date</label>
                                <input type="date" className={state.errors.admissionDate===""?"form-control":"form-control is-invalid"} id="admissionDate" name="admissionDate" value={state.admissionDate} onChange={handleChange} />
                            </div>
                            <div className="form-group col-md-6 col-sm-12">
                                <label >Admission Time</label>
                                <input type="time" className={state.errors.admissionTime===""?"form-control":"form-control is-invalid"} id="admissionTime" name="admissionTime" value={state.admissionTime}  onChange={handleChange}/>
                               
                            </div>
                        </div>

                        <div className="row">
                            <div className="form-group col-md-6 col-sm-12">
                                <label >Discharge Date</label>
                                <input type="date" className={state.errors.dischargeDate===""?"form-control":"form-control is-invalid"} id="dischargeDate" name="dischargeDate" value={state.dischargeDate} onChange={handleChange} />
                            </div>
                            <div className="form-group col-md-6 col-sm-12">
                                <label >Discharge Time</label>
                                <input type="time" className={state.errors.dischargeTime===""?"form-control":"form-control is-invalid"} id="dischargeTime" name="dischargeTime" value={state.dischargeTime}  onChange={handleChange}/>
                               
                            </div>
                        </div>
                        <div className="row">
                            <div className="form-group col-md-6 col-sm-12">
                                <label>Room Charge</label>
                                <input type="number" className={state.errors.roomCharges===""?"form-control":"form-control is-invalid"} id="roomCharges" name="roomCharges" value={state.roomCharges} onChange={handleChange} />
                                {/* <p className="text-danger text-small">{state.errors.roomCharges==="Amount should be a number in Rupees"?state.errors.roomCharges:null}</p> */}
                            </div>
                        </div>
                        
                        <div className=" row">
                            <div className="form-group col-md-6 col-sm-12">
                                <input type="submit" className ="btn btn-primary" value="Admit In Patient"/>
                            </div>
                        </div>
                    </div>
                    </form>
                </div>
            </React.Fragment>


        );
    else
    { 
        const {patient} = props.location.state;
        console.log(state);
        return(
        <React.Fragment>
        <div className="container">
            <br/>
            
            <form  className="card" onSubmit={handleSubmit}>
                <div className="card-header">
                    <h3>Update In Patient  </h3>
                    <h4 className="text-muted">{patient.firstName} {patient.lastName}</h4>
                    <h4 className="text-muted">{patient.gender}</h4>
                </div>
                <div className="card-body">
                   {displayAlert()}
                   {displaySuccess()}
                <div className="row">
                    <div className="form-group col-md-6 col-sm-12">
                        <label >Hospital ID</label>
                        <input type="text" className={state.errors.hospitalId===""?"form-control":"form-control is-invalid"} id="hospitalId" name="hospitalId" value={state.hospitalId} onChange={handleChange} />
                    </div>
                    <div className="form-group col-md-6 col-sm-12">
                        <label >Patient ID</label>
                        <input type="text" className={state.errors.patientId===""?"form-control":"form-control is-invalid"} id="patientId" name="patientId" value={state.patientId}  onChange={handleChange}/>
                       
                    </div>
                </div>

                <div className="row">
                    <div className="form-group col-md-6 col-sm-12">
                        <label >Admission Date</label>
                        <input type="date" className={state.errors.admissionDate===""?"form-control":"form-control is-invalid"} id="admissionDate" name="admissionDate" value={state.admissionDate} onChange={handleChange} />
                    </div>
                    <div className="form-group col-md-6 col-sm-12">
                        <label >Admission Time</label>
                        <input type="time" className={state.errors.admissionTime===""?"form-control":"form-control is-invalid"} id="admissionTime" name="admissionTime" value={state.admissionTime}  onChange={handleChange}/>
                       
                    </div>
                </div>

                <div className="row">
                    <div className="form-group col-md-6 col-sm-12">
                        <label >Discharge Date</label>
                        <input type="date" className={state.errors.dischargeDate===""?"form-control":"form-control is-invalid"} id="dischargeDate" name="dischargeDate" value={state.dischargeDate} onChange={handleChange} />
                    </div>
                    <div className="form-group col-md-6 col-sm-12">
                        <label >Discharge Time</label>
                        <input type="time" className={state.errors.dischargeTime===""?"form-control":"form-control is-invalid"} id="dischargeTime" name="dischargeTime" value={state.dischargeTime}  onChange={handleChange}/>
                       
                    </div>
                </div>
                <div className="row">
                    <div className="form-group col-md-6 col-sm-12">
                        <label>Room Charge</label>
                        <input type="number" className={state.errors.roomCharges===""?"form-control":"form-control is-invalid"} id="roomCharges" name="roomCharges" value={state.roomCharges} onChange={handleChange} />
                        {/* <p className="text-danger text-small">{state.errors.patientId==="Invalid Patient Id"?state.errors.patientId:null}</p> */}
                    </div>
                </div>
                
                <div className=" row">
                    <div className="form-group col-md-6 col-sm-12">
                        <input type="submit" className ="btn btn-primary" value="Admit In Patient"/>
                    </div>
                </div>
            </div>
            </form>
        </div>
    </React.Fragment>
        );
    }
   
}
 
export default InPatientFormComponent;
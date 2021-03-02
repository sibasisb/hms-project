import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.css'
import axios from 'axios';

class FacilityAddUpdateComponent extends Component {



    constructor(props) {
        super(props);
        this.state = { 
            hospitalId: localStorage.getItem("hospitalId"),
            hospitalName:"Fortis",
            facility:"",
            charges:"",
            desc:"",
            remarks:"",
            facilityList:[],
            facilityId:Object.keys(this.props.match.params).length === 0?"":this.props.match.params.id,
            errors:{
                hospitalId:"",
                hospitalName:"",
                facility:"",
                charges:"",
                desc:"",
                display_success:false,
                display_error:false
            }
         }
         console.log(this.props.match.params.id);
    }
    handleChange=(event)=> {
        this.setState({[event.target.name]:event.target.value});
      }

    componentDidMount(){

        axios.get(`http://localhost:8080/hospitals/${this.state.hospitalId}`)
        .then(res1=>{

            if(this.state.facilityId !== ""){
                axios.get(`http://localhost:8080/hospitalfacility/${this.props.match.params.id}`)
                .then(res=>{
                    this.setState({
                       facility: res.data.facilityName,
                       hospitalName:res1.data.name,
                       desc:res.data.description,
                       charges:res.data.charges,
                       remarks:res.data.remarks
                    })
                })
                .catch(err=>console.log(err));
             }
             else{
                 axios.get("http://localhost:8080/facilities/")
                 .then(res=>{
                     this.setState({facilityList:res.data,hospitalName:res1.data.name});
                     console.log(res.data);
                    })
                 .catch(err=>console.log(err));
             }

        })
        .catch(err=>console.log(err));
        
    }
    
    handleSubmit=(event)=> {
       
        let res=this.handleValidation();
        if(res)
        {
            if(this.state.facilityId === "")
            {
                const body={
                    "description":this.state.desc,
                    "remarks":this.state.remarks,
                    "charges":this.state.charges
                }
                const url=`http://localhost:8080/addfacility/${this.state.hospitalId}/${this.state.facility}`;
                console.log(url);
                console.log(body);
                axios.post(url,body)
                .then(res=>console.log(res.data))
                .catch(err=>console.log(err));
            }
            else
            {
                const body={
                    "hospitalFacilityId":this.props.match.params.id,
                    "description":this.state.desc,
                    "remarks":this.state.remarks,
                    "charges":this.state.charges
                }
                const url=`http://localhost:8080/updatefacility/${this.state.hospitalId}/${this.props.match.params.id}`
                console.log(url);
                console.log(body);
                axios.put(url,body)
                .then(res=>console.log(res.data))
                .catch(err=>console.log(err));

            }

        }
        event.preventDefault();
    }

    handleValidation=()=>{
        let valid=true;
        let temp = {
            hospitalId:"",
            hospitalName:"",
            facility:"",
            charges:"",
            desc:"",
            display_success:false,
            display_error:false
        };
        if(this.state.hospitalId=="")
        {
            valid=false;
            temp.hospitalId="Please provide a valid hospital ID";
        }
        if(this.state.hospitalName=="")
        {
            valid=false;
            temp.hospitalName="Please provide the hospital name";
        }
        if(this.state.facility=="")
        {
            valid=false;
            temp.facility="Please choose a facility to add";
        }
        if(this.state.charges=="")
        {
            valid=false;
            temp.charges="Please provide the charges of the facilty";
        }
        if(isNaN(this.state.charges))
        {
            valid=false;
            temp.charges="Amount should be a number in Rupees";
        }
        if(this.state.desc=="")
        {
            valid=false;
            temp.desc="Please provide a description for the facility";
        }
        temp.display_error = !valid;
        temp.display_success  = valid;
        console.log(temp);
        this.setState({errors:temp})
        if(valid)
            return true;
        else
            return false;

    }
    displayAlert=()=>{
      
        if(this.state.errors.display_error)
            return(
                <div className="alert alert-danger" role="alert">
                Please update the highlighted mandatory field(s).
                </div>
            )
        else
            return null;
    }
    displaySuccess=()=>{
      
        if(this.state.errors.display_success)
            return(
                <div className="alert alert-success" role="alert">
                    Form has been successfully submitted!
                </div>
            )
        else
            return null;
    }

    reset=()=>{
        this.setState({
            facility:"",
            charges:"",
            desc:"",
            remarks:"",
            errors:{
                hospitalId:"",
                hospitalName:"",
                facility:"",
                charges:"",
                desc:"",
                display_success:false,
                display_error:false
            }

        });
    }

    render() { 
        if(this.state.facilityId==="")
        {
        return ( 
            <React.Fragment>
                <div className="container">
                    <br/>
                    
                    <form  className="card" onSubmit={this.handleSubmit}>
                        <div className="card-header">
                            <h3>Add a new facility </h3>
                        </div>
                        <div className="card-body">
                           {this.displayAlert()}
                           {this.displaySuccess()}
                        <div className="row">
                            <div className="form-group col-md-6 col-sm-12">
                                <label >Hospital ID</label>
                                <input type="text" className={this.state.errors.hospitalId===""?"form-control":"form-control is-invalid"} id="hospitalId" name="hospitalId" value={this.state.hospitalId} onChange={()=>{}} disabled />
                            </div>
                            <div className="form-group col-md-6 col-sm-12">
                                <label >Hospital Name</label>
                                <input type="text" className={this.state.errors.hospitalName===""?"form-control":"form-control is-invalid"} id="hospitalName" name="hospitalName" value={this.state.hospitalName}  onChange={()=>{}} disabled/>
                               
                            </div>
                        </div>
                        <div className="row">
                            <div className="form-group col-md-6 col-sm-12">
                                <label >Select Facility</label>
                                <select className={this.state.errors.facility===""?"custom-select":"custom-select is-invalid"} name="facility" onChange={this.handleChange}>
                                    <option  value="choose">choose..</option>
                                    {this.state.facilityList.map((facility) =>{
                                        return(<option key={facility.facilityId} value={facility.facilityId}>
                                            {facility.name}
                                        </option>);
                                    })}
                                </select>
                               
                            </div>
                            <div className="form-group col-md-6 col-sm-12">
                                <label>charges of the Facilty</label>
                                <input type="text" className={this.state.errors.charges===""?"form-control":"form-control is-invalid"} id="charges" name="charges"  onChange={this.handleChange} />
                                <p className="text-danger text-small">{this.state.errors.charges==="Amount should be a number in Rupees"?this.state.errors.charges:null}</p>
                            </div>
                        </div>
                        <div className="row">
                            <div className="form-group col-md-6 col-sm-12">
                                <label>Description</label>
                                <input type="text" className={this.state.errors.desc===""?"form-control":"form-control is-invalid"} id="desc" name="desc"  onChange={this.handleChange} />
                                
                            </div>
                            <div className="form-group col-md-6 col-sm-12">
                                <label >Remarks</label>
                                <input type="text" className="form-control" id="remarks" name="remarks"  onChange={this.handleChange} />
                                <p></p>
                            </div>
                        </div>
                        <div className=" row">
                            <div className="form-group col-md-6 col-sm-12">
                                <input type="submit" className ="btn btn-primary" value="Add Facility"/><span>  </span>
                                <input type="reset" onClick={this.reset} className ="btn btn-primary" value="Reset"/>
                            </div>
                        </div>
                    </div>
                    </form>
                </div>
            </React.Fragment>
         );
        }
        else
        {
            return(

                <React.Fragment>
                <div className="container">
                    <br/>
                    
                    
                    <form className="card" onSubmit={this.handleSubmit}>
                        <div className="card-header">
                            <h3>Update facility</h3>
                            <h4 className="text-muted">{this.state.facility}</h4>
                        </div>
                        <div className="card-body">
                        {this.displayAlert()}
                        {this.displaySuccess()}
                        <div className="row">
                            <div className="form-group col-md-6 col-sm-12">
                                <label >Hospital ID</label>
                                <input type="text" className={this.state.errors.hospitalId===""?"form-control":"form-control is-invalid"} id="hospitalId" name="hospitalId" value={this.state.hospitalId} onChange={this.handleChange} />
                               
                            </div>
                            <div className="form-group col-md-6 col-sm-12">
                                <label >Hospital Name</label>
                                <input type="text" className={this.state.errors.hospitalName===""?"form-control":"form-control is-invalid"} id="hospitalName" name="hospitalName" value={this.state.hospitalName}  onChange={this.handleChange}/>
                               
                            </div>
                        </div>
                        <div className="row">
                            <div className="form-group col-md-6 col-sm-12">
                                <label>charges of the Facilty</label>
                                <input type="text" className={this.state.errors.charges===""?"form-control":"form-control is-invalid"} id="charges" name="charges" value={this.state.charges} onChange={this.handleChange} />
                                <p className="text-danger text-small">{this.state.errors.charges==="Amount should be a number in Rupees"?this.state.errors.charges:null}</p>
                            </div>
                            <div className="form-group col-md-6 col-sm-12">
                                <label>Description</label>
                                <input type="text" className={this.state.errors.desc===""?"form-control":"form-control is-invalid"} id="desc" name="desc" value={this.state.desc} onChange={this.handleChange} />
                            </div>
                        </div>
                        <div className="row">
                            <div className="form-group col-md-12 col-sm-12">
                                <label >Remarks</label>
                                <input type="text" className="form-control" id="remarks" name="remarks" value={this.state.remarks} onChange={this.handleChange} />
                            </div>
                        </div>
                        <div className=" row">
                            <div className="form-group col-md-6 col-sm-12">
                                <input type="submit" className ="btn btn-primary" value="Update Facility"/>
                            </div>
                        </div>
                    </div>
                    </form>
                </div>
            </React.Fragment>

            );
        }
    }
}
 
export default FacilityAddUpdateComponent;
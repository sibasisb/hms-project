import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.css'

class FacilityAddUpdateComponent extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            hospitalId:"HOS0001",
            hospitalName:"City Hospital",
            facility:"",
            charge:"",
            desc:"",
            remarks:"",
           
            facilityId:Object.keys(this.props.match.params).length === 0?"":this.props.match.params.id,
            errors:{
                hospitalId:"",
                hospitalName:"",
                facility:"",
                charge:"",
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

        if(this.state.facilityId !== ""){
        this.setState({
            hospitalId:"HOS0001",
            hospitalName:"City Hospital",
            facility:"Radiology/X-ray facility",
            charge:"6000",
            desc:"Nicee",
            remarks:"hgh",
        });
     }
    }
    
    handleSubmit=(event)=> {
       
        let res=this.handleValidation();
        if(res)
            console.log("Your details are submitted successfully");
        event.preventDefault();
    }

    handleValidation=()=>{
        let valid=true;
        let temp = {
            hospitalId:"",
            hospitalName:"",
            facility:"",
            charge:"",
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
        if(this.state.charge=="")
        {
            valid=false;
            temp.charge="Please provide the charge of the facilty";
        }
        if(isNaN(this.state.charge))
        {
            valid=false;
            temp.charge="Amount should be a number in Rupees";
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
                <div class="alert alert-success" role="alert">
                    Form has been successfully submitted!
                </div>
            )
        else
            return null;
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
                                <input type="text" className={this.state.errors.hospitalId===""?"form-control":"form-control is-invalid"} id="hospitalId" name="hospitalId" value={this.state.hospitalId} onChange={this.handleChange} />
                            </div>
                            <div className="form-group col-md-6 col-sm-12">
                                <label >Hospital Name</label>
                                <input type="text" className={this.state.errors.hospitalName===""?"form-control":"form-control is-invalid"} id="hospitalName" name="hospitalName" value={this.state.hospitalName}  onChange={this.handleChange}/>
                               
                            </div>
                        </div>
                        <div className="row">
                            <div className="form-group col-md-6 col-sm-12">
                                <label >Select Facility</label>
                                <select className={this.state.errors.facility===""?"custom-select":"custom-select is-invalid"} name="facility" onChange={this.handleChange}>
                                    <option  value="choose">choose..</option>
                                    <option value="OPD">OPD</option>
                                    <option value="Dental facility">Dental facility</option>
                                    <option value="Ward/ Indoor facility">Ward/ Indoor facility</option>
                                    <option value="Minor OT/ Dressing Room">Minor OT/ Dressing Room</option>
                                    <option value="Physiotherapy">Physiotherapy</option>
                                    <option value="Laboratory services">Laboratory services</option>
                                    <option value="ECG Services">ECG Services</option>
                                    <option value="Pharmacy">Pharmacy</option>
                                    <option value="Radiology/X-ray facility">Radiology/X-ray facility</option>
                                    <option value="Ambulance Services">Ambulance Services</option>
                                </select>
                               
                            </div>
                            <div className="form-group col-md-6 col-sm-12">
                                <label>Charge of the Facilty</label>
                                <input type="text" className={this.state.errors.charge===""?"form-control":"form-control is-invalid"} id="charge" name="charge"  onChange={this.handleChange} />
                                <p className="text-danger text-small">{this.state.errors.charge==="Amount should be a number in Rupees"?this.state.errors.charge:null}</p>
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
                                <input type="submit" className ="btn btn-primary" value="Add Facility"/>
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
                        <div class="card-header">
                            <h3>Update facility</h3>
                            <h4 className="text-muted">{this.state.facility}</h4>
                        </div>
                        <div class="card-body">
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
                                <label>Charge of the Facilty</label>
                                <input type="text" className={this.state.errors.charge===""?"form-control":"form-control is-invalid"} id="charge" name="charge" value={this.state.charge} onChange={this.handleChange} />
                                <p className="text-danger text-small">{this.state.errors.charge==="Amount should be a number in Rupees"?this.state.errors.charge:null}</p>
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
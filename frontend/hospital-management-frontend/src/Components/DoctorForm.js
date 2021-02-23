import React, {useEffect, useState} from 'react'
import MultiSelect from "react-multi-select-component";
//import { Multiselect } from 'multiselect-react-dropdown';
import '../styles/mystyle.css'

function DoctorForm(props) {

    const week=[{value:"Mon",label:"Mon"},{value:"Tue",label:"Tue"},{value:"Wed",label:"Wed"},{value:"Thu",label:"Thu"},{value:"Fri",label:"Fri"},{value:"Sat",label:"Sat"},{value:"Sun",label:"Sun"}]
    const error={
        borderColor:"#ff0000"
    }

    const noerror=
    {borderColor:"#ced4da"}

    const initialState = {
        speciality: "",
        experience: "",
        quality: "",
        hospitalName: "",
        dates:[],
        startTime:"",
        endTime:""
    }
    const [state,
        setstate] = useState(initialState)

    useEffect(() => {

        props.changeDoctorDetails(state)
    }, [state])

    function handleSelect(item)
    {
        let temp=[]
        if(item.length>0)
        {
        console.log(item)
        console.log(item[0].value)
      temp=state.dates
      console.log(temp)
       temp.push(item.pop())
       console.log(temp)
        }
        setstate(
            {
                ...state,
                dates:temp
            }
        )
        
        
    }
    function changeValue(event) {
        console.log(event)
        setstate({
            ...state,
            [event.target.name]: event.target.value
        })
    }
    return (
        <div>

            <div class=" row mb-3">
                <div class="col-md-6">
                    <label>Quality
                    </label>
                    <input
                        type="text"
                        class="form-control"
                        name="quality"
                        placeholder="Quality"
                        style={props.errors?.quality?error:noerror}
                        onChange={changeValue}/></div>
                <div class="col-md-6">
                    <label>Speciality
                    </label>
                    <input
                        type="text"
                        class="form-control"
                        name="speciality"
                        placeholder="Speciality"
                        style={props.errors?.speciality?error:noerror}
                        onChange={changeValue}/></div>
            </div>
            <div class=" row mb-3">
                <div class="col-md-6">
                    <label>Experience(in years)
                    </label>
                    <input
                        type="number"
                        class="form-control"
                        name="experience"
                        placeholder="Experience"
                        style={props.errors?.experience?error:noerror}
                        onChange={changeValue}/></div>
                <div class="col-md-6">
                    <label>Select Hospital Name</label>
                    <select
                        id="inputState"
                        class="form-control"
                        name="hospitalName"
                        style={props.errors?.hospitalName?error:noerror}
                        onChange={changeValue}>
                        <option value="" defaultValue>Choose</option>
                        <option value="any">Hospital 1</option>
                    </select>

                </div>
            </div>
            <div class=" row mb-3">
                <div class="col-12">
                    <label>Available Days</label>
                    <MultiSelect
                    name="dates"
                    className={props.errors?.dates?"select-empty":"select-notEmpty"}
                    options={week}
                    onChange={handleSelect}
                    value={state.dates}
                    />
                </div>
                </div>
                <div class="row mb-3">
                    <div className="col-md-6">
                    <div><label>Available Time</label></div>
                    <span><input type="time" class="form-control" name="startTime"  style={props.errors?.startTime?error:noerror}
                        onChange={changeValue} placeholder="Experience"/></span>
                     to 
                    <span><input type="time" class="form-control" name="endTime"  style={props.errors?.endTime?error:noerror}
                        onChange={changeValue} placeholder="Experience"/></span>
                    </div>
                </div>
                

            </div>
        
    )
}

export default DoctorForm

import React, { useEffect, useState } from 'react'
import { faEye } from '@fortawesome/free-solid-svg-icons';
const { FontAwesomeIcon } = require("@fortawesome/react-fontawesome");

function PatientForm(props) {


    const error = {
        borderColor: "red"
    }

    const noerror = {
        borderColor: "#ced4da"
    }
    //console.log(props.errors);
    //console.log(props.state)
    const initialState = {
        firstName: "",
        lastName: "",
        dateOfBirth: "",
        contact: "",
        gender: "male",
        email: "",
        password: ""
    }
    // const initialState=props.state
    const [state,
        setstate] = useState(initialState)

    const [show ,setShow]=useState(false);

    useEffect(() => {
        props.changeUserDetails(state)
    }, [state])

    function changeValue(event) {
        setstate({
            ...state,
            [event.target.name]: event.target.value
        })
    }

    function toggleShow(event) {
        event.preventDefault();
        setShow(!show);
      }

    return (

        <div>
            <div className="row">
                <div className="form-group col-md-6">
                    <label htmlFor="inputFName">First Name</label>
                    <input type="text" className="form-control" name="firstName" placeholder="First name" style={props.errors?.firstName ? error : noerror} onChange={changeValue} />
                    <small className="text-danger">{props.errors?.firstName}</small>
                </div>
                <div className="form-group col-md-6">
                    <label htmlFor="inputLName">Last Name</label>
                    <input type="text" className="form-control" name="lastName" placeholder="Last name" style={props.errors?.lastName ? error : noerror} onChange={changeValue} />
                    <small className="text-danger">{props.errors?.lastName}</small>
                </div>
            </div>
            <div className="row">
                <div className="form-group col-md-6">
                    <label htmlFor="inputDOB">Date of Birth</label>
                    <input type="date" className="form-control" name="dateOfBirth" placeholder="Date of Birth" style={props.errors?.dateOfBirth ? error : noerror} onChange={changeValue} />
                    <small className="text-danger">{props.errors?.dateOfBirth}</small>
                </div>
                <div className="form-group col-md-6">
                    <label htmlFor="inputContact">Contact Number</label>
                    <input
                        type="text"
                        className="form-control"
                        name="contact"
                        placeholder="Contact Number"
                        style={props.errors?.contact ? error : noerror}
                        onChange={changeValue} />
                    <small className="text-danger">{props.errors?.contact}</small>
                </div>
            </div>
            <div className="row">
                <div className="form-group col-md-6 ml-3" onClick={changeValue} >
                    <div>Gender :</div>
                    <div className="form-check-inline">
                        <label className="form-check-label" htmlFor="male">
                            <input
                                type="radio"
                                className="form-check-input"
                                id="male"
                                name="gender"
                                value="male"
                                {...(state.gender === "male" && { defaultChecked: "checked" })}
                            />Male
                        </label>
                    </div>
                    <div className="form-check-inline">
                        <label className="form-check-label" htmlFor="female">
                            <input
                                type="radio"
                                className="form-check-input"
                                id="female"
                                name="gender"
                                value="female"
                                {...(state.gender === "female" && { defaultChecked: "checked" })}
                            />Female
                        </label>
                    </div>
                </div>
            </div>
            <div className="form-group">
                <label htmlFor="inputEmail">Email</label>
                <input
                    type="email"
                    className="form-control"
                    name="email"
                    id="inputEmail"
                    placeholder="Email"
                    style={props.errors?.email ? error : noerror}
                    onChange={changeValue} />
            </div>
            <div className="form-group">
                <label htmlFor="inputPassword">Password</label>
                <div className="input-group">
                    <input
                        type={show ?"text":"password"}
                        className="form-control"
                        name="password"
                        id="inputPassword"
                        placeholder="Password"
                        style={props.errors?.password ? error : noerror}
                        onChange={changeValue} />
                    <div className="input-group-append">
                        <button className="btn" style={{borderColor:"#ced4da"}} onClick={toggleShow}>
                            <FontAwesomeIcon className="float-right" icon={faEye} />
                        </button>
                    </div>
                </div>
                <div><small className="text-danger">{props.errors?.password}</small></div>
            </div>

        </div>

    )
}

export default PatientForm;
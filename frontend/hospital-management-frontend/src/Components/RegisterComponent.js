import React, { useState, useEffect } from 'react'
import { initialState } from '../Reducers/userReducer';
import DoctorForm from './DoctorForm';
import HospitalAdminForm from './HospitalAdminForm';
import PatientForm from './PatientForm';
import { Link } from 'react-router-dom'
import axios from 'axios';
import { faCaretDown, faCaretUp, faList } from "@fortawesome/free-solid-svg-icons";

const { FontAwesomeIcon } = require("@fortawesome/react-fontawesome");

function RegisterComponent(props) {

    // let erroralert="";
    console.log(props.history)

    const initialState = {
        arrowDirectionUp: false,
        userDetails: {},
        hospitalDetails: {},
        doctorDetails: {},
        errors: {},
        successAlert: null
    }
    const [state,
        setState] = useState(initialState)


    function changeValue(event) {
        setState({
            ...state,
            [event.target.name]: event.target.value
        })
    }

    function changeAdminDetails(details) {
        console.log("called");
        setState({
            ...state,
            hospitalDetails: details
        })
    }

    function changeUserDetails(details) {
        console.log("called");
        setState({
            ...state,
            userDetails: details
        })
    }

    function changeDoctorDetails(details) {
        console.log("called");
        setState({
            ...state,
            doctorDetails: details
        })
    }


    function displayComponent() {
        //  if(arrowIcon.getclassNameList)
        setState({
            ...state,
            arrowDirectionUp: !state.arrowDirectionUp
        })
    }

    function validateForm() {
        let error = {};
        console.log("new call");
        for (let field in state.userDetails) {
            if (state.userDetails[field] === "")
                error[field] = true

            if (field === "firstName" && (state.userDetails[field].length < 4 || state.userDetails[field].length > 50))
                error[field] = "First Name should be between 4 and 50 characters"

            if (field === "lastName" && (state.userDetails[field].length < 4 || state.userDetails[field].length > 50))
                error[field] = "Last Name should be between 4 and 50 characters"

            if (field === "dateOfBirth") {
                let getAge = Math.floor((new Date() - new Date(state.userDetails[field]).getTime()) / 3.15576e+10)
                console.log(getAge)
                if (getAge < 18)
                    error[field] = "Age should be greater than 18"
            }
            if (field === "contact" && !(/^([0-9]{10})$/.test(state.userDetails[field])))
                error[field] = "Contact should have 10 digits"

            if (field === "password" && !(/^(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,}$/.test(state.userDetails[field])))
                error[field] = "Password should contain minimum 6 characters and also have atleast 1 special character."


        }

        if (Object.keys(error).length === 0 && Object.keys(state.hospitalDetails).length === 0 && props.location.state === "hospital admin") {
            error.erroralert = <div className="alert alert-danger" role="alert">
                Please fill up Hospital Details.
          </div>

        }

        for (let field in state.hospitalDetails) {
            if (state.hospitalDetails[field] === "" && field !== "addr2")
                error[field] = true;

            // if (field === "phone" && !(/^([0-9]{10})$/.test(state.userDetails[field])))
            //     error[field] = "Phone Number should have 10 digits"
        }


        if (Object.keys(error).length === 0 && Object.keys(state.doctorDetails).length === 0 && props.location.state === "doctor") {
            error.erroralert = <div className="alert alert-danger" role="alert">
                Please fill up Doctor details
          </div>;

        }


        for (let field in state.doctorDetails) {
            if (state.doctorDetails[field] === "" || state.doctorDetails[field].length === 0)
                error[field] = true
        }
        console.log(error)

        if (Object.keys(error).length === 0) {
            return true;
        }

        if (!error?.erroralert)
            error.erroralert = (<div className="alert alert-danger" role="alert">
                Please update the highlighted mandatory fields(s).</div>)

        setState(
            {
                ...state,
                errors: error
            }
        )
    }
    function handleSubmit(event) {
        event.preventDefault();
        if (validateForm()) {

            console.log("validated")
            let userDetails = {
                ...state.userDetails,
                role: props.location.state
            }
            console.log(userDetails)
            let hospitalDetails = {}
            let doctorDetails = {}
            if (props.location.state === "hospital admin") {
                let { addr1, addr2, city, state: state1, zip, ...restH } = state.hospitalDetails;

                hospitalDetails = {
                    ...restH,
                    address: [addr1, addr2, city, state1, zip].join(",")
                }
            }
            if (props.location.state === "doctor") {
                let { hospitalID, dates, startTime, endTime, ...restD } = state.doctorDetails;
                console.log(hospitalID)
                let availableDate = ``
                for (let date of dates) {
                    availableDate += `${date.value},`
                }

                let doctorDetails = {
                    ...restD,
                    availableDays: availableDate,
                    availableTime: `${startTime}-${endTime}`
                }
                hospitalDetails = {
                    hospitalId: hospitalID
                }
            }
            let userInfo = {
                user: userDetails,
                doctor: doctorDetails,
                hospital: hospitalDetails
            }
            console.log(userInfo)
            axios.post("http://localhost:8080/users/register", userInfo)
                .then(res => {
                    console.log(res.data);
                    console.log(userInfo)
                    console.log("Details saved successfully");
                    setState(
                        {
                            ...state,
                            errors: {},
                            successAlert: <div className="alert alert-success text-bold"><strong>{res.data}</strong></div>,
                            arrowDirectionUp: false
                        }
                    )
                }).catch(err => {
                    console.log(err);
                })

        }
        //  return false
    }
    return (
        <div>
            <div className="col-md-6 offset-md-3 mt-5">

                <div className="card card-outline-secondary">
                    <div className="card-header">
                        <h3 className="mb-0">Register as {props.location.state.toUpperCase()}</h3>
                    </div>
                    <div className="card-body">
                        {state.successAlert ? state.successAlert : state.errors?.erroralert}

                        <form className="form" role="form" noValidate onSubmit={handleSubmit}>
                            <PatientForm errors={state.errors} changeUserDetails={changeUserDetails} /> {props.location.state === "doctor"
                                ? <div className="bg-light mb-4" onClick={displayComponent}>Fill up Doctor specific details {state.arrowDirectionUp
                                    ?
                                    <FontAwesomeIcon icon={faCaretUp} />
                                    : <FontAwesomeIcon icon={faCaretDown} />}
                                </div>
                                : (props.location.state === "hospital admin"
                                    ? <div className="bg-light mb-4" onClick={displayComponent}>Fill up Hospital specific details {state.arrowDirectionUp
                                        ? <FontAwesomeIcon icon={faCaretUp} />
                                        : <FontAwesomeIcon icon={faCaretDown} />}
                                    </div>
                                    : "")
                            }

                            {state.arrowDirectionUp
                                ? (props.location.state === "doctor"
                                    ? <DoctorForm state={state.doctorDetails} errors={state.errors} changeDoctorDetails={changeDoctorDetails} />
                                    : (props.location.state === "hospital admin"
                                        ? <HospitalAdminForm state={state.hospitalDetails} errors={state.errors} changeAdminDetails={changeAdminDetails} />
                                        : null))
                                : null
                            }


                            <div className="d-flex flex-row justify-content-between" >
                                <div className="form-group">
                                    <input type="submit" className="btn btn-primary btn-lg mt-4" value="Register" />
                                </div>
                                <div className="mt-4"><b><Link to="/login">Go to Login Page</Link></b></div>
                            </div>
                        </form>

                    </div>
                </div>

            </div>
        </div>
    )
}

export default RegisterComponent

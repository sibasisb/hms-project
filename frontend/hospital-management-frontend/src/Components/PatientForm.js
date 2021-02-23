import React, { useEffect, useState } from 'react'

function PatientForm(props) {


    const error = {
        borderColor: "red"
    }

    const noerror = {
        borderColor: "#ced4da"
    }
    console.log(props.errors);
    console.log(props.state)
    const initialState = {
        fName: "",
        lName: "",
        dob: "",
        contact: "",
        gender: "male",
        email: "",
        password: ""
    }
    // const initialState=props.state
    const [state,
        setstate] = useState(initialState)

    useEffect(() => {
        console.log(state)
        props.changeUserDetails(state)
    }, [state])

    function changeValue(event) {
        console.log(event.target.value)
        setstate({
            ...state,
            [event.target.name]: event.target.value
        })
    }


    return (

        <div>
            <div className="row">
                <div className="form-group col-md-6">
                    <label htmlFor="inputFName">First Name</label>
                    <input type="text" className="form-control" name="fName" placeholder="First name" style={props.errors?.fName ? error : noerror} onChange={changeValue} />
                    <small className="text-danger">{props.errors?.fName}</small>
                </div>
                <div className="form-group col-md-6">
                    <label htmlFor="inputLName">Last Name</label>
                    <input type="text" className="form-control" name="lName" placeholder="Last name" style={props.errors?.lName ? error : noerror} onChange={changeValue} />
                    <small className="text-danger">{props.errors?.lName}</small>
                </div>
            </div>
            <div className="row">
                <div className="form-group col-md-6">
                    <label htmlFor="inputDOB">Date of Birth</label>
                    <input type="date" className="form-control" name="dob" placeholder="Date of Birth" style={props.errors?.dob ? error : noerror} onChange={changeValue} />
                    <small className="text-danger">{props.errors?.dob}</small>
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
                <input
                    type="password"
                    className="form-control"
                    name="password"
                    id="inputPassword"
                    placeholder="Password"
                    style={props.errors?.password ? error : noerror}
                    onChange={changeValue} />
                <small className="text-danger">{props.errors?.password}</small>
            </div>

        </div>

    )
}

export default PatientForm;
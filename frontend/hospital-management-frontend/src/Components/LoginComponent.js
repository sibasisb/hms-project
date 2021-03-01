import React, { Component, useEffect, useState } from 'react'
import userReducer from '../Reducers/userReducer'
import { Redirect } from 'react-router-dom';
import { UserContext } from '../App'
import axios from 'axios';



export const LoginComponent = (props) => {


    const { dispatch } = React.useContext(UserContext);
    const error = {
        borderColor: "red"
    }

    const noerror = {
        borderColor: "#ced4da"
    }

    const initialState = {
        userId: "",
        password: "",
        errors: {
            userId: "",
            password: ""
        },
        errorsAlert: "",
        invalidLogin: ""
    }

    const [state, setState] = useState(initialState)

    let passError, emailError;
    const validateForm = () => {
        let emailError = ""
        let passwordError = ""
        console.log(state.userId)
        if (state.userId === "")
            emailError = "userId Is Required"
        if (state.password === "")
            passwordError = "Password Is Required"
        if (emailError === "" && passwordError === "") {
            return true
        }
        else {
            let err = {
                userId: emailError,
                pass: passwordError
            }

            console.log(err)
            setState({
                ...state,
                errorsAlert: <div class="alert alert-danger mt-3" role="alert">
                    Please update the highlighted mandatory fields(s).</div>,
                invalidLogin: "",
                errors: err
            })

        }
    }


    const handleChange = (event) => {
        setState({
            ...state,
            [event.target.name]: event.target.value
        })
    }

    function handleSubmitUser(event) {
        event.preventDefault();
        let val = validateForm();
        console.log(val)
        //  console.log(state.errors)
        if (val) {
            let user = {
                userId: state.userId,
                password: state.password
            }
            console.log(user)
            axios.post("http://localhost:8080/users/login/user", user)
                .then((res) => {
                    console.log(res)
                    console.log(res.data);
                    let userData={
                        userId: res.data.userId,
                        token: res.data.token,
                        role: res.data.role,
                    }
                    if(res.data.role==="hospital admin")
                    {
                        
                        axios.get(`http://localhost:8080//hospitaladmin/${res.data.userId}`)
                        .then(res=>{
                            console.log(res)
                            dispatch(
                                {
                                    type: "LOGIN",
                                    payload: {
                                        ...userData,
                                        hospitalId:res.data
                                    }
                                }
                            )
                        }).catch(err=>console.log(err))
                    }
                    else{
                    dispatch(
                        {
                            type: "LOGIN",
                            payload: {
                                ...userData
                            }
                        }
                    )
                    }

                        switch (res.data.role) {
                            case "patient":
                                props.history.push("/patientdashboard")
                                break;
                                case "doctor":
                                props.history.push("/doctordashboard")
                                break;
                                case "hospital admin":{
                                props.history.push("/hospitaladmindashboard")
                                }
                                break;
                        
                            default:
                                break;
                        }


                })
                .catch(err => {
                    console.log(err)
                    setState({
                        ...state,
                        errors: {
                            userId: "",
                            pass: ""
                        },
                        errorsAlert: "",
                        invalidLogin: <div class="alert alert-danger mt-3" role="alert">Invalid UserID/Password.</div>
                    })
                })

        }
    }


    function handleSubmitAdmin(event) {
        event.preventDefault();
        let val = validateForm();
        console.log(val)
        if (val) {
            let user = {
                userId: state.userId,
                password: state.password
            }
            console.log(user)
            axios.post("http://localhost:8080/users/login/admin", user)
                .then((res) => {
                    console.log(res)
                    console.log(res.data);
                    dispatch(
                        {
                            type: "LOGIN",
                            payload: {
                                token: res.data,
                                role:"admin"
                            }
                        }
                    )
                    props.history.push("/admindashboard")

                })
                .catch(err => {
                    console.log(err)
                    setState({
                        ...state,
                        errors: {
                            userId: "",
                            pass: ""
                        },
                        errorsAlert: "",
                        invalidLogin: <div class="alert alert-danger mt-3" role="alert">Invalid UserID/Password.</div>
                    })
                })

        }
    }



    return (
        <div>

            <div className="container  mt-5 mb-3"  >

                <div className="card mx-auto h-90 login" >
                    <h2 className="card-header">Login</h2>
                    <div className="container">

                        {state.errorsAlert !== "" ? state.errorsAlert : ""}
                        {state.invalidLogin !== "" ? state.invalidLogin : ""}
                        <form  >
                            <div className="form-row mb-2 mt-2">
                                <div className="form-group col-12">
                                    <label htmlFor="inputEmail Address">UserID<span style={{ color: "#ff0000" }}>*</span></label>
                                    <input type="text" className="form-control" name="userId" value={state.userId} onChange={handleChange} style={state.errors.userId ? error : noerror} placeholder="UserID" />


                                </div>
                            </div>
                            <div className="form-row mb-2 ">
                                <div className="form-group col-12 ">
                                    <label htmlFor="inputPassword">Password<span style={{ color: "#ff0000" }}>*</span></label>
                                    <input type="password" className="form-control" name="password" value={state.password} onChange={handleChange} style={state.errors.pass ? error : noerror} placeholder="Password" />

                                    <div id="id2" className="text-danger"></div>
                                </div>
                            </div>


                            <div className="d-flex flex-row " >
                                <button type="submit" className="btn btn-primary btn-large mb-4" onClick={handleSubmitUser}> Login as User</button>
                                <button type="submit" className="btn btn-primary btn-large ml-4 mb-4" onClick={handleSubmitAdmin}> Login as System Admin </button>

                            </div>



                        </form>
                    </div>
                </div>
            </div>
        </div>


    )
}


export default LoginComponent
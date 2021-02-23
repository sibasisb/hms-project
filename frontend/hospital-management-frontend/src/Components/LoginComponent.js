import React, { Component, useEffect, useState } from 'react'


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
        email: "",
        password: "",
        errors:{
            email:"",
            password:""
        },
        errorsAlert: "",
        invalidLogin: ""
    }

    const [state, setState] = useState(initialState)

    let passError, emailError;
    const validateForm = () => {
        let emailError=""
        let passwordError=""
        console.log(state.email)
        if(state.email==="")
          emailError="Email Is Required"
        if(state.password==="")
          passwordError="Password Is Required"
        if(emailError==="" && passwordError==="")
          {    return true
        }
        else {
            let err={
                email:emailError,
                pass:passwordError
            }
            
            console.log(err)
            setState({
                ...state,
                errorsAlert:<div class="alert alert-danger mt-3" role="alert">
                Please update the highlighted mandatory fields(s).</div>,
                invalidLogin: "",
                errors:err
            })

        }
    }


    const handleChange = (event) => {
        setState({
            ...state,
            [event.target.name]: event.target.value
        })
    }

    function handleSubmit(event) {
        event.preventDefault();
        let val = validateForm();
        console.log(val)
        //  console.log(state.errors)
        if (val) {
            let user = {
                email: state.email,
                password: state.password
            }
            console.log(user)
            //  axios.post("http://localhost:8080/users",user)
            // .then((res)=>{
            //     console.log(res.data);
            //     dispatch(
            //         {
            //             type:"LOGIN",
            //             payload:{
            //                 user:res.data.id,
            //                 token:"token"
            //             }
            //         }
            //     )
            //     props.history.push("/watchlist")
            // })
            // .catch(err=>{ 
            // setState({
            //     ...state,
            //     errors:{
            //             email:"",
            //             pass:""
            //         },
            //     invalidLogin:"Invalid Email/Password"
            // })})

            //    }
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
                        {console.log(state.errors)}
                        <form onSubmit={handleSubmit}  >
                            <div className="form-row mb-2 mt-2">
                                <div className="form-group col-12">
                                    <label htmlFor="inputEmail Address">Email Address<span style={{ color: "#ff0000" }}>*</span></label>
                                    <input type="text" className="form-control" name="email" value={state.email} onChange={handleChange} style={state.errors.email?error:noerror}placeholder="Email Address" />


                                </div>
                            </div>
                            <div className="form-row mb-2 ">
                                <div className="form-group col-12 ">
                                    <label htmlFor="inputPassword">Password<span style={{ color: "#ff0000" }}>*</span></label>
                                    <input type="password" className="form-control" name="password" value={state.password} onChange={handleChange} style={state.errors.pass?error:noerror} placeholder="Password" />

                                    <div id="id2" className="text-danger"></div>
                                </div>
                            </div>


                            <div className="d-flex flex-row " >
                                <button type="submit" className="btn btn-primary btn-large mb-4"> Login as User</button>
                                <button type="submit" className="btn btn-primary btn-large ml-4 mb-4"> Login as System Admin </button>

                            </div>



                        </form>
                    </div>
                </div>
            </div>
        </div>


    )
}


export default LoginComponent
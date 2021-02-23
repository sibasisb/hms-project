import React, { useState,useEffect } from 'react'
import { Link } from 'react-router-dom'
import doctorImg from '../images/doctor.png'
import patientImg from '../images/new-patient.png'
import adminImg from '../images/admin.png'
import RegisterComponent from './RegisterComponent'



function HomeComponent(props) {
    const style={
        width:'200px',
        height:'200px'
    }
   const initialState={
        role:""
    }

    const [state, setstate] = useState(initialState)
   
    const anonymousUser=
    <div className="container ">
        <div className="row text-center "  style={{height:'90vh',display:'flex',alignItems:'center',justifyContent:'center'}}>
            <div className="col-md-4">
            <Link
             
             to={{
                pathname:"/register",
                state:"hospital admin"
            }}
            >
                <img className="rounded-circle shadow mb-4" src={adminImg}  style={style} />
                </Link>
                <p>Admin</p>
            </div>
            <div className="col-md-4">
            <Link
            
            to={{
                pathname:"/register",
                state:"doctor"
            }}
            >
                <img className="rounded-circle shadow mb-4" src={doctorImg} style={style} />
                </Link>
                <p>Doctor</p>
                
            </div>
            <div className="col-md-4">
                <Link
                 
            to={{
                pathname:"/register",
                state:"patient"
            }}
                >
                <img className="rounded-circle shadow  mb-4" src={patientImg}   style={style} />
                </Link>
                <p>Patient</p>
            </div>
            <div>Already have an account?<b><Link to="/login">Login here</Link></b></div>
        </div>
        
    </div>

    return (
        <div>
            {
               anonymousUser
                
            }
        </div>
    )
}

export default HomeComponent

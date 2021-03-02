import React, { useContext } from "react";
import { Link } from "react-router-dom";
import { NavLink } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.css";
import { UserContext } from "../App";
import hmslogonew from '../images/hmslogonew.jpg'
import { faClinicMedical } from "@fortawesome/free-solid-svg-icons";
import axios from "axios";
import { getHeader } from "../helpers/AuthorizationHeader";

const { FontAwesomeIcon } = require("@fortawesome/react-fontawesome");

const MenuComponent = () => {

    const {dispatch}=useContext(UserContext);

    function logout()
    {
        axios.get("http://localhost:8080/users/logout",getHeader())
        .then(res=>{
            console.log(res);
            dispatch({
                type: "LOGOUT"
              })

        }).catch(err=>console.log(err))
    }

    let menuItems="";
    let role="";

  
    if(localStorage.getItem("token"))
    {
            role=localStorage.getItem("role")
            if(role==="hospital admin")
            role=role.split(" ").join("")
            menuItems=(<><li className="nav-item" style={{listStyleType:"none"}}>
            <NavLink className="nav-link text-white " to={`/${role}dashboard`} >Dashboard</NavLink>
        </li>
        <li className="nav-item" style={{listStyleType:"none"}}>
        <NavLink className="nav-link text-white " to="/login" onClick={logout}>Logout</NavLink>
    </li></>)
    }
    return (
        <div>
            <nav className="navbar navbar-expand-sm navbar-dark bg-dark pt-3">
                <Link to="/" className="navbar-brand">
                <FontAwesomeIcon icon={faClinicMedical} className="mr-2 mb-1" size="2x" />
                   <span style={{fontSize:"xx-large",fontWeight:"bold"}}> HCrux</span>
        </Link>
                <button
                    className="navbar-toggler"
                    type="button"
                    data-toggle="collapse"
                    data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                >
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav ml-auto" ></ul>
                    {menuItems}
                </div>
            </nav>
        </div>
    );
};

export default MenuComponent;

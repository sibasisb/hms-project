import React, { useContext } from "react";
import { Link } from "react-router-dom";
import { NavLink } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.css";
import { UserContext } from "../App";

const MenuComponent = () => {

    const {dispatch}=useContext(UserContext);

    function logout()
    {
        dispatch({
            type: "LOGOUT"
          })
    }

    let menuItems="";
    let role="";

  
    if(localStorage.getItem("token"))
    {
            role=localStorage.getItem("role")
            if(role==="hospital admin")
            role=role.split(" ").join("")
            menuItems=(<><li className="nav-item">
            <NavLink className="nav-link text-white text-muted" to={`/${role}dashboard`} >Dashboard</NavLink>
        </li>
        <li className="nav-item">
        <NavLink className="nav-link text-white text-muted" to="/login" onClick={logout}>Logout</NavLink>
    </li></>)
    }
    return (
        <div>
            <nav className="navbar navbar-expand-sm navbar-dark bg-dark">
                <Link to="/" className="navbar-brand">
                    HM System
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
                    <ul className="navbar-nav ml-auto"></ul>
                    {menuItems}
                </div>
            </nav>
        </div>
    );
};

export default MenuComponent;

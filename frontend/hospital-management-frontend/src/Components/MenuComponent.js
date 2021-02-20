import React, { useContext } from 'react';
import {Link} from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.css'

const MenuComponent=()=>{
    


    return (
        <div>
            <nav className="navbar navbar-expand-sm navbar-dark bg-dark">
                <Link to="Companies" className="navbar-brand">HM System</Link>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">  
                <ul className="navbar-nav mr-auto">  
                
                </ul>
                </div>
            </nav>
        </div>
    );
}

export default MenuComponent;
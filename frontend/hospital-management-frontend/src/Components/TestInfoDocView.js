import React, { useEffect, useState} from 'react';
import 'bootstrap/dist/css/bootstrap.css'
import {Link,useParams} from 'react-router-dom'
import axios from 'axios'
import { faEye } from "@fortawesome/free-solid-svg-icons";
import TestResults from './TestResults';

const { FontAwesomeIcon } = require("@fortawesome/react-fontawesome");


const TestInfoDocView=()=>{

    

    return (
        <div className="container">
            <h1 className="text-center mb-5 mt-5">Test result {}</h1>
            <table className="table table-responsive table-condensed mx-auto mt-3" style={{width:"60%"}} >
                <thead>
                    <tr>
                    <th scope="col">Result Id</th>
                    <th scope="col">Test Name</th>
                    <th scope="col">Test results</th>
                    <th scope="col">Patient history</th>
                    </tr>
                </thead>
            </table>
        </div>
    )
}

export default TestInfoDocView;
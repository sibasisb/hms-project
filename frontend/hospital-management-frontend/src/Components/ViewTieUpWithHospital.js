import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import { getHeader } from '../helpers/AuthorizationHeader'

function ViewTieUpWithHospital() {
    let [data, setdata] = useState([]);
    let [display,setdisplay]=useState(false)
    useEffect(() => {
        axios.get(`http://localhost:8080/tieups/${localStorage.getItem("hospitalId")}`,getHeader())
        .then(res=>{
            console.log(res)
            setdata(res.data)
            setdisplay(true)
        }).catch(err=>
            {
                console.log(err)
            })
        setdata(data)
    }, [])

    return (
        <div className="container mt-5">
              <div className="card">
				<h4 className="card-header"> List of Your Tie Ups </h4>
				<div className="card-body">
            {
          
           display?
           data.length!==0?
            <table className="table table-hover ">
                <thead>
                    <tr>
                        <th scope="col">id</th>
                        <th scope="col">Hospital Id</th>
                        <th scope="col">Hospital Name</th>
                    </tr>
                </thead>
                <tbody>
                    {data.map((tieUp,index) => {
                    return( <tr key={tieUp.hospital2Id}>
                            <th scope="row">{index+1}</th>
                            <td>{tieUp.hospital2Id}</td>
                            <td><Link style={{color:"black"}} to={`/viewhospital/${tieUp.hospital2Id}`} >{tieUp.hospital2Name}</Link></td>
                        </tr>
                    )
                    })}
                </tbody>
            </table>:<div className="alert alert-danger" role="alert">
                No Tie Ups Found
          </div>
            :""
}
</div>
</div>
        </div>
    )
}

export default ViewTieUpWithHospital

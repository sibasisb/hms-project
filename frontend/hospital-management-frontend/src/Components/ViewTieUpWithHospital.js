import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { getHeader } from '../helpers/AuthorizationHeader'

function ViewTieUpWithHospital() {
    let [data, setdata] = useState([]);

    useEffect(() => {
        // axios.get(`http://localhost:8080/tieups/HOS0591`,getHeader())
        // .then(res=>{
        //     setdata(res.data)
        // }).catch(err=>
        //     {
        //         console.log(err)
        //     })
        // setdata(data)
        data=[["HOS0995","FORTIS"],
        ["HOS0995","FORTIS"],
        ["HOS0995","FORTIS"],
        ["HOS0995","FORTIS"]]
        setdata(data)
    }, [])

    return (
        <div className="container mt-5">
            <h1 className="mb-5">List of Tied Up Hospitals</h1>
            <table className="table">
                <thead>
                    <tr>
                        <th scope="col">id</th>
                        <th scope="col">Hospital</th>
                    </tr>
                </thead>
                <tbody>
                    {data.map((tieUp,index) => {
                    // return( <tr key={tieUp.hospital2Id}>
                    //         <th scope="row">{index+1}</th>
                    //         <td>{tieUp.hospital2Id}-{tieUp.hospital2Name}</td>
                    //     </tr>
                    return( <tr key={index}>
                        <th scope="row">{index+1}</th>
                        <td>{tieUp[0]}-{tieUp[1]}</td>
                    </tr>
                    )
                    })}
                </tbody>
            </table>
        </div>
    )
}

export default ViewTieUpWithHospital

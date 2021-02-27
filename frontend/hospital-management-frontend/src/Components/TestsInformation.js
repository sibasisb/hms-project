import React, { useEffect,useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css'
import axios from 'axios';

const TestsInformation=()=>{
    const [testFacilities,setTestFacilities]=useState([])
    useEffect(()=>{
        const hospitalAdminId="HAD0998";
        axios.get(`http://localhost:8080/getfacility/${hospitalAdminId}`)
        .then(res=>{
            console.log(res)
            setTestFacilities(res.data)
        })
        .catch(err=>{
            console.log(err)
        })

    },[])

    const fetchResults=()=>{

        const extractValues=(baselines)=>{
            let li=[]
    
            for(let baseline of baselines){
                let l=[]
                for(let i in baseline){
                    l.push(baseline[i])
                }
                li.push(l)
            }
            return (
                li.map((item,index)=>{
                    return (
                            <tr>
                            <td>{item[1]}</td>
                            <td>{item[2]}</td>
                            </tr>
                    )
                })
            )
            
        }
        
        return testFacilities.map((testFacility,index)=>{
            return (
                <tr key={index}>
                    <td>{testFacility.hospitalFacilityId}</td>
                    <td>{testFacility.facility.name}</td>
                    <td>{testFacility.description}</td>
                    <td>{testFacility.remarks}</td>
                    <table className="table table-condensed table-bordered">
                    {extractValues(testFacility.facility.baselines)}
                    </table>
                    <td>&#x20B9;{testFacility.charges}</td>
                </tr>   
            )
        })
    }

    return (
        <div className="container">
            <h1 className="text-center m-md-3 pr-md-2">Information for all test facilities</h1>
            <table className="table table-responsive table-condensed mx-auto mt-3" style={{width:"80%"}}>
                <thead className="mx-auto">
                    <tr>
                    <th scope="col">Facility Id</th>
                    <th scope="col">Facility Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Remarks</th>
                    <th scope="col">Baseline Values</th>
                    <th scope="col">Charges</th>
                    </tr>
                </thead>
                <tbody>
                {fetchResults()}
                </tbody>
            </table>
        </div>
    )
}

export default TestsInformation
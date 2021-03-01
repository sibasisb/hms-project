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
                            <td className="text-center">{item[1]}</td>
                            <td className="text-center">{item[2]}</td>
                            </tr>
                    )
                })
            )
            
        }
        
        return testFacilities.map((testFacility,index)=>{
            return (
                <tr key={index}>
                    <td className="text-center">{testFacility.hospitalFacilityId}</td>
                    <td className="text-center">{testFacility.facility.name}</td>
                    <td className="text-center">{testFacility.description}</td>
                    <td className="text-center">{testFacility.remarks}</td>
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
            <div className="card mt-5 mx-auto" style={{width:"80%"}}>
            <div className="card-header"><h3 className="">Information for all test facilities</h3></div>
            <div className="card-body">
            <table className="table table-bordered table-responsive table-condensed mt-3" style={{width:"100%"}}>
                <thead className="mx-auto">
                    <tr>
                    <th className="text-center" scope="col">Facility Id</th>
                    <th className="text-center" scope="col">Facility Name</th>
                    <th className="text-center" scope="col">Description</th>
                    <th className="text-center" scope="col">Remarks</th>
                    <th className="text-center" scope="col">Baseline Values</th>
                    <th className="text-center" scope="col">Charges</th>
                    </tr>
                </thead>
                <tbody>
                {fetchResults()}
                </tbody>
            </table>
            </div>
            </div>
        </div>
    )
}

export default TestsInformation
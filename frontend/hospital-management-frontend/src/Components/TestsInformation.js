import React, { useEffect,useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css'

const TestsInformation=()=>{
    const [testFacilities,setTestFacilities]=useState([])
    useEffect(()=>{
         const newtestFacilities=[
             {
                facilityId:"FAC000001",
                facilityName:"Blood Test",
                description:"Test for blood ",
                remarks:"OKK",
                hospitalId:"HOS00001",
                values:{
                    HB_Value:"102",
                    Pressure_systolic:"120",
                    Pressure_dia:"80"
                },
                charge:125.00
             },
             {
                facilityId:"FAC000002",
                facilityName:"Blood Test",
                description:"Test for blood ",
                remarks:"UMM",
                hospitalId:"HOS00001",
                values:{
                    HB_Value:"102",
                    Pressure_systolic:"120",
                    Pressure_dia:"80"
                },
                charge:125.00
             }
         ]
         setTestFacilities(newtestFacilities)
    },[])

    const fetchResults=()=>{

        const extractValues=(values)=>{
            let li=[]
    
            for(let i in values){
                li.push([i,values[i]])    
            }
            return (
                li.map((item,index)=>{
                    return (
                            <tr>
                            <td>{item[0]}</td>
                            <td>{item[1]}</td>
                            </tr>
                    )
                })
            )
            
        }
        
        return testFacilities.map((testFacility,index)=>{
            return (
                <tr key={index}>
                    <td>{testFacility.facilityId}</td>
                    <td>{testFacility.facilityName}</td>
                    <td>{testFacility.description}</td>
                    <td>{testFacility.remarks}</td>
                    <table className="table table-condensed table-bordered">
                    {extractValues(testFacility.values)}
                    </table>
                    <td>&#x20B9;{testFacility.charge}</td>
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
                    <th scope="col">Charge</th>
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
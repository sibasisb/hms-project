import React,{useEffect, useState} from 'react';
import 'bootstrap/dist/css/bootstrap.css'
import {Link, useParams} from 'react-router-dom'
import axios from 'axios'

const TestResults=()=>{

    const [testResultList,setTestResultList]=useState([])
    const {patientId}=useParams()
    const {appointmentId}=useParams()
    const [showError,setShowError]=useState(false)

    useEffect(()=>{
        setShowError(false)
        //fetch all test result records from test results table for this patient and appointment

        axios.get('http://localhost:8080/testresults/fetch/' + appointmentId + "/" + patientId)
        .then(res=>{
            console.log(res)
            setTestResultList(res.data)
            if(res.data.length==0)
                setShowError(true)
        })
        .catch(error=>{
            console.log(error)
        })
    },[])

    const extractResults=(result)=>{
        let li=[]

        for(let i in result){
            li.push([i,result[i]])    
        }
        return (
            li.map((item,index)=>{
                return (
                        <tr key={index}>
                        <td className="text-center">{item[0]}</td>
                        <td className="text-center">{item[1]}</td>
                        </tr>
                )
            })
        )
        
    }

    const fetchResults=()=>{
        
        return testResultList.map((testResult,index)=>{
            return (
                <tr key={index}>
                    <td className="text-center" scope="row">{testResult.resultId}</td>
                    <td>{testResult.testName}</td>
                    <table className="table table-condensed table-bordered">
                    {extractResults(testResult.infos)}
                    </table>
                    <td className="text-center">
                    <Link to={"/edittestresult/"+testResult.resultId}>
                    <span className="material-icons pl-3 pt-3" style={{color:"black"}}>create</span>
                    </Link>
                    </td>    
                </tr>   
            )
        })
    }

    return (
        <div className="container">
            <div className="card mt-5 mx-auto" style={{width:"80%"}}>
            <div className="card-header"><h3 className="">Test results of patient {patientId}</h3></div>
            <div className="card-body">
            {
                showError?
                (<div>
                    <div className="alert alert-danger">
                    <h3><strong>No test result for this patient found</strong></h3>
                    </div>
                    <Link to={`/addtestresult/${patientId}/${appointmentId}`} style={{width:"40%"}}>
                    <button className="btn btn-md btn-primary m-2">Add test result</button>
                    </Link>
                </div>):
                (<>
                <table className="table table-bordered table-responsive table-condensed mt-3" >
                    <thead>
                        <tr>
                        <th className="text-center" scope="col">Result Id</th>
                        <th className="text-center" scope="col">Test Name</th>
                        <th className="text-center" scope="col">Test results</th>
                        <th className="text-center" scope="col">Update</th>
                        </tr>
                    </thead>
                    <tbody>
                    {fetchResults()}
                    </tbody>
                </table>
                </>)
            }
            </div>
            </div>
        </div>
    )
}

export default TestResults
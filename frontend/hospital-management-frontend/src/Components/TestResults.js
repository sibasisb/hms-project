import React,{useEffect, useState} from 'react';
import 'bootstrap/dist/css/bootstrap.css'
import {Link, useParams} from 'react-router-dom'
import axios from 'axios'

const TestResults=()=>{

    const [testResultList,setTestResultList]=useState([])
    const {patientId}=useParams()
    const {appointmentId}=useParams()

    useEffect(()=>{
        //fetch all patient records from test results table
        
        axios.get('http://localhost:8080/testresults/fetch/' + appointmentId + "/" + patientId)
        .then(res=>{
            console.log(res)
            setTestResultList(res.data)
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
                        <td>{item[0]}</td>
                        <td>{item[1]}</td>
                        </tr>
                )
            })
        )
        
    }

    const fetchResults=()=>{
        
        return testResultList.map((testResult,index)=>{
            return (
                <tr key={index}>
                    <td scope="row">{testResult.resultId}</td>
                    <td>{testResult.testName}</td>
                    <table className="table table-condensed table-bordered">
                    {extractResults(testResult.infos)}
                    </table>
                    <td>
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
            <h1 className="text-center mb-3">Test results of patient {patientId}</h1>
            <table className="table table-responsive table-condensed mx-auto mt-3" style={{width:"60%"}} >
                <thead>
                    <tr>
                    <th scope="col">Result Id</th>
                    <th scope="col">Test Name</th>
                    <th scope="col">Test results</th>
                    <th scope="col">Update</th>
                    </tr>
                </thead>
                {fetchResults()}
            </table>
        </div>
    )
}

export default TestResults
import React,{useState} from 'react';
import 'bootstrap/dist/css/bootstrap.css'
import {Link, useParams} from 'react-router-dom'

const TestResults=()=>{

    const [editState,setEditState]=useState(false)
    const {patientId}=useParams()
    //fetch all patient records from test results table
    const testResultList=[
        {
            resultId:"TEST000001",
            patientId,
            appointmentId:"AP000001",
            result:{
                HB_Value:"102",
                Pressure_systolic:"120"
            }
        },
        {
            resultId:"TEST000002",
            patientId,
            appointmentId:"AP000001",
            result:{
                HB_Value:"102",
                Pressure_systolic:"120"
            }
        },
        {
            resultId:"TEST000003",
            patientId,
            appointmentId:"AP000001",
            result:{
                HB_Value:"102",
                Pressure_systolic:"120"
            }
        }
    ]

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
                    <td>{testResult.appointmentId}</td>
                    <table className="table table-condensed table-bordered">
                    {extractResults(testResult.result)}
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
                    <th scope="col">Appointment Id</th>
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
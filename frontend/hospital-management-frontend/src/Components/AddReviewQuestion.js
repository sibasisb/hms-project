import React, { useEffect, useState } from 'react'
import 'bootstrap/dist/css/bootstrap.css'
import axios from 'axios'
import { getHeader } from '../helpers/AuthorizationHeader'

const AddReviewQuestion=()=>{

    const [question,setQuestion]=useState("")
    const [showSuccessAlert,setShowSuccessAlert]=useState(false)
    const [showFailureAlert,setShowFailureAlert]=useState(false)

    useEffect(()=>{
        setShowSuccessAlert(false)
        setShowFailureAlert(false)
    },[])

    const handleAddSubmit=(e)=>{
        e.preventDefault()
        if(question.length===0){
            setShowFailureAlert(true)
            return
        }
        axios.post(`http://localhost:8080/reviewquestion/add`,{
            question:question
        },getHeader())
        .then(res=>{
            console.log(res);
            setQuestion("")
            setShowSuccessAlert(true)
        })
        .catch(err=>{
            console.log(err)
            setShowFailureAlert(true)
        })
    }

    return (
        <div className="container">
            <form className="card mt-5" onSubmit={handleAddSubmit} style={{width: "80%"}}>
                <div className="card-header"><h3>Add review question</h3></div>
                <div className="card-body">
                    {
                        showSuccessAlert ?
                        (
                            <div className="alert alert-success">
                                <strong>Success!</strong> Added question successfully!!!!
                            </div>
                        ) :
                        (<></>)
                    }
                    {
                        showFailureAlert ?
                        (
                            <div className="alert alert-danger">
                                <strong>Failure!</strong> Cannot add empty question
                            </div>
                        ) :
                        (<></>)
                    }
                    <div className="form-group form-row">
                        <div className="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <label htmlFor="questionarea">Write a question to add to the questionnaire</label><br/>
                        <input placeholder="Write your question here..." className="form-control input-lg" id="questionarea" type="textarea" rows="50" style={{width:"100%",height:"100%"}} value={question} onChange={(e)=>{setQuestion(e.target.value)}}/>
                        </div>
                    </div>
                    <div className="form-group mt-5">
                        <button className="btn btn-md btn-primary mt-3">Add this question</button>
                    </div>
                </div>
            </form>
        </div>
    )
}

export default AddReviewQuestion;
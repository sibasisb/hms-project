import React, { useEffect, useState } from 'react'
import 'bootstrap/dist/css/bootstrap.css'
import axios from 'axios'
import { useParams } from 'react-router-dom'
import { faEye } from '@fortawesome/free-solid-svg-icons'
import { getHeader } from '../helpers/AuthorizationHeader'

const PatientReview=()=>{

    const [questionList,setQuestionList]=useState([])
    const [answersList,setAnswersList]=useState([])
    const {appointmentId}=useParams()
    const [showSuccessAlert,setShowSuccessAlert]=useState(false)
    const [showFailureAlert,setShowFailureAlert]=useState(false)
    const [showError,setShowError]=useState(false)

    useEffect(()=>{
        setShowError(false)
        setShowSuccessAlert(false)
        setShowFailureAlert(false)
        axios.get(`http://localhost:8080/reviewquestion/get`,getHeader())
        .then(res=>{
            setQuestionList(res.data)
            let newQuestionList=res.data
            let newAnswersList=[]
            newQuestionList.forEach(newQuestion => {
                const answer={
                    questionId:newQuestion.questionId,
                    appointmentId:appointmentId
                }
                newAnswersList.push(answer)
            });
            setAnswersList(newAnswersList)
            if(res.data.length==0)
                setShowError(true)
        })
        .catch(err=>{
            console.log(err);
        })
    },[])

    const handleChange=(e)=>{
        let newAnswersList=answersList
        let nlist=[]
        newAnswersList.forEach((newAnswer)=>{
            let obj={}
            if(newAnswer.questionId==e.target.id){
                obj={...newAnswer,answers:e.target.value}
                nlist.push(obj)   
            }
            else{
                nlist.push(newAnswer)
            }
        })
        setAnswersList(nlist)
    }

    const handleSubmit=(e)=>{
        e.preventDefault()
        console.log(questionList)
        console.log(answersList)
        let flag=0
        answersList.forEach((answer)=>{
            if(answer.answers==null){
                setShowFailureAlert(true)
                setShowSuccessAlert(false)
                flag=1
            }
        })
        if(flag===1)
            return

        axios.post(`http://localhost:8080/feedback/add`,answersList,getHeader())
        .then(res=>{
            console.log(res);
            setShowSuccessAlert(true)
            setShowFailureAlert(false)
        })
        .catch(err=>{
            console.log(err);
            setShowFailureAlert(true)
            setShowSuccessAlert(false)
        })
    }

    const displayQuestions=()=>{
        return (
            questionList.map((question,index)=>{
                return (
                    <div className="list-group-item" key={index}>
                        <div className="form-group">
                        <label><h5>Q{index+1} .  {question.question}</h5></label><br/>
                        <div className="form-check form-check-inline">
                            <input className="form-check-input" type="radio" name={question?.questionId} id={question?.questionId} value={1} onChange={handleChange}/>
                            <label className="form-check-label" htmlFor={question?.questionId}>1</label>
                        </div>
                        <div className="form-check form-check-inline">
                            <input className="form-check-input" type="radio" name={question?.questionId} id={question?.questionId} value={2} onChange={handleChange}/>
                            <label className="form-check-label" htmlFor={question?.questionId}>2</label>
                        </div>
                        <div className="form-check form-check-inline">
                            <input className="form-check-input" type="radio" name={question?.questionId} id={question?.questionId} value={3} onChange={handleChange}/>
                            <label className="form-check-label" htmlFor={question?.questionId}>3</label>
                        </div>
                        <div className="form-check form-check-inline">
                            <input className="form-check-input" type="radio" name={question?.questionId} id={question?.questionId} value={4} onChange={handleChange}/>
                            <label className="form-check-label" htmlFor={question?.questionId}>4</label>
                        </div>
                        <div className="form-check form-check-inline">
                            <input className="form-check-input" type="radio" name={question?.questionId} id={question?.questionId} value={5} onChange={handleChange}/>
                            <label className="form-check-label" htmlFor={question?.questionId}>5</label>
                        </div>
                        </div>
                    </div>
                )
            })
        )
    }

    return (
        <div className="container">
            <form className="card mt-5" onSubmit={handleSubmit}>
                <div className="card-header"><h3>Patient Feedback</h3></div>
                {
                    showError?
                    (<div className="alert alert-danger">
                        <h3><strong>No questions to show!!!</strong></h3>
                    </div>):
                    (
                        <div className="card-body">
                    {
                        showSuccessAlert ?
                        (
                            <div className="alert alert-success">
                                <strong>Thank you</strong> for your feedback!!!!
                            </div>
                        ) :
                        (<></>)
                    }
                    {
                        showFailureAlert ?
                        (
                            <div className="alert alert-danger">
                                <strong>Please answer all questions</strong>
                            </div>
                        ) :
                        (<></>)
                    }
                    <div className="list-group">
                    {displayQuestions()}
                    </div>
                    <button className="btn btn-primary btn-md mt-3">Submit feedback</button>
                    </div>
                    )
                }
                
            </form>
        </div>
    )
}

export default PatientReview;
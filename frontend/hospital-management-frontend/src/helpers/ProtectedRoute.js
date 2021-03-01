import React from 'react'
import { Redirect, Route } from 'react-router-dom'

function ProtectedRoute({component:Component,roles,...rest}) {

   

    function checkAuthentication()
    {    
         let token=localStorage.getItem("token");
        if(token)
        {
           return true
        }
        else{
          console.log("in here");
        return false;
        }

    }

    function checkAuthorization()
    {
        let role=localStorage.getItem("role").split("\"").join("");
        console.log("in here 2");   
        if(roles.includes(role))
       { 
        console.log("in here 3");   
        return true}
        else
        {
            console.log("not in there");
          return false
        }

    }


    return (
        <Route
    {...rest}
    render={(props)=>
      {
        if(!checkAuthentication())
        {
         return <Redirect to="/login"/>
        }
         else
         {if(checkAuthorization())
         return <Component {...props} />
         else
         return <Redirect to="/unauthorized"/>
         }
      }
    }
    />
    )
}

export default ProtectedRoute

import React from 'react'
import { Redirect, Route } from 'react-router-dom'
import jwt_decode from "jwt-decode";

function ProtectedRoute({ component: Component, roles, ...rest }) {


  function checkAuthentication() {
    let token = localStorage.getItem("token");
    if (token) {
      const decode = jwt_decode(token);
      if (decode.exp > Date.now())
        return false
      else
        return true
    }
    else {
      return false;
    }

  }

  function checkAuthorization() {
    let role = localStorage.getItem("role").split("\"").join("");
    if (roles.includes(role)) {
      return true
    }
    else {
      return false
    }

  }


  return (
    <Route
      {...rest}
      render={(props) => {
        if (!checkAuthentication()) {
          return <Redirect to="/login" />
        }
        else {
          if (checkAuthorization())
            return <Component {...props} />
          else
            return <Redirect to="/unauthorized" />
        }
      }
      }
    />
  )
}

export default ProtectedRoute

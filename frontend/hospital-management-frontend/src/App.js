import React, { createContext, useContext, useEffect, useReducer } from 'react'
import {Switch,BrowserRouter,Route, useHistory} from 'react-router-dom'
import './App.css';
import MenuComponent from './Components/MenuComponent';
import HomeComponent from './Components/HomeComponent';
import userReducer, { initialState } from './Reducers/userReducer';
import RegisterComponent from './Components/RegisterComponent';
import LoginComponent from './Components/LoginComponent';

export const UserContext=createContext()

const Routing=()=>{
  const {state,dispatch}=useContext(UserContext)
  const history=useHistory()
  useEffect(()=>{
    
  },[])
  
  return (
    <Switch>
      <Route path="/" exact component={HomeComponent}/>
      <Route path="/register" exact component={RegisterComponent}/>
      <Route path="/login" exact component={LoginComponent}/>
    </Switch>
  )
}

function App() {
  const [state,dispatch]=useReducer(userReducer,initialState)
  return (
    <div>
      <UserContext.Provider value={{state,dispatch}}>
        <BrowserRouter>
          <MenuComponent/>
          {/* <HomeComponent/> */}
          <Routing/>
        </BrowserRouter>
      </UserContext.Provider>
    </div>
  );
}

export default App;
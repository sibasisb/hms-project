import React, { createContext, useContext, useEffect, useReducer } from 'react'
import {Switch,BrowserRouter,Route, useHistory} from 'react-router-dom'
import './App.css';
import MenuComponent from './Components/MenuComponent';
import userReducer, { initialState } from './Reducers/userReducer';

export const UserContext=createContext()

const Routing=()=>{
  const {state,dispatch}=useContext(UserContext)
  const history=useHistory()
  useEffect(()=>{
    
  },[])
  
  return (
    <Switch>
      
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
          <Routing/>
        </BrowserRouter>
      </UserContext.Provider>
    </div>
  );
}

export default App;
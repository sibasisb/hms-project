import React, { createContext, useContext, useEffect, useReducer } from 'react'
import {Switch,BrowserRouter,Route, useHistory} from 'react-router-dom'
import './App.css';
import MenuComponent from './Components/MenuComponent';
import userReducer, { initialState } from './Reducers/userReducer';
import FacilityAddUpdateComponent from './Components/FacilityAddUpdateComponent';
import FacilityUpdateList from './Components/FacilityUpdateListComponent';

//import {BrowserRouter as Router , Switch , Route } from 'react-router-dom';

export const UserContext=createContext()

const Routing=()=>{
  const {state,dispatch}=useContext(UserContext)
  const history=useHistory()
  useEffect(()=>{
    
  },[])
  
  return (
    <Switch>
      <Route path="/addfacility" exact component={FacilityAddUpdateComponent} />
      <Route path="/addfacility/:id"  component={FacilityAddUpdateComponent} />
      <Route path="/updatefacility"  component={FacilityUpdateList} />

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
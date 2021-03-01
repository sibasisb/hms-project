import React, { createContext, useContext, useEffect, useReducer } from "react";
import { Switch, BrowserRouter, Route, useHistory } from "react-router-dom";
import "./App.css";
import Appointment from "./Components/Appointment";
import MenuComponent from "./Components/MenuComponent";
import userReducer, { initialState } from "./Reducers/userReducer";
import EditTestResult from "./Components/EditTestResult";
import TestResults from "./Components/TestResults";
import TestResultsUpdate from "./Components/TestResultsUpdate";
import TestsInformation from "./Components/TestsInformation";
import HomeComponent from "./Components/HomeComponent";
import RegisterComponent from "./Components/RegisterComponent";
import LoginComponent from "./Components/LoginComponent";
import FacilityAddUpdateComponent from "./Components/FacilityAddUpdateComponent";
import FacilityUpdateList from "./Components/FacilityUpdateListComponent";
import ViewAppointment from "./Components/ViewAppointment";
import ApproveAppointments from "./Components/ApproveAppointments";
import Notification from "./Components/Notification";

export const UserContext = createContext();

const Routing = () => {
  const { state, dispatch } = useContext(UserContext);
  const history = useHistory();
  useEffect(() => {}, []);

  return (
    <Switch>
      <Route path="/" exact component={HomeComponent} />
      <Route path="/register" exact component={RegisterComponent} />
      <Route path="/login" exact component={LoginComponent} />
      <Route path="/testresults/:patientId">
        <TestResults />
      </Route>
      <Route path="/testresults">
        <TestResultsUpdate />
      </Route>
      <Route path="/edittestresult/:testResultId">
        <EditTestResult />
      </Route>
      <Route path="/testsinformation">
        <TestsInformation />
      </Route>
      <Route path="/appointment/:patientId">
        <Appointment />
      </Route>
      <Route path="/addfacility" exact component={FacilityAddUpdateComponent} />
      <Route path="/addfacility/:id" component={FacilityAddUpdateComponent} />
      <Route path="/updatefacility" component={FacilityUpdateList} />
      <Route path="/view-appointment/:patientId">
        <ViewAppointment />
      </Route>
      <Route path="/approve-appointment/:serviceId">
        <ApproveAppointments />
      </Route>
      <Route path="/notifications/:patientId">
        <Notification />
      </Route>
    </Switch>
  );
};

function App() {
  const [state, dispatch] = useReducer(userReducer, initialState);
  return (
    <div>
      <UserContext.Provider value={{ state, dispatch }}>
        <BrowserRouter>
          <MenuComponent />
          <Routing />
        </BrowserRouter>
      </UserContext.Provider>
    </div>
  );
}

export default App;

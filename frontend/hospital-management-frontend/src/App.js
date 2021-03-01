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
import PatientDashboard from "./Components/PatientDashboard";
import DoctorDashboard from "./Components/DoctorDashboard";
import HospitalAdminDashboard from "./Components/HospitalAdminDashboard";
import SystemAdminDashboard from "./Components/SystemAdminDashboard";
import PatientTreatmentHistory from "./Components/PatientTreatmentHistory";
import ProtectedRoute from "./helpers/ProtectedRoute";
import { roles } from "./helpers/Roles";
import Unauthorized from "./helpers/Unauthorized";

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
      <ProtectedRoute roles={[roles.patient,roles.doctor]} path="/testresults" component={TestResults}/>
      <Route path="/edittestresult/:testResultId">
        <EditTestResult />
      </Route>
      <ProtectedRoute roles={[roles.patient,roles.doctor]} path="/testsinformation" component={TestsInformation}/>
      <Route path="/appointment/:patientId">
        <Appointment />
      </Route>
      <Route path="/patientdashboard" exact component={PatientDashboard} />
      <Route path="/doctordashboard" exact component={DoctorDashboard} />
      <Route path="/hospitaladmindashboard" exact component={HospitalAdminDashboard} />
      <Route path="/admindashboard" exact component={SystemAdminDashboard} />
      <ProtectedRoute path="/addtreatmenthistory" component={PatientTreatmentHistory} />
      <Route path="/addfacility" exact component={FacilityAddUpdateComponent} />
      <Route path="/addfacility/:id" component={FacilityAddUpdateComponent} />
      <Route path="/updatefacility" component={FacilityUpdateList} />
      <Route path="/unauthorized" exact component={Unauthorized}></Route>
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

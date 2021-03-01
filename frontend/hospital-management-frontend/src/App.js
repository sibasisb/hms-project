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
import AllHospitalsListComponent from "./Components/AllHospitalsListComponent";
import HospitalDetailsComponent from "./Components/HospitalDetailsComponent";
import AllFacilitiesDocotorsComponent  from "./Components/AllfacilitiesDoctorsListComponent";
import FacilityDoctorDetailsComponent from "./Components/FacilityDoctorDetailsComponent";
import InPatientFormComponent from "./Components/InPatientAddUpdateComponent";
import InPatientUpdateListComponent from "./Components/InPatientUpdateListComponent";
import BillingComponent from "./Components/BillingComponent";

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
      <Route path="/hospitals"  component={AllHospitalsListComponent} />
      <Route path="/viewhospital/:id" exact component={HospitalDetailsComponent} />
      <Route path="/facilities/:id"  component={AllFacilitiesDocotorsComponent} />
      <Route path="/doctors/:id"  component={AllFacilitiesDocotorsComponent} />
      <Route path="/viewfacility/:id" exact component={FacilityDoctorDetailsComponent} />
      <Route path="/viewdoctor/:id" exact component={FacilityDoctorDetailsComponent} />
      <Route path="/inpatientform" exact component={InPatientFormComponent} />
      <Route path="/inpatientform/:id" component={InPatientFormComponent} />
      <Route path="/inpatientlist" exact component={InPatientUpdateListComponent} />
      <Route path="/billing" component={BillingComponent} />
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

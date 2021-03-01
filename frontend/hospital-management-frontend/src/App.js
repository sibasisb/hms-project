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
import DoctorViewingPatients from "./Components/DoctorViewingPatients";
import PatientInfoDocView from "./Components/PatientInfoDocView";
import TestInfoDocView from "./Components/TestInfoDocView";
import AddReviewQuestion from "./Components/AddReviewQuestion";
import PatientReview from "./Components/PatientReview";
import TreatmentHistoryPatient from "./Components/TreatmentHistoryPatient";
import AllHospitalsListComponent from "./Components/AllHospitalsListComponent";
import HospitalDetailsComponent from "./Components/HospitalDetailsComponent";
import AllFacilitiesDocotorsComponent  from "./Components/AllfacilitiesDoctorsListComponent";
import FacilityDoctorDetailsComponent from "./Components/FacilityDoctorDetailsComponent";
import InPatientFormComponent from "./Components/InPatientAddUpdateComponent";
import InPatientUpdateListComponent from "./Components/InPatientUpdateListComponent";
import BillingComponent from "./Components/BillingComponent";
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
      <Route path="/doctorviewpatients" exact component={DoctorViewingPatients}/>
      <Route path="/patientInfoDoc/:patientId" exact component={PatientInfoDocView}/>
      <Route path="/testresults" exact component={TestResultsUpdate}/>
      <Route path="/edittestresult/:testResultId" exact component={EditTestResult}/>
      <Route path="/testinfodocview/:testResultId" exact component={TestInfoDocView}/>
      <Route path="/addreviewquestion" exact component={AddReviewQuestion} />
      <Route path="/patientreview/:appointmentId" exact component={PatientReview}/>
      <Route path="/treatmenthistory/:patientId" exact component={TreatmentHistoryPatient}/>
      <ProtectedRoute roles={[roles.patient,roles.doctor]} exact path="/testresults/:patientId/:appointmentId" component={TestResults}/>
      <ProtectedRoute roles={[roles.patient,roles.doctor]} exact path="/testsinformation" component={TestsInformation}/>
      <Route path="/appointment/:patientId" exact component={Appointment}/>
      <Route path="/patientdashboard" exact component={PatientDashboard} />
      <Route path="/doctordashboard" exact component={DoctorDashboard} />
      <Route path="/hospitaladmindashboard" exact component={HospitalAdminDashboard} />
      <Route path="/admindashboard" exact component={SystemAdminDashboard} />
      <ProtectedRoute path="/addtreatmenthistory" exact component={PatientTreatmentHistory} />
      <Route path="/addfacility" exact component={FacilityAddUpdateComponent} />
      <Route path="/addfacility/:id" exact component={FacilityAddUpdateComponent} />
      <Route path="/updatefacility" exact component={FacilityUpdateList} />
      <Route path="/hospitals"  exact component={AllHospitalsListComponent} />
      <Route path="/viewhospital/:id" exact exact component={HospitalDetailsComponent} />
      <Route path="/facilities/:id"  exact component={AllFacilitiesDocotorsComponent} />
      <Route path="/doctors/:id"  exact component={AllFacilitiesDocotorsComponent} />
      <Route path="/viewfacility/:id" exact component={FacilityDoctorDetailsComponent} />
      <Route path="/viewdoctor/:id" exact component={FacilityDoctorDetailsComponent} />
      <Route path="/inpatientform" exact component={InPatientFormComponent} />
      <Route path="/inpatientform/:id" exact component={InPatientFormComponent} />
      <Route path="/inpatientlist" exact component={InPatientUpdateListComponent} />
      <Route path="/billing" exact component={BillingComponent} />
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

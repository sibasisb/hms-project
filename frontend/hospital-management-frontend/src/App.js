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
      <ProtectedRoute roles={[roles.doctor]} path="/doctorviewpatients" exact component={DoctorViewingPatients}/>
      <ProtectedRoute roles={[roles.doctor,roles.patient]} path="/patientInfoDoc/:patientId" exact component={PatientInfoDocView}/>
      <ProtectedRoute roles={[roles.hospital_admin]} path="/testresults" exact component={TestResultsUpdate}/>
      <ProtectedRoute roles={[roles.hospital_admin]} path="/edittestresult/:testResultId" exact component={EditTestResult}/>
      <ProtectedRoute roles={[roles.doctor,roles.patient]} path="/testinfodocview/:testResultId" exact component={TestInfoDocView}/>
      <ProtectedRoute roles={[roles.sys_admin]} path="/addreviewquestion" exact component={AddReviewQuestion} />
      <ProtectedRoute roles={[roles.patient]} path="/patientreview/:appointmentId" exact component={PatientReview}/>
      <ProtectedRoute roles={[roles.patient]} path="/treatmenthistory/:patientId" exact component={TreatmentHistoryPatient}/>
      <ProtectedRoute roles={[roles.hospital_admin]} exact path="/testresults/:patientId/:appointmentId" component={TestResults}/>
      <ProtectedRoute roles={[roles.hospital_admin]} exact path="/testsinformation" component={TestsInformation}/>
      <ProtectedRoute roles={[roles.patient]} path="/appointment/:patientId" exact component={Appointment}/>
      <ProtectedRoute roles={[roles.patient]} path="/patientdashboard" exact component={PatientDashboard} />
      <ProtectedRoute roles={[roles.doctor]} path="/doctordashboard" exact component={DoctorDashboard} />
      <ProtectedRoute roles={[roles.hospital_admin]} path="/hospitaladmindashboard" exact component={HospitalAdminDashboard} />
      <ProtectedRoute roles={[roles.sys_admin]} path="/admindashboard" exact component={SystemAdminDashboard} />
      <ProtectedRoute roles={[roles.doctor]} path="/addtreatmenthistory/:patientId/:doctorId" exact component={PatientTreatmentHistory} />
      <ProtectedRoute roles={[roles.hospital_admin]} path="/addfacility" exact component={FacilityAddUpdateComponent} />
      <ProtectedRoute roles={[roles.hospital_admin]} path="/addfacility/:id" exact component={FacilityAddUpdateComponent} />
      <ProtectedRoute roles={[roles.hospital_admin]} path="/updatefacility" exact component={FacilityUpdateList} />
      <ProtectedRoute roles={[roles.patient]} path="/hospitals"  exact component={AllHospitalsListComponent} />
      <ProtectedRoute roles={[roles.patient]} path="/viewhospital/:id" exact exact component={HospitalDetailsComponent} />
      <ProtectedRoute roles={[roles.patient]} path="/facilities/:id"  exact component={AllFacilitiesDocotorsComponent} />
      <ProtectedRoute roles={[roles.patient]} path="/doctors/:id"  exact component={AllFacilitiesDocotorsComponent} />
      <ProtectedRoute roles={[roles.patient]} path="/viewfacility/:id" exact component={FacilityDoctorDetailsComponent} />
      <ProtectedRoute roles={[roles.patient]} path="/viewdoctor/:id" exact component={FacilityDoctorDetailsComponent} />
      <ProtectedRoute roles={[roles.hospital_admin]} path="/inpatientform" exact component={InPatientFormComponent} />
      <ProtectedRoute roles={[roles.hospital_admin]} path="/inpatientform/:id" exact component={InPatientFormComponent} />
      <ProtectedRoute roles={[roles.hospital_admin]} path="/inpatientlist" exact component={InPatientUpdateListComponent} />
      <ProtectedRoute roles={[roles.hospital_admin]} path="/billing" exact component={BillingComponent} />
      <ProtectedRoute roles={[roles.patient,roles.doctor,roles.hospital_admin,roles.sys_admin]} path="/unauthorized" exact component={Unauthorized} />
      <ProtectedRoute roles={[roles.patient]} path="/view-appointment/:patientId" exact component={ViewAppointment} />
      <ProtectedRoute roles={[roles.doctor, roles.hospital_admin]} path="/approve-appointment/:serviceId" exact component={ApproveAppointments} />
      <ProtectedRoute roles={[roles.patient]} path="/notifications/:patientId" exact component={Notification} />
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

import React, { createContext, useContext, useEffect, useReducer } from "react";
import { Switch, BrowserRouter, Route, useHistory } from "react-router-dom";
import "./App.css";
import Appointment from "./Components/Appointment";
import MenuComponent from "./Components/MenuComponent";
import userReducer, { initialState } from "./Reducers/userReducer";
import EditTestResult from './Components/EditTestResult';
import TestResults from './Components/TestResults';
import TestResultsUpdate from './Components/TestResultsUpdate';
import TestsInformation from './Components/TestsInformation';

export const UserContext = createContext();

const Routing = () => {
	const { state, dispatch } = useContext(UserContext)
	const history = useHistory()
	useEffect(() => {

	}, [])

	return (
		<Switch>
			<Route path="/testresults/:patientId"><TestResults /></Route>
			<Route path="/testresults"><TestResultsUpdate /></Route>
			<Route path="/edittestresult/:testResultId"><EditTestResult /></Route>
			<Route path="/testsinformation"><TestsInformation /></Route>
			<Route path="/appointment/:patientId"><Appointment /></Route>
		</Switch>
	)
}

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

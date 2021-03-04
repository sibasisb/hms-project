import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getHeader } from '../helpers/AuthorizationHeader';

const AddUpdateTieUp = (props) => {
    const [hospitals, setHospitals] = useState([]);

    const tempHospital1 = useParams()['hospital1'];
    const tempHospital2 = useParams()['hospital2'];
    const tieUpId = useParams()['tieUpId'];

    const [hospital1, setHospital1] = useState((tempHospital1 == 0) ? "" : tempHospital1);
    const [hospital2, setHospital2] = useState((tempHospital2 == 0) ? "" : tempHospital2);
    const [error, setErrors] = useState({ hospital1: false, hospital2: false })
    const [errorMsg, setErrorMsg] = useState('');
    const [validate, setValidate] = useState(false);


    useEffect(() => {
        axios
            .get("http://localhost:8080/hospitals", getHeader())
            .then((res) => {
                setHospitals(res.data);
            })
            .catch((error) => console.log(error));

    }, []);

    const checkTieUps = () => {
        let areThereErrors = false;
        const tieUpObject = {
            tieUpId: tieUpId,
            hospital1: {
                hospitalId: hospital1
            },
            hospital2: {
                hospitalId: hospital2
            }
        }

        axios
            .post("http://localhost:8080/tieups/check", tieUpObject, getHeader())
            .then((res) => {
                if (res.data === true) {
                    console.log("they are tied");
                    areThereErrors = true;

                    let newErrors = { hospital1: false, hospital2: false };
                    newErrors.hospital1 = true
                    newErrors.hospital2 = true
                    const newErrorMsg = "Tie up between these hospitals exist";

                    setErrors(newErrors);
                    setErrorMsg(newErrorMsg);
                }
                else {
                    // console.log("would have posted");
                    axios
                        .post("http://localhost:8080/tieups/add", tieUpObject, getHeader())
                        .then((res) => {
                            if (res.status === 201)
                                setValidate(true);
                            else
                                setValidate(false);
                            
                            setErrors({ hospital1: false, hospital2: false });
                            setErrorMsg("");
                        })
                        .catch((error) => console.log(error));

                }
            })
            .catch((error) => console.log(error));
    }

    const validateFields = () => {
        let areThereErrors = false;
        let newErrorMsg = '';

        let newErrors = { hospital1: false, hospital2: false };

        if (hospital1 == '') {
            newErrors.hospital1 = true;
            newErrorMsg = "Please fill mandatory field Hospital 1";
            areThereErrors = true;
        }

        if (hospital2 == '') {
            newErrors.hospital2 = true
            newErrorMsg = "Please fill mandatory field Hospital 2";
            areThereErrors = true;
        }

        if (hospital1 === hospital2) {
            newErrors.hospital1 = true
            newErrors.hospital2 = true
            newErrorMsg = "Both hospitals are same";
            areThereErrors = true;
        }

        setErrors(newErrors);
        setErrorMsg(newErrorMsg);
        return (!areThereErrors);
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        if (validateFields()) {
            checkTieUps();
        }
    };


    return (
        <div className="container mt-3">
            <div className="card">
                <h5 className="card-header"> Add/Update tie-up between hospitals </h5>
                <div className="card-body">
                    <form
                        className="container needs-validation"
                        onSubmit={handleSubmit}
                        noValidate
                    >
                        {(errorMsg != '') ? (
                            <div className="alert alert-danger" role="alert">
                                {errorMsg}
                            </div>
                        ) : (
                                ""
                            )}
                        {validate ? (
                            <div className="alert alert-success" role="alert">
                                Form was successfully submitted
                            </div>
                        ) : (
                                ""
                            )}

                        <div className="form-group">
                            <label htmlFor="hospital1_name">Hospital 1</label>
                            <select
                                className={
                                    error.hopital1
                                        ? "is-invalid form-control"
                                        : "form-control"
                                }
                                id="hospital1_name"
                                value={hospital1}
                                onChange={(event) =>
                                    setHospital1(event.target.value)
                                }
                                required
                            >
                                <option value=""> choose an hospital </option>
                                {hospitals.map((hopital) => (
                                    <option
                                        key={hopital.hospitalId}
                                        value={hopital.hospitalId}
                                    >
                                        {hopital.name}
                                    </option>
                                ))}
                            </select>
                        </div>

                        <div className="form-group">
                            <label htmlFor="hospital_name">Hospital 2</label>
                            <select
                                className={
                                    error.hopital2
                                        ? "is-invalid form-control"
                                        : "form-control"
                                }
                                id="hospital_name"
                                value={hospital2}
                                onChange={(event) =>
                                    setHospital2(event.target.value)
                                }
                                required
                            >
                                <option value=""> choose an hospital </option>
                                {hospitals.map((hopital) => (
                                    <option
                                        key={hopital.hospitalId}
                                        value={hopital.hospitalId}
                                    >
                                        {hopital.name}
                                    </option>
                                ))}
                            </select>
                        </div>

                        <input type="submit" className="btn btn-primary" />
                    </form>
                </div>
            </div>
        </div>

    );
}

export default AddUpdateTieUp;
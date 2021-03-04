import React, { useState, useEffect } from 'react'

function HospitalAdminForm(props) {

    const error = {
        borderColor: "#ff0000"
    }

    const noerror =
        { borderColor: "#ced4da" }

    console.log(props.errors);
    console.log(props.state)
    const initialState = {
        name: "",
        phone: "",
        website: "",
        addr1: "",
        addr2: "",
        city: "",
        state: "",
        zip: ""
    }
    // const initialState=props.state
    const [state,
        setstate] = useState(initialState)

    useEffect(() => {
        props.changeAdminDetails(state)
    }, [state])

    function changeValue(event) {
        setstate({
            ...state,
            [event.target.name]: event.target.value
        })
    }
    return (
        <div>
            <div className="row mb-3">
                <div className="col">
                    <label htmlFor="name">
                        Hospital Name</label>
                    <input
                        type="text"
                        name="name"
                        id="name"
                        className="form-control"
                        style={props.errors?.name ? error : noerror}
                        onChange={changeValue} />

                </div>

            </div>
            <div className="row mb-3">
                <div className="col-md-6">
                    <label htmlFor="phone">
                        Hospital Phone Number</label>
                    <input
                        type="text"
                        name="phone"
                        id="phone"
                        className="form-control"
                        style={props.errors?.phone ? error : noerror}
                        onChange={changeValue} />
                    <small className="text-danger">{props.errors?.phone}</small>
                </div>
                <div className="col-md-6">
                    <label htmlFor="website">
                        Hospital Website
                    </label>
                    <input
                        type="text"
                        name="website"
                        id="website"
                        className="form-control"
                        style={props.errors?.website ? error : noerror}
                        onChange={changeValue} />
                </div>
            </div>

            <div className="row mb-3">
                <div className="col-12">
                    <b>Hospital Address</b>
                </div>
            </div>

            <div className="row mb-3">
                <div className="col-12">
                    <label htmlFor="addr">Addr line 1</label>
                    <input
                        type="text"
                        className="form-control"
                        id="addr1"
                        name="addr1"
                        placeholder="Addr line 1"
                        onChange={changeValue}
                        style={props.errors?.addr1 ? error : noerror}
                    />

                </div>
            </div>
            <div className="row mb-3">
                <div className="col-12">
                    <label htmlFor="addr">Addr line 2</label>
                    <input
                        type="text"
                        className="form-control"
                        id="addr2"
                        name="addr2"
                        onChange={changeValue}
                        placeholder="Addr line 2"
                    />

                </div>
            </div>

            <div className="row">
                <div className="col-md-4 mb-3">
                    <label htmlFor="city">City</label>
                    <input
                        type="text"
                        className="form-control"
                        id="city"
                        name="city"
                        placeholder="City"
                        onChange={changeValue}
                        style={props.errors?.city ? error : noerror} />

                </div>
                <div className="col-md-5 mb-3">
                    <label htmlFor="state">State</label>
                    <input
                        type="text"
                        className="form-control"
                        id="state"
                        name="state"
                        placeholder="State"
                        onChange={changeValue}
                        style={props.errors?.state ? error : noerror} />

                </div>
                <div className="col-md-3 mb-3">
                    <label htmlFor="zip">Zip</label>
                    <input
                        type="text"
                        className="form-control"
                        id="zip"
                        name="zip"
                        placeholder="Zip"
                        onChange={changeValue}
                        style={props.errors?.zip ? error : noerror} />

                </div>
            </div>
        </div>

    )
}

export default HospitalAdminForm

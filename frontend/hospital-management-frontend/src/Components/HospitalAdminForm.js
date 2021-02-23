import React, {useState, useEffect} from 'react'

function HospitalAdminForm(props) {

    const error={
        borderColor:"#ff0000"
    }

    const noerror=
    {borderColor:"#ced4da"}

    console.log(props.errors);
    console.log(props.state)
    const initialState = {
        hospitalName: "",
        phone:"",
        website:"",
        addr1:"",
        addr2:"",
        city:"",
        state:"",
        zip:""
    }
   // const initialState=props.state
    const [state,
        setstate] = useState(initialState)

    useEffect(() => {
        console.log(state)
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
                <div class="col">
                    <label htmlFor="name">
                        Hospital Name</label>
                    <input
                        type="text"
                        name="hospitalName"
                        id="hospitalName"
                        class="form-control"
                        style={props.errors?.hospitalName? error:noerror}
                        onChange={changeValue}/>
                    
                </div>
               
            </div>
            <div className="row mb-3">
                <div class="col-md-6">
                    <label htmlFor="phone">
                        Hospital Phone Number</label>
                    <input
                        type="text"
                        name="phone"
                        id="phone"
                        class="form-control"
                        style={props.errors?.phone? error:noerror}
                        onChange={changeValue}/>
                        <small className="text-danger">{props.errors?.phone}</small>
                </div>
                <div class="col-md-6">
                    <label htmlFor="website">
                        Hospital Website
                    </label>
                    <input
                        type="text"
                        name="website"
                        id="website"
                        class="form-control"
                        style={props.errors?.website? error:noerror}
                        onChange={changeValue}/>
                </div>
            </div>

            <div class="row mb-3">
                <div className="col-12">
                    <b>Hospital Address</b>
                </div>
            </div>

            <div class="row mb-3">
                <div className="col-12">
                    <label for="addr">Addr line 1</label>
                    <input
                        type="text"
                        class="form-control"
                        id="addr1"
                        name="addr1"
                        placeholder="Addr line 1"
                        onChange={changeValue}
                        style={props.errors?.addr1? error:noerror}
                        />
                    
                </div>
            </div>
            <div class="row mb-3">
                <div className="col-12">
                    <label for="addr">Addr line 2</label>
                    <input
                        type="text"
                        class="form-control"
                        id="addr2"
                        name="addr2"
                        onChange={changeValue}
                        placeholder="Addr line 2"
                        />
                  
                </div>
            </div>

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label for="city">City</label>
                    <input
                        type="text"
                        class="form-control"
                        id="city"
                        name="city"
                        placeholder="City"
                        onChange={changeValue}
                        style={props.errors?.city? error:noerror}/>
                   
                </div>
                <div class="col-md-3 mb-3">
                    <label for="state">State</label>
                    <input
                        type="text"
                        class="form-control"
                        id="state"
                        name="state"
                        placeholder="State"
                        onChange={changeValue}
                        style={props.errors?.state? error:noerror}/>
                    
                </div>
                <div class="col-md-3 mb-3">
                    <label for="zip">Zip</label>
                    <input
                        type="text"
                        class="form-control"
                        id="zip"
                        name="zip"
                        placeholder="Zip"
                        onChange={changeValue}
                        style={props.errors?.zip? error:noerror}/>
                  
                </div>
            </div>
        </div>

    )
}

export default HospitalAdminForm

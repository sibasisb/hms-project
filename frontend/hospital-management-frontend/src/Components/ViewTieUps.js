import { faPen, faPlus } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { getHeader } from '../helpers/AuthorizationHeader';

const ViewTieUps = (props) => {
    const [tieups, setTieups] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/tieups/', getHeader()).then((res) => {
            setTieups(res.data);
        }).catch((error) => console.log(error));
    }, []);

    return (
        <div className="container">
            <table className="table table-hover mt-5">
                <thead>
                    <tr>
                        <th scope="col"> Id </th>
                        <th scope="col"> Hospital 1 </th>
                        <th scope="col"> Hospital 2 </th>
                        <th scope="col"> Update </th>
                    </tr>
                </thead>
                <tbody>
                    {tieups.map((tieup, index) => (
                        <tr key={tieup.tieUpId}>
                            <th scope="row">{index + 1}</th>
                            <td> <Link to={`/viewhospital/${tieup.hospital1Id}`} >{`${tieup.hospital1Id} - ${tieup.hospital1Name}`} </Link></td>
                            <td> <Link to={`/viewhospital/${tieup.hospital2Id}`} >{`${tieup.hospital2Id} - ${tieup.hospital2Name}`} </Link></td>
                            <td>
                                <Link to={`/tieup/add/${tieup.hospital1Id}/${tieup.hospital2Id}/${tieup.tieUpId}`}>
                                    <FontAwesomeIcon icon={faPen} className="mr-2" />
                    Update
                                </Link>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <div className="w-100 d-flex justify-content-center">

                <Link to="/tieup/add/0/0/0" className="btn btn-primary">
                    <FontAwesomeIcon icon={faPlus} className="mr-2" />
                    Add more
                    </Link>
            </div>
        </div>
    );
}

export default ViewTieUps;
import React, { useState, useEffect } from 'react'
import { listDepartments, deleteDepartment} from '../../services/DepartmentService';
import { Link , useNavigate } from 'react-router-dom'

export const ListDepartmentComponent = () => {

    const [departments, setDepartments] = useState([]);
   const navigate = useNavigate();

    useEffect(() => {
        getAllDepartments();
    } ,[])

    function getAllDepartments() {
        listDepartments().then((response) => {
            setDepartments(response.data);
        }).catch(error => {
            console.error(error);
        })
    }

    const updateDepartment = (id) => {
        navigate(`/edit-department/${id}`)
    }

    const removeDepartment = (id) => {
        deleteDepartment(id).then((response) => {
            getAllDepartments();
        }).catch(error => {
            console.error(error);
        })
    }

  return (
    <div className='container'> 
        <h2 className='text-center'>List of Departments</h2>
        <Link to="/add-department" className='btn btn-primary mb-2'>Add Department</Link>
        <table className='table table-striped table-bordered'>
            <thead>
                <th>Department ID</th>
                <th>Department Name</th>
                <th>Department Description</th>
            </thead>
            <tbody>
                {
                    departments.map(department => (
                            <tr key={department.id}>
                                <td>{department.id}</td>
                                <td>{department.departmentName}</td>
                                <td>{department.departmentDescription}</td>
                                <td>
                                    <button className='btn btn-info' onClick={() => updateDepartment(department.id)}>Update</button>
                                    <button className='btn btn-danger' onClick={() => removeDepartment(department.id)}
                                        style={{marginLeft: '10px'}}
                                    >Delete</button>
                                </td>
                            </tr>
                        )
                    )
                }
            </tbody>
        </table>
    </div>
  )
}
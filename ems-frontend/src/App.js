import './App.css';
import { ListEmployeeComponent } from './components/Employees/ListEmployeeComponent';
import { EmployeeComponent } from './components/Employees/EmployeeComponent'
import { HeaderComponent } from './components/Employees/HeaderComponent'
import { FooterComponent } from './components/Employees/FooterComponent'
import { ListDepartmentComponent } from './components/Departments/ListDepartmentComponent'
import { DepartmentComponent } from './components/Departments/DepartmentComponent';
import { BrowserRouter, Routes, Route } from 'react-router-dom'

function App() {
  return (
    <>
      <BrowserRouter>
        <HeaderComponent />
        <Routes>
          <Route path='/' element= {<ListEmployeeComponent />} />
          <Route path='/employees' element= {<ListEmployeeComponent />} />
          <Route path='/add-employee' element= {<EmployeeComponent />} />
          <Route path='/edit-employee/:id' element= {<EmployeeComponent />} />
          <Route path='/departments' element= {<ListDepartmentComponent />} />
          <Route path='/add-department' element= {<DepartmentComponent />} />
          <Route path='/edit-department/:id' element= {<DepartmentComponent />} />
        </Routes>
        <FooterComponent />
      </BrowserRouter>
    </>
  );
}

export default App;

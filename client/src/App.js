import './App.css';
import Header from './components/Header';
import Register from './components/Register';
import Login from './components/Login';
import React from 'react';
import{Link, Route, Routes} from "react-router-dom"


function App() {

  return ( 
    <>
      <nav>
        <ul>
          <li>
            <Link to="/">Header</Link>
          </li>
          <li>
            <Link to="/register">Register</Link>
          </li>
          <li>
            <Link to="/login">Login</Link>
          </li>
        </ul>
      </nav>
      <Routes>
        <Route path="/" element={<Header/>}/>
        <Route path="/register" element={<Register/>}/>
        <Route path="/login" element={<Login/>}/>
      </Routes>
    </>
  );
}

export default App;

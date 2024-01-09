import './App.css';
import Header from './components/Header';
import Register from './components/Register';
import Login from './components/Login';
import SolarWatch from './components/SolarWatch';
import React, {useState} from 'react';
import{Link, Route, Routes} from "react-router-dom"


function App() {

  const [user ,setUser] = useState('No user');
  const [jwt, setJwt] = useState('')


  return ( 
    <>
      <nav>
        <ul>
          <li>
            <Link to="/"><button type="button">Header</button></Link>
          </li>
          <li>
            <Link to="/register"><button type="button">Register</button></Link>
          </li>
          <li>
            <Link to="/login"><button type="button">Login</button></Link>
          </li>
          <li>
            <Link to="/solar-watch"><button type="button">Solar Watch</button></Link>
          </li>
        </ul>
      </nav>

      <h1>You are logged as {user}</h1>

      <Routes>
        <Route path="/" element={<Header/>}/>
        <Route path="/register" element={<Register/>}/>
        <Route path="/login" element={<Login setJwt={setJwt} setUser={setUser} />}/>
        <Route path="/solar-watch" element={<SolarWatch jwt={jwt} user={user}/>}/>
      </Routes>
    </>
  );
}

export default App;

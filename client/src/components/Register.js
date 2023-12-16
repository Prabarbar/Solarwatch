import { useState } from "react"
import { useNavigate} from "react-router-dom";

export default function Register(){

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const navigate = useNavigate()


    function handleSubmit(event){
        event.preventDefault()
        // setSubmitted(true)
        console.log("Registration completed!")
        fetch("http://localhost:8080/open-access/user/register",{
          method: 'POST',
          headers: {'Content-Type': 'application/json'},
          body: JSON.stringify({username:username, password:password})
        })
      }


    function handleUsername(e){
        console.log(e.target.value)
        setUsername(e.target.value)
    }

    function handlePassword(e){
        console.log(e.target.value)
        setPassword(e.target.value)
    }

    return (
        <>
        <form onSubmit={handleSubmit}>
            <h1>Registration</h1>
            <div className="registration">
                <ul>
                    <label>Username</label>
                    <input onChange={handleUsername} type="text"/>
                </ul>
                <ul>
                    <label>Password</label>
                    <input onChange={handlePassword} type="passowrd"/>
                </ul>
            </div>
            <button onClick={handleSubmit}>Register</button>
        </form>
        <button onClick={()=>navigate(-1)}>Back</button>
        </>
    )
}
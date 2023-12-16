import { useState } from "react"
import { useNavigate} from "react-router-dom";

export default function Login(){

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const navigate = useNavigate()


    async function handleSubmit(event){
        event.preventDefault()
        const postRes = await fetch("http://localhost:8080/open-access/user/login",{
          method: 'POST',
          headers: {'Content-Type': 'application/json'},
          body: JSON.stringify({username:username, password:password})
        })
        const info = await postRes.json()
        console.log("Login completed!")
        console.log(info)
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
            <h1>Login</h1>
            <div className="login">
                <ul>
                    <label>Username</label>
                    <input onChange={handleUsername} type="text"/>
                </ul>
                <ul>
                    <label>Password</label>
                    <input onChange={handlePassword} type="passowrd"/>
                </ul>
            </div>
            <button onClick={handleSubmit}>login</button>
        </form>
        <button onClick={()=>navigate(-1)}>Back</button>
        </>
    )
}
import { useNavigate } from "react-router-dom"
import react, {useState} from "react"

export default function SolarWatch({jwt, user}){

    const navigate = useNavigate()

    const [city, setCity] = useState('')
    const [date, setDate] = useState('')
    const [sunrise, setSunrise] = useState('No sunrise')
    const [sunset, setSunset] = useState('No sunset')

    async function handleSubmit(event){
        event.preventDefault()
        const getRes = await fetch(`http://localhost:8080/get?city=${city}&date=${date}`, {
            headers: {'Authorization' : `Bearer ${jwt}`}
          })
        const info = await getRes.json();
        setSunrise(info.sunrise)
        setSunset(info.sunset)
    }


    return(
        <>
        <form onSubmit={handleSubmit}>
        <ul>
            <label>City</label>
            <input onChange={(e)=>setCity(e.target.value)} type="text"/>
        </ul>
        <ul>
            <label>Date</label>
            <input onChange={(e)=>setDate(e.target.value)} type="text"/>
        </ul>
        </form>
        <button onClick={handleSubmit}>Send</button>
        <button onClick={()=>navigate("/")}>Back</button>
        <p>Sunrise at: {sunrise}</p>
        <p>Sunset at: {sunset}</p>
        </>
    )
}
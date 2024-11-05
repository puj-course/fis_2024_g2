import { useParams } from "react-router-dom";
import SongList from "../../components/songList/SongList";
import { useEffect, useState } from "react";

const Album = () => {

    let {name} = useParams();
    const [songs, setSongs] = useState([]);

    useEffect(() => {
        bringSongs();
    }, [])

    const bringSongs = async () => {
        try {
            const token = localStorage.getItem("token");
            const response = await fetch(`http://localhost:8080/canciones_album?nombreAlbum=${name}`, {
                method: "GET",
                headers: {
                    'Authorization': `Bearer ${token}`,
                    "Content-Type": "application/json"
                }
            })
            const data = await response.json();

            if(response.ok) {
                setSongs(data);
            } else {
                console.log("upsi");
            }

        } catch (error) {
            console.error(error);
        }
    }

    return (
        <div className="p-8">
            <h1 className="text-2xl">Album</h1>

            <SongList title={name} songs={songs} howMuch={songs.length}/>

            {console.log(name)}
        </div>
    );

}

export default Album;
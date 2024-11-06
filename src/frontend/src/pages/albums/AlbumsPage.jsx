import { useEffect, useState } from "react";
import Album from "../../components/album/Album";
import Spinner from "../../components/spinner/Spinner";
const AlbumPage = () => {
    
    const [albums, setAlbums] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        bringAlbums();
    }, [])

    const bringAlbums = async () => {
        try {
            const token = localStorage.getItem("token");
            const response = await fetch("http://localhost:8080/album", {
                method: "GET",
                headers: {
                    'Authorization': `Bearer ${token}`,
                    "Content-Type": "application/json"
                }
            })
            const data = await response.json();

            if(response.ok) {
                setAlbums(data);
                setLoading(false);
            } else {
                console.error(data);
            }
        } catch(error) {
            console.error(error);
        }
    }

    if(loading) {
        return <Spinner/>
    }

    return (
        <div className="p-8">
            <h1 className="text-3xl mb-4">Albums</h1>

            <div className="grid grid-cols-3 gap-6">
                {

                    albums.length > 0 ? (albums.map((album, index) => {
                        return <Album key={index} nombre={album.nombre} imagenUrl={album.imagenUrl} fechaLanzamiento={album.fechaLanzamiento}></Album>
                    })) : <h1>...</h1>

                }

            </div>
        </div>
    );
}



export default AlbumPage;
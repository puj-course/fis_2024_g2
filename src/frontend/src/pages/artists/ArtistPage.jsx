import ArtistCard from "../../components/artistCard/ArtistCard";

import { musicData } from "../../../data/musicData";
import { useEffect, useState } from "react";

const ArtistsPage = () => {
    const [artist, setArtist] = useState([]);

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
                setArtist(data);
            } else {
                console.error(data);
            }
        } catch(error) {
            console.error(error);
        }
    }
    
    return (
        <div className="p-8">
            <h1 className="text-3xl mb-4">Artists</h1>
            <div className="grid grid-cols-1 gap-5 md:grid-cols-2 lg:grid-cols-3">
                {musicData?.artists?.map((artist) => {
                    return <ArtistCard key={artist.id} artist={artist} />;
                })}
            </div>
        </div>
    );
};

export default ArtistsPage;

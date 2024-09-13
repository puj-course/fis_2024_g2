import { useParams } from "react-router-dom";

import { musicData } from "../../../data/musicData";

import { useEffect, useState } from "react";

const ArtistPage = () => {
    let { id } = useParams();
    const [artist, setArtist] = useState(null);

    useEffect(() => {
        const artistData = musicData.artists.find(
            (artist) => artist.id === parseInt(id)
        );
        artistData && setArtist(artistData);
    }, []);

    return artist ? (
        <div className="p-8">
            {/* <div className="w-full h-96 p-8 flex flex-col justify-end relative">
                <img
                    src={artistPhoto}
                    alt="Artist Photo"
                    className="absolute top-0 left-0 w-full h-full object-cover"
                />
                <div className="p-4 flex flex-col gap-4">
                    <h3 className="text-6xl text-white font-semibold z-40">
                        {artist.name}
                    </h3>
                    <span className="text-xl z-40">Artista verificado</span>
                </div>
            </div>

            <p>{artist.bio}</p> */}
            <div className="w-full">
                <div className="flex flex-col items-center gap-4">
                    <img
                        src={artist.profileImage}
                        alt="Artist"
                        className="w-56 h-56 rounded-full object-cover"
                    />
                    <h1 className="text-3xl font-semibold">{artist.name}</h1>
                    <p className="dark:text-gray-300 text-gray-600 text-base text-center">{artist.bio}</p>
                </div>
            </div>

        </div>
    ) : (
        <div className="p-4">
            <h1>El artista no existe</h1>
        </div>
    );
};

export default ArtistPage;

import ArtistCard from "../../components/artistCard/ArtistCard";

import { musicData } from "../../../data/musicData";

const ArtistsPage = () => {
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

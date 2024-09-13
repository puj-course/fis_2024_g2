import { useNavigate } from "react-router-dom";

const ArtistCard = ({artist}) => {
    const navigate = useNavigate();
    
    const handleNavigation = (path) => {
        navigate(path);
    }

    return (
        <div className="bg-gray-200 dark:bg-zinc-800 p-6 w-full flex flex-col items-center gap-2 cursor-pointer relative rounded hover:bg-gray-300 dark:hover:bg-zinc-700 transition-all" onClick={() => handleNavigation(`/artist/${artist.id}`)}>
            <img src={artist?.profileImage} alt={artist?.name} className="rounded-full w-56 h-56 object-cover"/>
            <h3 className="text-2xl font-semibold">{artist?.name}</h3>
            <p className="font-normal text-center text-gray-600 dark:text-gray-300">{artist?.bio}</p>
        </div>
    );
}

export default ArtistCard;
import { FaPlay } from "react-icons/fa";
import { usePlayer } from "../../../data/PlayerProvider";

const PlayButton = ({ songUrl, songName, songImg }) => {
    const { playTrack } = usePlayer();

    const song = {
        audioUrl: songUrl,
        songName,
        songImg,
    };

    return (
        <div
            onClick={() => {
                playTrack(song);
            }}
            className="flex items-center justify-center p-2.5 bg-green-600 hover:bg-green-700 active:bg-green-800 rounded-full cursor-pointer transition-all duration-300 ease-in-out transform hover:scale-110"
        >
            <FaPlay color="#fff" size={10} />
        </div>
    );
};

export default PlayButton;


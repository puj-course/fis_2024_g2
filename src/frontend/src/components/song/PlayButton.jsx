import { FaPlay } from "react-icons/fa";
import { FaStop } from "react-icons/fa";
import { usePlayer } from "../../../data/PlayerProvider";

const PlayButton = ({ songUrl, songName, songImg }) => {
    const { playTrack, addToQueue, isPlaying, currentTrack } = usePlayer();

    const song = {
        audioUrl: songUrl,
        songName,
        songImg,
    };

    return (
        <div
            onClick={() => playTrack(song)}
            className={`flex items-center justify-center p-1.5 text-center rounded-full cursor-pointer bg-green-700`}
        >
            
                <FaPlay color="eee" size={10} />
                
        </div>
    );
};

export default PlayButton;

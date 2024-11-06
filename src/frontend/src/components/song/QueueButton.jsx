import { MdQueueMusic } from "react-icons/md"; // Usamos un ícono relacionado con listas o cola
import { usePlayer } from "../../../data/PlayerProvider";

const QueueButton = ({songUrl, songName, songImg}) => {
    const {addToQueue} = usePlayer();

    const song = {
        audioUrl: songUrl,
        songName,
        songImg,
    };

    return (
        <button onClick={() => {addToQueue(song)}}
            className="w-8 h-8 flex justify-center items-center rounded-full bg-blue-500 hover:bg-blue-600 text-white transition-colors duration-300 focus:outline-none focus:ring-2 focus:ring-blue-400">
                <MdQueueMusic className="w-5 h-5"/>
        </button>
    );
}

export default QueueButton;

import { usePlayer } from "../../../data/PlayerProvider";

const QueueComponent = () => {

    const {queue} = usePlayer();

    return (
        <div className="p-8">
        <h2 className="text-2xl">Queue of Songs</h2>
        <ul className="p-4">
            {
                queue.length > 0 ? queue.map((song, index) => (
                    <li key={index} className="mt-2">
                        <img src={song.songImg} alt="Song cover" className="w-8 h-8 object-cover rounded-full"/>
                        <span>{song.songName}</span>
                    </li>
                )) : <li>No songs in queue</li>
            }
        </ul>
        </div>
    );
}

export default QueueComponent;
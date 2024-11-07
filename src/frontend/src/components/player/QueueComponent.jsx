import { usePlayer } from "../../../data/PlayerProvider";

const QueueComponent = () => {
  const { queue } = usePlayer();

  return (
    <div className="p-8 bg-gray-100 dark:bg-zinc-800 rounded-lg shadow-lg max-w-xl mx-auto mt-10">
      <h2 className="text-3xl font-semibold text-gray-800 dark:text-white mb-6">Queue of Songs</h2>
      <ul className="space-y-4">
        {queue.length > 0 ? (
          queue.map((song, index) => (
            <li
              key={index}
              className="flex items-center gap-4 p-4 bg-white dark:bg-zinc-700 rounded-lg shadow-sm hover:bg-gray-200 dark:hover:bg-zinc-600 transition duration-200"
            >
              {/* Imagen de la carátula */}
              <img
                src={song.songImg}
                alt="Song cover"
                className="w-14 h-14 object-cover rounded-full border-2 border-blue-500"
              />

              {/* Nombre de la canción */}
              <div className="flex-1">
                <p className="text-lg font-semibold text-gray-900 dark:text-white truncate">{song.songName}</p>
              </div>
            </li>
          ))
        ) : (
          <li className="text-gray-600 dark:text-gray-400 text-lg">No songs in queue</li>
        )}
      </ul>
    </div>
  );
};

export default QueueComponent;

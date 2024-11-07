import { useContext, useEffect } from "react";
import { usePlayer } from "../../../data/PlayerProvider";
import { FaPlay, FaPause, FaBackward, FaForward } from "react-icons/fa";
import exp from "constants";
import { useNavigate } from "react-router-dom";

const PlayerComponent = ({expanded}) => {
  const navigate = useNavigate();

  const handleNavigation = (path) => {
    navigate(path);
}
  
  const {
    currentTrack,
    isPlaying,
    togglePlayPause,
    skipForward,
    skipBackward,
    currentTime,
    duration,
    paused
  } = usePlayer();

  const formatTime = (seconds) => {
    const minutes = Math.floor(seconds / 60);
    const secs = Math.floor(seconds % 60);
    return `${minutes}:${secs < 10 ? "0" : ""}${secs}`;
  };

  return (
    <div
      className={`${
        isPlaying === false && paused === false ? "hidden" : ""
      } 
      ${
        expanded ? "pl-[19rem]" : "pl-[80px]"
      }  
    w-screen fixed bottom-0 left-0 h-20 bg-gray-300 dark:bg-zinc-900 dark:text-white transition-colors flex items-center justify-between p-8`}
    >
      {/* Información de la canción */}
      <div className="flex items-center gap-4">
        <div className="w-16 h-16 bg-gray-400 rounded-full overflow-hidden">
          {/* Aquí podrías mostrar la carátula del álbum */}
          <img
            src={currentTrack?.songImg}
            alt="Album"
            className="w-full h-full object-cover"
          />
        </div>
        <p className="text-lg font-semibold truncate">{currentTrack?.songName}</p>
      </div>

      {/* Controles de reproducción */}
      <div className="flex items-center gap-4">
        <button
          onClick={() => skipBackward(5)}
          className="p-2 bg-gray-600 text-white rounded-full hover:bg-gray-700 transition"
        >
          <FaBackward size={20} />
        </button>
        <button
          onClick={togglePlayPause}
          className="p-2 bg-blue-500 text-white rounded-full hover:bg-blue-600 transition"
        >
          {isPlaying ? <FaPause size={24} /> : <FaPlay size={24} />}
        </button>
        <button
          onClick={() => skipForward(5)}
          className="p-2 bg-gray-600 text-white rounded-full hover:bg-gray-700 transition"
        >
          <FaForward size={20} />
        </button>
        <button onClick={() => handleNavigation('/queue')} className="bg-green-700 py-2 px-4 rounded-full text-white">
          Go to Queue
        </button>
      </div>

      {/* Barra de progreso */}
      <div className="flex items-center gap-2">
        <span className="text-sm">{formatTime(currentTime)}</span>
        <input
          type="range"
          value={(currentTime / duration) * 100 || 0}
          max="100"
          onChange={(e) =>
            (audioRef.current.currentTime = (e.target.value / 100) * duration)
          }
          className="w-32 h-1 bg-gray-400 rounded-lg appearance-none cursor-pointer"
        />
        <span className="text-sm">{formatTime(duration)}</span>
      </div>
    </div>
  );
};

export default PlayerComponent;

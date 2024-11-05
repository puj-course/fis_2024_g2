import { usePlayer } from "../../../data/PlayerProvider";

const PlayerComponent = () => {
    const { currentTrack, isPlaying, togglePlayPause, skipForward, skipBackward, currentTime, duration } = usePlayer();
    
    const formatTime = (seconds) => {
        const minutes = Math.floor(seconds / 60);
        const secs = Math.floor(seconds % 60);
        return `${minutes}:${secs < 10 ? '0' : ''}${secs}`;
      };

    return(
        <div className={`${!isPlaying ? 'hidden' : '' }  w-screen fixed bottom-0 left-0 h-20 bg-gray-300 dark:bg-zinc-900 dark:text-white transition-colors flex gap-7 items-center justify-center p-8`}>
            <p>{currentTrack?.songName}</p>
            <button onClick={() => skipBackward(5)}>Retroceder 5 segundos</button>
            <button onClick={togglePlayPause}> {isPlaying ? 'pause' : 'play'} </button>
            <button onClick={() => skipForward(5)}>Adelantar 5 segundos</button>
            <span>{formatTime(currentTime)}</span> / <span>{formatTime(duration)}</span>
        </div>
    );
}

export default PlayerComponent;
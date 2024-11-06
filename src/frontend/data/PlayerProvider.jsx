// PlayerContext.js
import React, { createContext, useContext, useState, useEffect } from 'react';

const PlayerContext = createContext();

export const usePlayer = () => useContext(PlayerContext);

export const PlayerProvider = ({ children }) => {
  const [currentTrack, setCurrentTrack] = useState(null);

  const [audio] = useState(new Audio());
  const [queue, setQueue] = useState([]);
  const [isPlaying, setIsPlaying] = useState(false);
  const [currentTime, setCurrentTime] = useState(0);
  const [duration, setDuration] = useState(0);
  const [paused, setPaused] = useState(false);

  useEffect(() => {
    if (currentTrack) {
      audio.src = currentTrack.audioUrl;
      audio.play();
      setIsPlaying(true);

      // Configurar duración de la canción actual
      audio.onloadedmetadata = () => {
        setDuration(audio.duration);
      };

      // Actualizar tiempo actual
      audio.ontimeupdate = () => {
        setCurrentTime(audio.currentTime);
      };

      // Llamar a playNextTrack cuando la canción termina
      audio.onended = () => {
        playNextTrack();
      };
    }

    return () => {
      // Limpiar eventos del audio cuando cambia el currentTrack
      audio.onended = null;
      audio.ontimeupdate = null;
      audio.onloadedmetadata = null;
    };
  }, [currentTrack]);

  const playTrack = (track) => {
    setCurrentTrack(track);
    setCurrentTrackImg(track.songImg);
    console.log(track.songImg);
  };

  const playNextTrack = () => {
    if (queue.length > 0) {
      setCurrentTrack(queue[0]); // Configurar la siguiente canción en la cola
      setQueue(queue.slice(1)); // Eliminar la canción que se va a reproducir de la cola
    } else {
      setIsPlaying(false); // No hay más canciones, detener la reproducción
    }
  };

  const addToQueue = (track) => {
    setQueue((prevQueue) => [...prevQueue, track]);
  };

  const togglePlayPause = () => {
    if (isPlaying) {
      audio.pause();
      setPaused(true);
      setIsPlaying(false);
    } else {
      audio.play();
      setPaused(false);
    }
    setIsPlaying(!isPlaying);
  };

  const skipForward = (seconds) => {
    audio.currentTime = Math.min(audio.currentTime + seconds, audio.duration);
  };

  const skipBackward = (seconds) => {
    audio.currentTime = Math.max(audio.currentTime - seconds, 0);
  };

  const stopAudio = () => {
    audio.pause();  // Detener la reproducción
    audio.currentTime = 0;  // Resetear el tiempo de reproducción a 0
    setPaused(false);
    setIsPlaying(false);  // Actualizar el estado de isPlaying
    setCurrentTrack(null);  // Limpiar la canción actual
    setQueue([]);  // Vaciar la cola de canciones (si es necesario)
    setCurrentTrackImg(null);  // Limpiar la imagen de la canción
    audio.src = null;  // Asegurarse de que no haya ninguna fuente de audio
  };

  return (
    <PlayerContext.Provider
      value={{
        currentTrack,
        isPlaying,
        currentTime,
        duration,
        queue,
        playTrack,
        togglePlayPause,
        addToQueue,
        skipForward,
        skipBackward,
        paused,
        stopAudio,

      }}
    >
      {children}
    </PlayerContext.Provider>
  );
};

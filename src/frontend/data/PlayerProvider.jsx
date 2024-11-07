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
      console.log(currentTrack)
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
        console.log('Canción terminada');
        audio.pause();  // Detener la reproducción
        audio.currentTime = 0;  // Resetear el tiempo de reproducción a 0
        setPaused(false);
        setIsPlaying(false);  // Actualizar el estado de isPlaying
        setCurrentTrack(null);  // Limpiar la canción actual
        audio.src = null;  // Asegurarse de que no haya ninguna fuente de audio
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

  // Este useEffect se ejecutará cuando la cola cambie
  useEffect(() => {
    if (queue.length > 0 && !currentTrack && !isPlaying) {
      // Si no hay track actual y la cola tiene canciones, reproducir la primera
      playNextTrack();
    }
  }, [queue, currentTrack]); // Depende de queue y currentTrack

  const playTrack = (track) => {
    setCurrentTrack(track);
  };

  const playNextTrack = () => {
    if (queue.length > 0) {
      console.log('Valide el if')
      const nextTrack = queue[0];
      console.log(nextTrack);
      setCurrentTrack(nextTrack);  // Esto actualizará currentTrack y disparará el efecto
      setQueue(queue.slice(1));  // Eliminar la canción que se va a reproducir de la cola
    } else {
      setIsPlaying(false); // No hay más canciones, detener la reproducción
    }
  };

  const playNextTrackQueue = () => {
    if (queue.length > 0) {
      console.log('Valide el if')
      const nextTrack = queue[0];
      console.log(nextTrack);
      setCurrentTrack(nextTrack);  // Esto actualizará currentTrack y disparará el efecto
      setQueue(queue.slice(1));  // Eliminar la canción que se va a reproducir de la cola
    } 
  };

  const addToQueue = (track) => {
    setQueue((prevQueue) => {
      const newQueue = [...prevQueue, track];

      // Si no hay track actual y la cola está vacía, reproducir la canción inmediatamente
      if (!currentTrack && newQueue.length === 1) {
        playNextTrack();  // Esto comenzará a reproducir la primera canción
      }

      console.log(newQueue);
      return newQueue;
    });
  };

  const togglePlayPause = () => {
    if (isPlaying) {
      audio.pause();
      setPaused(true);
      setIsPlaying(false);
    } else {
      audio.play();
      setPaused(false);
      setIsPlaying(true);
    }
  };

  const skipForward = (seconds) => {
    audio.currentTime = Math.min(audio.currentTime + seconds, audio.duration);
  };

  const skipBackward = (seconds) => {
    audio.currentTime = Math.max(audio.currentTime - seconds, 0);
  };

  const restartTrack = () => {
    audio.currentTime = 0;
  };

  const stopAudio = () => {
    audio.pause();  // Detener la reproducción
    audio.currentTime = 0;  // Resetear el tiempo de reproducción a 0
    setPaused(false);
    setIsPlaying(false);  // Actualizar el estado de isPlaying
    setCurrentTrack(null);  // Limpiar la canción actual
    setQueue([]);  // Vaciar la cola de canciones (si es necesario)
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
        playNextTrackQueue,
        togglePlayPause,
        addToQueue,
        skipForward,
        skipBackward,
        restartTrack,
        paused,
        stopAudio,
      }}
    >
      {children}
    </PlayerContext.Provider>
  );
};

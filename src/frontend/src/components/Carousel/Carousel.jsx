import React, { useEffect, useState } from 'react';
import useEmblaCarousel from 'embla-carousel-react';
import Autoplay from 'embla-carousel-autoplay';
import { FaArrowCircleLeft, FaArrowCircleRight } from "react-icons/fa";
import Album from './Album';
import Spinner from '../spinner/Spinner';

export function Carousel() {
  const [albums, setAlbums] = useState([]);
  const [loading, setLoading] = useState(true); // Estado de carga

  const [emblaRef, emblaApi] = useEmblaCarousel({ loop: true }, [Autoplay()]);

  const scrollPrev = () => {
    if (emblaApi) emblaApi.scrollPrev();
  };

  const scrollNext = () => {
    if (emblaApi) emblaApi.scrollNext();
  };

  useEffect(() => {
    bringAlbums();
  }, []);

  const bringAlbums = async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await fetch("http://localhost:8080/album", {
        method: "GET",
        headers: {
          'Authorization': `Bearer ${token}`,
          "Content-Type": "application/json",
        },
      });
      const data = await response.json();

      if (response.ok) {
        setAlbums(data);
        setLoading(false); // Desactiva la carga una vez que los álbumes están disponibles
      } else {
        console.error(data);
        setLoading(false); // Asegura que el indicador de carga desaparezca en caso de error
      }
    } catch (error) {
      console.error(error);
      setLoading(false); // Desactiva la carga si hay un error en la solicitud
    }
  };

  // Lógica para duplicar los álbumes hasta que haya suficientes
  const extendedAlbums = albums.length > 0 ? [...albums, ...albums, ...albums] : [];

  return (
    <div className="relative">
      {/* Indicador de carga */}
      {loading ? (
        <Spinner />
      ) : null}

      <div className="embla overflow-hidden" ref={emblaRef}>
        <div className="embla__container flex">
          {extendedAlbums.length > 0 ? (
            extendedAlbums.map((album, index) => (
              <div key={index} className="embla__slide flex-none p-2 w-[70%] sm:w-[68%] md:w-[40%] lg:w-[34%] xl:w-[23%] cursor-pointer">
                <Album key={index} nombre={album.nombre} imagenUrl={album.imagenUrl} />
              </div>
            ))
          ) : (
            <h1>Loading...</h1>
          )}
        </div>
      </div>

      <button
        onClick={scrollPrev}
        className="absolute top-1/2 left-4 transform -translate-y-1/2 text-white p-2 rounded-full shadow-lg"
      >
        <FaArrowCircleLeft className="w-6 h-6" />
      </button>

      <button
        onClick={scrollNext}
        className="absolute top-1/2 right-4 transform -translate-y-1/2 text-white p-2 rounded-full shadow-lg"
      >
        <FaArrowCircleRight className="w-6 h-6" />
      </button>
    </div>
  );
}

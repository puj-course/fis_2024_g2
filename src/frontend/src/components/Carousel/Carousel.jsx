import React from 'react';
import useEmblaCarousel from 'embla-carousel-react';
import Autoplay from 'embla-carousel-autoplay'
import { FaArrowCircleLeft, FaArrowCircleRight } from "react-icons/fa"; // 
import Album from './Album'

export function Carousel() {
  const [emblaRef, emblaApi] = useEmblaCarousel({ loop: true }, [Autoplay()]);

  const scrollPrev = () => {
    if (emblaApi) emblaApi.scrollPrev();
  };

  const scrollNext = () => {
    if (emblaApi) emblaApi.scrollNext();
  };

  return (
    <div className="relative">
      <div className="embla overflow-hidden" ref={emblaRef}>
        <div className="embla__container flex">
          {Array.from({ length: 12 }).map((_, index) => (
            <div key={index} className="embla__slide flex-none p-2 w-[70%] sm:w-[68%] md:w-[40%] lg:w-[34%] xl:w-[23%] ">
              <Album />
             </div>
          ))}
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

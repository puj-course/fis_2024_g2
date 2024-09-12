//Aux image
import concert from "../../assets/concert.jpg";

//Components
import SongList from "../../components/songList/SongList";
import { Carousel } from "../../components/Carousel/Carousel";

const HomePage = () => {
    return (
        <div className="p-4 min-h-screen">
            {/* Musify image */}
            <div className="bg-cover bg-center h-96 w-full flex flex-col p-6 pt-8 rounded mb-5 relative">
                <img src={concert} alt="Musify image" className="absolute top-0 left-0 w-full h-full object-cover"/>
                <h3 className="text-white text-4xl font-bold font-mono z-40">
                    Musify
                </h3>
                <span className="text-gray-400 z-40">
                    The place where the music comes to live
                </span>
            </div>

            <h3 className="text-xl font-semibold mb-2">
                    Explore trending albums
            </h3>
            <Carousel/>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-8 mt-4">
                
                {/* Popular album */}
                <div className="flex flex-col gap-6">
                    <h3 className="text-xl font-semibold">Popular album 🔥</h3>
                    <img src={concert} alt="Top album" className="rounded-xl h-4/5 object-cover m-2 max-h-[367.5px]" />
                </div>

                {/* Top songs */}
                <SongList />
            </div>
        </div>
    );
};

export default HomePage;

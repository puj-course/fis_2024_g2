import Song from "../../components/song/Song";

// Reemplazo por una API call
const songs = [
    {
        id: 1,
        name: "Mi nuevo vicio",
        author: "Morat",
        duration: "3:54"
    },
    {
        id: 2,
        name: "Dance with me",
        author: "Orleans",
        duration: "3:54"
    },
    {
        id: 3,
        name: "The nights",
        author: "Avicii",
        duration: "3:54"
    },
    {
        id: 4,
        name: "Yellow",
        author: "Coldplay",
        duration: "3:54"
    },
    {
        id: 5,
        name: "The scientist",
        author: "Coldplay",
        duration: "3:54"
    }
]

const TopSongs = () => {    
    return (
        <div className="flex flex-col gap-6">
            <h3 className="text-xl font-semibold">Top Songs ⬆️</h3>

            <div className="flex flex-col gap-4 p-1">
                
                {
                    songs.map((song) => (
                        <Song key={song.id} name={song.name} author={song.author} duration={song.duration} />
                    ))
                }

            </div>
        </div>
    );
};

export default TopSongs;
import Song from "../song/Song";

const SongList = ({title, songs, howMuch}) => {        
    return (
        <div className="flex flex-col gap-6">
            <h3 className="text-xl font-semibold">{ title }</h3>

            <div className="flex flex-col gap-4 p-1">
                
                {
                    songs?.slice(0, howMuch).map((song, index) => (
                        <Song key={index} img={song.imagenUrl} name={song.nombre} author={song.author} authorId={song.artistId} duration={song.duracion} url={song.audioUrl}/>
                    ))
                }

            </div>
        </div>
    );
};

export default SongList;
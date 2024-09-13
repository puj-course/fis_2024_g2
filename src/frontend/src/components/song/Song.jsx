import concert from '../../assets/concert.jpg';
import PlayButton from './PlayButton';
import LikeButton from './LikeButton';
import { Link } from 'react-router-dom';

const Song = ({id, img=concert ,name: title="Song name", author="Song author", authorId=0, duration="00:00"}) => {
    return (
        <div className="px-6 py-2 flex bg-gray-100 hover:bg-gray-300 dark:bg-zinc-900 hover:dark:bg-zinc-800 h-[3.7rem] rounded-lg gap-5 items-center select-none overflow-hidden">
            <img
                src={img}
                alt="Song cover"
                className="rounded w-8 h-8 object-cover"
            />
            <div className="flex-1 flex flex-col">
                <h4 className="text-base font-normal">{title}</h4>
                <Link to={`/artist/${authorId}`} className='text-gray-700 dark:text-gray-400 hover:underline '>{ author }</Link>
                {/* <span className='text-sm text-gray-400'>{author}</span> */}
            </div>
            <span>{duration}</span>
            <PlayButton />
            <LikeButton />
        </div>
    );
}

export default Song;
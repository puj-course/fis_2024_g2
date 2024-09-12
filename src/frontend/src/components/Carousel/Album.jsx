import img from '../../assets/img.jpg';

const Album = () => {
    return (
        <div className='w-56 flex flex-col items-center'>
            <img src={img} alt="img" className='w-full object-cover rounded'/>
            <span>Album name</span>
        </div>
    );
};

export default Album;
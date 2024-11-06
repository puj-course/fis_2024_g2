import { useNavigate } from 'react-router-dom';
import img from '../../assets/img.jpg';

const Album = ({nombre, imagenUrl}) => {
    const navigate = useNavigate();
    
    const handleNavigation = (path) => {
        navigate(path);
    }
    
    return (
        <div onClick={() => handleNavigation(`/album/${nombre}`)} className='w-56 flex flex-col items-center'>
            <img src={imagenUrl} alt="img" className='w-full object-cover rounded'/>
            <span>{nombre}</span>
        </div>
    );
};

export default Album;
import { useNavigate } from "react-router-dom";


const Album = ({nombre, fechaLanzamiento, imagenUrl}) => {
    const navigate = useNavigate();
    
    const handleNavigation = (path) => {
        navigate(path);
    }
    
    return (
        <div onClick={() => handleNavigation(`/album/${nombre}`)} className="bg-gray-200 dark:bg-darkNavBar p-4 flex flex-col items-center h-72 rounded-md cursor-pointer hover:dark:bg-zinc-800 transition-colors">
            <img src={imagenUrl} alt="imagen del album" className="rounded-md w-full h-5/6 object-cover"/>
            <h3 className="text-xl text-zinc-900 dark:text-white">{nombre}</h3>
            <p className="text-slate-700 dark:text-gray-400">{fechaLanzamiento}</p>
        </div>
    );
}

export default Album;
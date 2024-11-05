const Album = ({nombre, fechaLanzamiento, imagenUrl}) => {
    return (
        <div className="bg-gray-200 dark:bg-darkNavBar p-4 flex flex-col items-center h-72 rounded-md">
            <img src={imagenUrl} alt="imagen del album" className="rounded-md w-full h-3/4 object-cover"/>
            <h3 className="text-xl text-zinc-900 dark:text-white">{nombre}</h3>
            <p className="text-slate-700 dark:text-gray-400">{fechaLanzamiento}</p>
        </div>
    );
}

export default Album;
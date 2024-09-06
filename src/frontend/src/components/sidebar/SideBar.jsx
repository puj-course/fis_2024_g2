//Dependencias
import { createContext, useContext } from "react";
import { useNavigate } from "react-router-dom";

//Logo de musify
import musifyLogo from "../../assets/musify.png";

//Iconos
import {
    BsArrowBarLeft,
    BsPersonCircle,
    BsArrowBarRight,
} from "react-icons/bs";

const SideBarContext = createContext();

const SideBar = ({ children, expanded, setExpanded }) => {
    return (
        <aside
            className={`
                    h-screen transition-all fixed top-0 left-0 text-[#8151e9]
                    ${expanded ? "w-72" : "w-[72px]"}  
                `}
        >
            <nav className="h-full flex flex-col bg-[#F9FAFB] dark:bg-[#1D1E22] shadow-sm">
                {/* Parte superior del SideBar*/}
                <div className={`p-1 pb-2 flex items-center h-[6rem] ${expanded? "justify-between" : "justify-center"}`}>
                    <div className="flex gap-1s items-center ">
                        <img
                            src={musifyLogo}
                            alt="React"
                            className={`
                                    overflow-hidden transition-all 
                                    ${expanded ? "w-[5rem] h-[5rem]" : "w-0"}
                                `}
                        />
                        <span
                            className={`
                                    text-xl font-bold tracking-wide overflow-hidden transition-all 
                                    ${expanded ? "w-12s" : "w-0"}
                                `}
                        >
                            Musify
                        </span>
                    </div>
                    <button onClick={() => setExpanded((ex) => !ex)}>
                        {expanded ? (
                            <BsArrowBarLeft size={25} className="dark:text-white text-[#151719]" />
                        ) : (
                            <BsArrowBarRight size={25} className="dark:text-white text-[#151719]" />
                        )}
                    </button>
                </div>

                {/* 
                    Items del SideBar, se reciben a traves de las props del componente como "children"
                    Envuelve los items del side bar dentro del provider del context
                    */}
                <SideBarContext.Provider value={{ expanded }}>
                    <ul className="flex-1 px-3">{children}</ul>
                </SideBarContext.Provider>

                {/* Seccion de usuario, la parte de abajo */}
                <div className="flex p-3 h-16 justify-center">
                    <BsPersonCircle size={27} color="6837CE" />
                    <div
                        className={`
                        flex justify-between items-start ml-3 flex-col
                        overflow-hidden transition-all ${
                            expanded ? "w-52" : "w-0"
                        }    
                    `}
                    >
                        <h4 className="dark:text-white">Usuario</h4>
                        <span className="text-xs text-gray-700 dark:text-gray-300">
                            Informacion del usuario
                        </span>
                    </div>
                </div>
            </nav>
        </aside>
    );
};

export const SideBarItem = ({ icon, text, active, alert, path }) => {
    const navigate = useNavigate();

    const handleNavigation = () => {
        navigate(path);
    };

    const { expanded } = useContext(SideBarContext);

    return (
        <li
            onClick={() => {
                handleNavigation(path);
            }}
            className={`
            relative flex items-center py-2 px-3 my-1 font-medium rounded-md cursor-pointer
            transition-colors
                ${
                    active
                        ? "bg-gradient-to-tr from-violet-300 to-violet-200 text-indigo-800 dark:from-zinc-700 dark:to-zinc-700 dark:text-white "
                        : "hover:bg-violet-100 dark:hover:bg-zinc-800 text-[#323334] dark:text-[#CDCDCC]"
                }   
            `}
        >
            {icon}

            <span
                className={`transition-all overflow-hidden ${
                    expanded ? "w-52 ml-3" : "w-0"
                }`}
            >
                {text}
            </span>

            {alert && (
                <div
                    className={`absolute right-2 w-2 h-2 rounded bg-violet-500 ${
                        expanded ? "" : "top-2"
                    }`}
                ></div>
            )}
        </li>
    );
};

export default SideBar;

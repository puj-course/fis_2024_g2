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

//Se crea el contexto de la sidebar, para ver si esta expandida o no
const SideBarContext = createContext();

//Componente SideBar
const SideBar = ({ children, expanded, setExpanded }) => {
    return (
        <aside
            className={`
                    h-screen transition-all fixed top-0 left-0 text-[#8151e9] select-none
                    ${expanded ? "w-72" : "w-[72px]"}  
                `}
        >
            <nav className="h-full flex flex-col bg-[#F9FAFB] dark:bg-darkNavBar shadow-sm">
                {/* Parte superior del SideBar*/}
                <div
                    className={`h-[6rem]  flex items-centerp-1 pb-2 ${
                        expanded ? "justify-between pr-2" : "justify-center"
                    }`}
                >
                    <div className="flex items-center gap-1s">
                        <img
                            src={musifyLogo}
                            alt="React"
                            className={`
                                    overflow-hidden transition-all  
                                    ${expanded ? "w-20 h-20" : "w-0"}
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
                            <BsArrowBarLeft
                                size={25}
                                className="dark:text-white text-[#151719]"
                            />
                        ) : (
                            <BsArrowBarRight
                                size={25}
                                className="dark:text-white text-[#151719]"
                            />
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
                <div className="flex justify-center p-3 h-16 ">
                    <BsPersonCircle size={27} color="6837CE" />
                    <div
                        className={`
                        flex  flex-col justify-between items-start ml-3 
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
                        ? "bg-gradient-to-tr from-violet-300 to-violet-200 text-indigo-800 dark:from-zinc-700 dark:to-zinc-600 dark:text-white "
                        : "hover:bg-violet-100 dark:hover:bg-zinc-800 text-[#323334] dark:text-[#CDCDCC]"
                }   
                ${  expanded 
                        ? "justify-start" 
                        : "justify-center"
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

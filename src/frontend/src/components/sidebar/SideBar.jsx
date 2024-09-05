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
                    h-screen transition-all fixed top-0 left-0 text-[#6837CE]
                    ${expanded ? "w-72" : "w-16"}  
                `}
        >
            <nav className="h-full flex flex-col bg-white border-r shadow-sm">
                {/* Parte superior del SideBar*/}
                <div className="p-4 pb-2 flex justify-between items-center h-16">
                    <div className="flex gap-2 items-center">
                        <img
                            src={musifyLogo}
                            alt="React"
                            className={`
                                    overflow-hidden transition-all 
                                    ${expanded ? "w-10 h-10" : "w-0"}
                                `}
                        />
                        <span
                            className={`
                                    text-xl font-bold tracking-wide overflow-hidden transition-all 
                                    ${expanded ? "w-12s" : "hidden"}
                                `}
                        >
                            Musify
                        </span>
                    </div>
                    <button onClick={() => setExpanded((ex) => !ex)}>
                        {expanded ? (
                            <BsArrowBarLeft size={25} color="6837CE" />
                        ) : (
                            <BsArrowBarRight size={25} color="6837CE" />
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
                <div className="border-t flex p-3 h-16">
                    <BsPersonCircle size={27} color="6837CE" />
                    <div
                        className={`
                        flex justify-between items-start ml-3 flex-col
                        overflow-hidden transition-all ${
                            expanded ? "w-52" : "w-0"
                        }    
                    `}
                    >
                        <h4>Usuario</h4>
                        <span className="text-xs text-gray-700">
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
                        ? "bg-gradient-to-tr from-violet-300 to-violet-200 text-indigo-800"
                        : "hover:bg-violet-100 text-gray-600"
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
                    className={`absolute right-2 w-2 h-2 rounded bg-indigo-400 ${
                        expanded ? "" : "top-2"
                    }`}
                ></div>
            )}
        </li>
    );
};

export default SideBar;

//Dependencias
import { createContext, useContext, useEffect, useState } from "react";
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
                    h-screen transition-all fixed top-0 left-0 text-[#8151e9] select-none z-50
                    ${expanded ? "w-72" : "w-[72px]"}  
                `}
        >
            <nav className="h-full flex flex-col bg-[#e9ecee] dark:bg-darkNavBar shadow-sm">
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
                <SideBarUserInfo expanded={expanded}/>
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

const SideBarUserInfo = ({expanded}) => {
    const [img, setImg] = useState(null);
    const [name, setName] = useState(null);
    const [rol, setRol] = useState(null);

    useEffect(() => {
        bringUserInfo();
    })

    const bringUserInfo = async () => {
        try {
            const response = await fetch(`http://localhost:8080/usuario/${localStorage.getItem('nickname')}`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                },
            });

            const data = await response.json();
            
            if(response.ok) {
                setImg(data.fotoPerfilUrl);
                setName(data.nickname);
                setRol(data.rol);
            } else {
                console.log("Rayos");
            }
        } catch (error) {
            console.log(error);
        }
    } 

    return (
        <div className="flex justify-center p-3 h-16 ">
                    {/* <BsPersonCircle size={27} color="6837CE" /> */}
                    <img src={img} alt="imagen de usuario" className="w-[46px] h-[46px] rounded-full object-cover object-center" />
                    <div
                        className={`
                        flex  flex-col justify-between items-start ml-3 
                        overflow-hidden transition-all ${
                            expanded ? "w-52" : "w-0"
                        }    
                    `}
                    >
                        <h4 className="dark:text-white">{name ? name : 'User'}</h4>
                        <span className="text-xs text-gray-700 dark:text-gray-300">
                            {rol ? rol : 'user info'}
                        </span>
                    </div>
                </div>
    );
}

export default SideBar;

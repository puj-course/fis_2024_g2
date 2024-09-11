import { useState } from 'react'
import { Outlet, useLocation } from 'react-router-dom';

//Componentes propios
import SideBar, { SideBarItem } from '../../components/sidebar/SideBar';

//Iconos de la sidebar
import { BsHouseDoor } from 'react-icons/bs';
import { RiAlbumLine } from "react-icons/ri";
import { TiMicrophoneOutline } from "react-icons/ti";

const MainPage = () => {
    const [expanded, setExpanded] = useState(true); 
    const location = useLocation();

    return (
        <div className="main-page-container">
            <SideBar expanded={expanded} setExpanded={setExpanded}>
                <SideBarItem
                    text="Home"
                    active={location.pathname === "/home" || location.pathname === "/"}
                    path="/home"
                    icon={<BsHouseDoor size={20} />}
                ></SideBarItem>
                <SideBarItem
                    text="Albums"
                    active={location.pathname === "/albums"}
                    path="/albums"
                    icon={<RiAlbumLine size={20} />}
                ></SideBarItem>
                <SideBarItem
                    text="Artist"
                    active={location.pathname === "/artist"}
                    path="/artist"
                    alert={true}
                    icon={<TiMicrophoneOutline size={20} />}
                ></SideBarItem>
            </SideBar>

            <div
                className={`transition-all dark:text-white bg-white min-h-screen dark:bg-[#131416] ${
                    expanded ? "ml-72" : "ml-[72px]"
                }`}
            >
                <Outlet />
            </div>
        </div>
    );
};

export default MainPage;
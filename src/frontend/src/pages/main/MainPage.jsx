import { useEffect, useState } from 'react'
import { Outlet, useLocation, useNavigate } from 'react-router-dom';

//Componentes propios
import SideBar, { SideBarItem } from '../../components/sidebar/SideBar';
import Header from '../../components/header/Header';

//Iconos de la sidebar
import { BsHouseDoor } from 'react-icons/bs';
import { RiAlbumLine } from "react-icons/ri";
import { TiMicrophoneOutline } from "react-icons/ti";

const MainPage = () => {
    const [expanded, setExpanded] = useState(true); 
    const location = useLocation();
    const token = localStorage.getItem('token');
    const navigate = useNavigate();

    useEffect(() => {
        if(token == null) {
            navigate('/login');
        }

    }, []);

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
                    text="Artists"
                    active={location.pathname === "/artists"}
                    path="/artists"
                    alert={true}
                    icon={<TiMicrophoneOutline size={20} />}
                ></SideBarItem>
            </SideBar>

            <div
                className={`transition-all dark:text-white bg-white min-h-screen dark:bg-darkCustom ${
                    expanded ? "ml-72" : "ml-[72px]"
                }`}
            >
                <Header />
                <Outlet />
            </div>
        </div>
    );
};

export default MainPage;
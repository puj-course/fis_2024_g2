import { useEffect, useState } from "react";
import { Outlet, useLocation, useNavigate } from "react-router-dom";

//Componentes propios
import SideBar, { SideBarItem } from "../../components/sidebar/SideBar";
import Header from "../../components/header/Header";
import PlayerComponent from "../../components/player/PlayerComponent";

//Iconos de la sidebar
import { BsHouseDoor } from "react-icons/bs";
import { RiAlbumLine } from "react-icons/ri";
import { TiMicrophoneOutline } from "react-icons/ti";
import Spinner from "../../components/spinner/Spinner";

const MainPage = () => {
    const [expanded, setExpanded] = useState(true);
    const [loading, setLoading] = useState(true);
    const location = useLocation();
    const token = localStorage.getItem("token");
    const navigate = useNavigate();

    // useEffect(() => {
    //     if (token == null) {
    //         navigate("/login");
    //     }
    // }, []);

    useEffect(() => {
        // Simulamos un tiempo de espera o comprobamos el token
        const checkAuth = async () => {
            if (!token) {
                navigate("/login");
            }
            setLoading(false); // Cuando termine la verificación, cambiamos el estado de loading
        };
        checkAuth();
    }, [token, navigate]);

    if (loading) {
        return <Spinner />; // Asegúrate de tener un componente de carga
    }


    return (
        <div className="main-page-container">
            <SideBar expanded={expanded} setExpanded={setExpanded}>
                <SideBarItem
                    text="Home"
                    active={
                        location.pathname === "/home" ||
                        location.pathname === "/"
                    }
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
                className={`transition-all dark:text-white bg-white min-h-screen dark:bg-darkCustom pb-24 ${
                    expanded ? "ml-72" : "ml-[72px]"
                }`}
            >
                <Header />
                <Outlet />
            </div>

            <PlayerComponent expanded={expanded}/>
        </div>
    );
};

export default MainPage;

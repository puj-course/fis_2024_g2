import { CiLogout } from "react-icons/ci";
import { useNavigate } from "react-router-dom";
import { usePlayer } from "../../../data/PlayerProvider";

const LogOut = () => {

    const { isPlaying, togglePlayPause } = usePlayer();

    const navigate = useNavigate();

    const logOut = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('nickname');
        if(isPlaying) {
            togglePlayPause();
        }
        navigate('/login');
    }

    return <CiLogout onClick={logOut} className="cursor-pointer text-xl"/>
}

export default LogOut;
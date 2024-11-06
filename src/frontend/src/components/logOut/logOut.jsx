import { CiLogout } from "react-icons/ci";
import { useNavigate } from "react-router-dom";
import { usePlayer } from "../../../data/PlayerProvider";

const LogOut = () => {

    const { isPlaying, stopAudio, setIsPlaying } = usePlayer();

    const navigate = useNavigate();

    const logOut = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('nickname');
        stopAudio();
        navigate('/login');
        window.location.reload();
    }

    return <CiLogout onClick={logOut} className="cursor-pointer text-xl"/>
}

export default LogOut;
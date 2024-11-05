import { CiLogout } from "react-icons/ci";
import { useNavigate } from "react-router-dom";

const LogOut = () => {

    const navigate = useNavigate();

    const logOut = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('nickname');
        navigate('/login');
    }

    return <CiLogout onClick={logOut} className="cursor-pointer text-xl"/>
}

export default LogOut;
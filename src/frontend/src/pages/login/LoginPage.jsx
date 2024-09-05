import { BsPerson, BsLock } from 'react-icons/bs';
import './LoginPage.css'
import musify from '../../assets/musify.png'
import { useNavigate } from 'react-router-dom';

const LoginPage = ({isLogged, logged}) => {
    
    const navigate = useNavigate();
    
    const handleLogin = () => {
        navigate('/');
    }
    
    return (
    <div className='container min-w-full'>
        <div className="wrapper">
            <form>
                <h1>LOGIN</h1>
                <div className="logo-box">
                    <img src={musify} alt="Logotipo" />
                </div>

                <div className="input-box">
                    <input type="text" placeholder="Username" required />
                    <i>
                        <BsPerson />
                    </i>
                </div>
                <div className="input-box">
                    <input type="password" placeholder="Password" required />
                    <i>
                        <BsLock />
                    </i>
                </div>

                <div className="remember-forgot">
                    <label>
                        <input type="checkbox" /> Remember Me
                    </label>
                    <a href="#">Forgot password?</a>
                </div>

                <button type="button" className="btn-submit" onClick={handleLogin}>
                    Login
                </button>

                <div className="register-link">
                    <p>
                        Don't have an account? <a href="#">Register</a>
                    </p>
                </div>
            </form>
        </div>
    </div>
    );
};

export default LoginPage;
import { BsPerson, BsLock } from 'react-icons/bs';
import './LoginPage.css'
import musify from '../../assets/musify.png'
import { Link, useNavigate } from 'react-router-dom';
import { useState } from 'react';

const LoginPage = ({setAuth}) => {
    
    const [nickname, setNickname] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async () => {
        // Implement your authentication logic here
        // For now, we'll just set the user as authenticated
        try {
            const response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: {
                    'ContentType': 'application/json'
                },
                body: JSON.stringify(
                    {
                        nickname: nickname,
                        password: password
                    }
                )
            })

            const data = await response.json();

            if(response.ok) {
                localStorage.setItem('token', data.token);
                localStorage.setItem('nickname', nickname);
                navigate('/');
            } else {
                alert("Error al iniciar sesión");
            }

        } catch(error) {
            alert("Credenciales inválidas");
        }

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
                    <input type="text" placeholder="Username" onChange={(e) => setNickname(e.target.value)} required />
                    <i>
                        <BsPerson />
                    </i>
                </div>
                <div className="input-box">
                    <input type="password" placeholder="Password" onChange={(e) => setPassword(e.target.value)} required />
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
                        Don't have an account?<Link to={"/register"}> Register</Link>
                    </p>
                </div>
            </form>
        </div>
    </div>
    );
};

export default LoginPage;
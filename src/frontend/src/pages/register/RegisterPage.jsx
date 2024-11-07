import React, { useState } from "react";
import musify from "../../assets/musify.png";
import { useNavigate } from "react-router-dom";

const RegisterPage = () => {
    
    const navigate = useNavigate();

    const handleNavigation = () => {
        navigate("/login");
    };
    
    const [formData, setFormData] = useState({
        nombres: "",
        apellidos: "",
        nickname: "",
        contra: "",
        numeroTelefonico: "",
        fechaNacimiento: "",
        paisIdPais: 1, // Puede ser un valor predeterminado o necesario según tu API
        idiomaIdIdioma: 1, // Igual que el anterior
        rol: "gratuito"
    });

    const [error, setError] = useState(null); 
    const [loading, setLoading] = useState(false); 
    const [success, setSuccess] = useState(false); 

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        setError(null);
        setLoading(true);

        // Validación de campos vacíos
        if (!formData.nombres || !formData.apellidos || !formData.nickname || 
            !formData.contra || !formData.numeroTelefonico || !formData.fechaNacimiento || 
            !formData.rol) {
                setError("Por favor, completa todos los campos.");
                setLoading(false);
            return; // Si algún campo está vacío, no enviamos el formulario
        }

        try {
            const response = await fetch("http://localhost:8080/usuario/crearUsuario", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    nombres: formData.nombres,
                    apellidos: formData.apellidos,
                    nickname: formData.nickname,
                    contra: formData.contra,
                    numeroTelefonico: formData.numeroTelefonico,
                    fechaNacimiento: formData.fechaNacimiento,
                    paisIdPais: formData.paisIdPais,
                    idiomaIdIdioma: formData.idiomaIdIdioma,
                    rol: formData.rol,
                }),
            });

            if (!response.ok) {
                throw new Error("Hubo un error al registrar el usuario");
            }

            const result = await response.json();
            setSuccess(true);
            // Aquí puedes hacer algo con la respuesta, como redirigir a otra página

        } catch (err) {
            setError(err.message); // Mostrar error si algo falla
        } finally {
            setLoading(false);
        }
    };
    
    return (
        <div className="h-screen w-screen flex">
            {/* Sección de fondo de la izquierda */}
            <div className="bg-login flex-1 bg-center bg-cover">
                <div className="w-full p-4 flex items-center justify-start">
                    <img src={musify} alt="Musify" className="w-16" />
                    <span className="text-white font-bold text-xl tracking-wider">
                        Musify
                    </span>
                </div>
            </div>

            {/* Sección de formulario de la derecha */}
            <div className="bg-[#131416] flex-1 flex flex-col">
                <div className="w-full p-8 flex justify-end">
                    <span className="text-white font-semibold">Register</span>
                </div>
                <div className="flex-1 flex justify-center items-center">
                    <div className="text-white text-center">
                        <h1 className="font-bold text-3xl mb-3">
                            Create an account
                        </h1>
                        <p className="text-gray-400 mb-6">
                            Enter your details below to create an account
                        </p>
                        <form className="flex flex-col gap-5" onSubmit={handleSubmit}>
                            {/* Campo de Nombre */}
                            <input
                                type="text"
                                name="nombres"
                                value={formData.nombres}
                                onChange={handleChange}
                                placeholder="Nombre"
                                className="bg-transparent outline-none border-none px-1 outline-[.5px] outline-gray-600 focus:outline-white rounded"
                            />

                            {/* Campo de Apellido */}
                            <input
                                type="text"
                                name="apellidos"
                                value={formData.apellidos}
                                onChange={handleChange}
                                placeholder="Apellido"
                                className="bg-transparent outline-none border-none px-1 outline-[.5px] outline-gray-600 focus:outline-white rounded"
                            />

                            {/* Campo de Nickname */}
                            <input
                                type="text"
                                name="nickname"
                                value={formData.nickname}
                                onChange={handleChange}
                                placeholder="Nickname"
                                className="bg-transparent outline-none border-none px-1 outline-[.5px] outline-gray-600 focus:outline-white rounded"
                            />

                            {/* Campo de Contraseña */}
                            <input
                                type="password"
                                name="contra"
                                value={formData.contra}
                                onChange={handleChange}
                                placeholder="Contraseña"
                                className="bg-transparent outline-none border-none px-1 outline-[.5px] outline-gray-600 focus:outline-white rounded"
                            />

                            {/* Campo de Número de Teléfono */}
                            <input
                                type="tel"
                                name="numeroTelefonico"
                                value={formData.numeroTelefonico}
                                onChange={handleChange}
                                placeholder="Número de teléfono"
                                className="bg-transparent outline-none border-none px-1 outline-[.5px] outline-gray-600 focus:outline-white rounded"
                            />

                            {/* Campo de Fecha de Nacimiento */}
                            <input
                                type="date"
                                name="fechaNacimiento"
                                value={formData.fechaNacimiento}
                                onChange={handleChange}
                                className="bg-transparent outline-none border-none px-1 outline-[.5px] outline-gray-600 focus:outline-white rounded"
                            />

                            {/* Campo de Selección de Tipo de Usuario */}
                            <select
                                name="rol"
                                value={formData.rol}
                                onChange={handleChange}
                                className="bg-transparent outline-none border-none px-1 outline-[.5px] outline-gray-600 focus:outline-white rounded text-white"
                            >
                                <option value="gratuito" className="text-black">
                                    Gratis
                                </option>
                                <option value="premium" className="text-black">
                                    Premium
                                </option>
                            </select>

                            {/* Mensajes de error o éxito */}
                            {error && <p className="text-red-500">{error}</p>}
                            {success && <p className="text-green-500">¡Registro exitoso!</p>}

                            {/* Botón de Inicio de Sesión */}
                            <button
                                type="button"
                                className="bg-white text-black hover:bg-gray-300 rounded-lg"
                                onClick={handleNavigation}
                            >
                                Login
                            </button>
                                
                            {/* Botón de Registro */}
                            <button type="submit" className="bg-white text-black hover:bg-gray-300 rounded-lg py-2 mt-4" disabled={loading}>
                                {loading ? "Cargando..." : "Register"}
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default RegisterPage;

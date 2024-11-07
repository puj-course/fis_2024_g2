// import { useEffect, useState } from "react";
// import { useParams } from "react-router-dom";

// const ProfilePage = () => {
//     let { nickname } = useParams();
//     const [user, setUser] = useState();

//     useEffect(() => {
//         bringUserInfo();
//     }, []);

//     const bringUserInfo = async () => {
//         try {
//             const token = localStorage.getItem("token");
//             const response = await fetch(
//                 `http://localhost:8080/usuario/${nickname}`,
//                 {
//                     method: "GET",
//                     headers: {
//                         Authorization: `Bearer ${token}`,
//                         "Content-Type": "application/json",
//                     },
//                 }
//             );
//             const data = await response.json();

//             if (response.ok) {
//                 console.log(data);
//                 setUser(data);
//             } else {
//                 console.log("upsi");
//             }
//         } catch (error) {
//             console.error(error);
//         }
//     };

//     return (
//         <div className="p-8 text-2xl font-semibold">
//             <h1>Profile Page</h1>
//             {user ? (
//                 <>
//                     <div className="w-full h-96 flex items-center justify-center flex-col gap-6">
//                         <img
//                             src={user?.fotoPerfilUrl}
//                             alt="Foto del usuario"
//                             className="rounded-full w-64 h-64 object-cover object-center"
//                         />
//                         <h3>{user?.nickname}</h3>
//                     </div>
//                     <div className="p-8">
//                         <h2 className="font-medium">Your info</h2>
//                         <ul className="font-light text-lg pt-4">
//                             <li>Nombre: {user?.nombres}</li>
//                             <li>Apellido: {user?.apellidos}</li>
//                             <li>Numero telefónico: {user?.numeroTelefonico}</li>
//                             <li>Fecha de nacimiento: {user?.fechaNacimiento}</li>
//                             <li>Fecha registro: {user?.fechaRegistro}</li>
//                             <li>Estado: {user?.estado}</li>
//                             <li>Pais: {user?.pais.nombre}</li>

//                         </ul>
//                     </div>
//                 </>
//             ) : (
//                 <h4 className="mt-4 font-medium text-sm">User info ...</h4>
//             )}
//         </div>
//     );
// };

// export default ProfilePage;

import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const ProfilePage = () => {
    let { nickname } = useParams();
    const [user, setUser] = useState();

    useEffect(() => {
        bringUserInfo();
    }, []);

    const bringUserInfo = async () => {
        try {
            const token = localStorage.getItem("token");
            const response = await fetch(
                `http://localhost:8080/usuario/${nickname}`,
                {
                    method: "GET",
                    headers: {
                        Authorization: `Bearer ${token}`,
                        "Content-Type": "application/json",
                    },
                }
            );
            const data = await response.json();

            if (response.ok) {
                setUser(data);
                console.log(data);  
            } else {
                console.log("upsi");
            }
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className="p-8 max-w-4xl mx-auto bg-white dark:bg-darkNavBar rounded-lg shadow-lg">
            <h1 className="text-3xl font-bold text-center text-gray-700 dark:text-gray-100 mb-6">Profile Page</h1>
            {user ? (
                <>
                    {/* Image and Nickname */}
                    <div className="flex flex-col items-center gap-6">
                        <img
                            src={user?.fotoPerfilUrl}
                            alt="Foto del usuario"
                            className="rounded-full w-40 h-40 object-cover shadow-xl transition-all duration-300 transform hover:scale-105"
                        />
                        <h3 className="text-2xl font-semibold text-gray-800 dark:text-gray-200">{user?.nickname}</h3>
                    </div>
                    
                    {/* User Info Section */}
                    <div className="mt-8 bg-gray-50 dark:bg-zinc-800 p-6 rounded-lg shadow-sm">
                        <h2 className="text-xl font-medium text-gray-900 dark:text-gray-100 mb-4">Your Info</h2>
                        <ul className="space-y-4 text-lg font-light text-gray-700 dark:text-gray-300">
                            <li className={`flex items-center gap-2 font-medium ${user?.rol == 'premium' ? 'text-purple-600' : ''}`}>
                                <strong className="w-1/3">Tipo de suscripción: </strong>
                                <span>{user?.rol}</span>
                            </li>
                            
                            <li className="flex items-center gap-2">
                                <strong className="w-1/3">Apellido:</strong>
                                <span>{user?.apellidos}</span>
                            </li>
                            <li className="flex items-center gap-2">
                                <strong className="w-1/3">Teléfono:</strong>
                                <span>{user?.numeroTelefonico}</span>
                            </li>
                            <li className="flex items-center gap-2">
                                <strong className="w-1/3">Fecha de nacimiento:</strong>
                                <span>{user?.fechaNacimiento}</span>
                            </li>
                            <li className="flex items-center gap-2">
                                <strong className="w-1/3">Fecha de registro:</strong>
                                <span>{user?.fechaRegistro}</span>
                            </li>
                            <li className="flex items-center gap-2">
                                <strong className="w-1/3">Estado:</strong>
                                <span>{user?.estado}</span>
                            </li>
                            <li className="flex items-center gap-2">
                                <strong className="w-1/3">País:</strong>
                                <span>{user?.pais.nombre}</span>
                            </li>
                        </ul>
                    </div>
                </>
            ) : (
                <h4 className="mt-4 font-medium text-sm text-gray-600">Cargando información...</h4>
            )}
        </div>
    );
};

export default ProfilePage;

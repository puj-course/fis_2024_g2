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
                console.log(data);
                setUser(data);
            } else {
                console.log("upsi");
            }
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className="p-8 text-2xl font-semibold">
            <h1>Profile Page</h1>
            {user ? (
                <>
                    <div className="w-full h-96 flex items-center justify-center flex-col gap-6">
                        <img
                            src={user?.fotoPerfilUrl}
                            alt="Foto del usuario"
                            className="rounded-full w-64 h-64 object-cover object-center"
                        />
                        <h3>{user?.nickname}</h3>
                    </div>
                    <div className="p-8">
                        <h2 className="font-medium">Your info</h2>
                        <ul className="font-light text-lg pt-4">
                            <li>Nombre: {user?.nombres}</li>
                            <li>Apellido: {user?.apellidos}</li>
                            <li>Numero telefónico: {user?.numeroTelefonico}</li>
                            <li>Fecha de nacimiento: {user?.fechaNacimiento}</li>
                            <li>Fecha registro: {user?.fechaRegistro}</li>
                            <li>Estado: {user?.estado}</li>

                        </ul>
                    </div>
                </>
            ) : (
                <h4 className="mt-4 font-medium text-sm">User info ...</h4>
            )}
        </div>
    );
};

export default ProfilePage;

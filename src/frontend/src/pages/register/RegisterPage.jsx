import React from "react";

import musify from "../../assets/musify.png";

const RegisterPage = () => {
    return (
        <div className="h-screen w-screen flex">
            <div className="bg-login flex-1 bg-center bg-cover">
                <div className="w-full p-4 flex items-center justify-start">
                    <img src={musify} alt="Musify" className="w-16" />
                    <span className="text-white font-bold text-xl tracking-wider">Musify</span>
                </div>
            </div>
            <div className="bg-[#131416] flex-1 flex flex-col">
                <div className="w-full p-8 flex justify-end">
                    <span className="text-white font-semibold">Register</span>
                </div>
                <div className="flex-1 flex justify-center items-center">
                    
                    <div className="text-white text-center">
                        <h1 className="font-bold text-3xl mb-3">Create an account</h1>
                        <p className="text-gray-400 mb-6">Enter your email below to create an account</p>
                        <form className="flex flex-col gap-5">
                            <input type="text" placeholder="example@gmail.com" className="bg-transparent outline-none border-none px-1 outline-[.5px] outline-gray-600 focus:outline-white rounded"/>
                            <input type="text" placeholder="********" className="bg-transparent outline-none border-none px-1 outline-[.5px] outline-gray-600 focus:outline-white rounded"/>
                            <button className="bg-white text-black hover:bg-gray-300 rounded-lg py-2">Register</button>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    );
};

export default RegisterPage;

//Hooks de react
import React, { useEffect, useState } from "react";

//Iconos
import { CiSun } from "react-icons/ci";
import { IoMoonOutline } from "react-icons/io5";

const DarkModeToggle = () => {
    const [darkMode, setDarkMode] = useState(false);

    useEffect(() => {
        // Check localStorage for saved theme
        const savedTheme = localStorage.getItem("theme");
        if (savedTheme) {
            const isDarkMode = savedTheme === "dark";
            setDarkMode(isDarkMode);
            document.documentElement.classList.toggle("dark", isDarkMode);
        } else {
            // Default to system preference if no saved theme
            const prefersDarkMode = window.matchMedia(
                "(prefers-color-scheme: dark)"
            ).matches;
            setDarkMode(prefersDarkMode);
            document.documentElement.classList.toggle("dark", prefersDarkMode);
        }
    }, []);

    const handleToggle = () => {
        setDarkMode((prevMode) => {
            const newMode = !prevMode;
            document.documentElement.classList.toggle("dark", newMode);
            localStorage.setItem("theme", newMode ? "dark" : "light");
            return newMode;
        });
    };

    return (
        <div className="w-12 bg-gray-400 dark:bg-gray-800 text-white rounded-full relative cursor-pointer h-6  flex justify-evenly items-center transition-all" onClick={handleToggle}>
            <CiSun />
            <IoMoonOutline/>
            <div
                className={`w-1/2 h-full rounded-full bg-gray-100 dark:bg-gray-700 z-50 absolute top-0 ${darkMode ? "right-0" : "left-0"}`}
            ></div>
        </div>
    );
};

export default DarkModeToggle;

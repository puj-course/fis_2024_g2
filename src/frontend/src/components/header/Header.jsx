//Componentes
import DarkModeToggle from "../darkModeToggle/DarkModeToggle";
import LogOut from "../logOut/logOut";
import { FaSearch } from 'react-icons/fa';

const Header = () => {
    return (
        <header className="p-4 flex justify-between items-center">
            <div className="relative w-1/2">
                <FaSearch className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-500" />
                <input
                    type="text"
                    className="bg-transparent outline-none border-none bg-gray-200 dark:bg-darkNavBar px-10 py-2 w-full rounded-lg text-gray-500 dark:text-gray-300"
                    placeholder="Search artist, albums, songs..."
                />
            </div>
            <div className="flex gap-3 items-center">
                <LogOut />
                <DarkModeToggle />
            </div>
        </header>
    );
};

export default Header;

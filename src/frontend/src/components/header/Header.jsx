//Componentes
import DarkModeToggle from "../darkModeToggle/DarkModeToggle";
import { FaSearch } from 'react-icons/fa';

const Header = () => {
    return (
        <header className="p-4 flex justify-between items-center">
            {/* <input
                type="text"
                className="bg-transparent outline-none border-none bg-gray-100 dark:bg-darkNavBar px-4 py-2 w-1/2 rounded-lg"
                placeholder="Search artist, albums, songs..."
            /> */}
            <div className="relative w-1/2">
                <FaSearch className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-500" />
                <input
                    type="text"
                    className="bg-transparent outline-none border-none bg-gray-100 dark:bg-darkNavBar px-10 py-2 w-full rounded-lg"
                    placeholder="Search artist, albums, songs..."
                />
            </div>  
            <DarkModeToggle />
        </header>
    );
};

export default Header;

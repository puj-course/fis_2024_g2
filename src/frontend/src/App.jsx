//Bibliotecas
import { useState } from 'react'
import { Outlet } from 'react-router-dom';

//Componentes propios
import SideBar, { SideBarItem } from './components/sidebar/SideBar';

//Iconos de la sidebar
import { BsHouseDoor } from 'react-icons/bs';
import { RiAlbumLine } from "react-icons/ri";
import { TiMicrophoneOutline } from "react-icons/ti";

function App() {
  const [expanded, setExpanded] = useState(true); 
    
    return (
    <div className="main-page-container">
      
      <SideBar expanded={expanded} setExpanded={setExpanded}>
        <SideBarItem text="Home" path="/home" icon={<BsHouseDoor size={20}/>}></SideBarItem>
        <SideBarItem text="Albums" path="/albums" icon={<RiAlbumLine size={20}/>}></SideBarItem>
        <SideBarItem text="Artist" path="/artist" icon={<TiMicrophoneOutline size={20}/>}></SideBarItem>
      </SideBar>
      
      <div className={`transition-all ${expanded ? "ml-72" : "ml-16"}`}>
        <Outlet />
      </div>
    </div>
    );
}

export default App

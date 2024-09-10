//Bibliotecas
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";

//Paginas y componentes
import RegisterPage from "./pages/register/RegisterPage";
import LoginPage from './pages/login/LoginPage'
import AlbumPage from './pages/albums/AlbumsPage';
import ArtistPage from './pages/artist/ArtistPage';
import HomePage from './pages/home/HomePage';
import MainPage from './pages/main/MainPage';


//Router
const router = createBrowserRouter([
  {
    path: "/register",
    element: <RegisterPage />
  },
  {
    path: "/login",
    element: <LoginPage />
  },
  {
    path: "/",
    element: <MainPage />,
    children: [
      {
        path: '/',
        element: <HomePage />
      },
      {
        path: '/home',
        element: <HomePage />
      },
      {
        path: '/albums',
        element: <AlbumPage />
      },
      {
        path: '/artist',
        element: <ArtistPage />
      }
    ]
  }
]);

function App() {
  return (
    <RouterProvider router={router} />
  );
}

export default App

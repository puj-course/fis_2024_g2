//Bibliotecas
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";

//Paginas y componentes
import RegisterPage from "./pages/register/RegisterPage";
import LoginPage from './pages/login/LoginPage'
import AlbumPage from './pages/albums/AlbumsPage';
import ArtistsPage from './pages/artists/ArtistPage';
import ArtistPage from './pages/artist/ArtistPage';
import HomePage from './pages/home/HomePage';
import MainPage from './pages/main/MainPage';
import Album from "./pages/album/AlbumPage";
import ProfilePage from "./pages/profile/profilePage";


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
        element: <AlbumPage />,
      },
      {
        path: '/album/:name',
        element: <Album />
      },
      {
        path: '/artists',
        element: <ArtistsPage />,
      },
      {
        path: '/artist/:id',
        element: <ArtistPage />
      },
      {
        path: '/profile/:nickname',
        element: <ProfilePage />
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

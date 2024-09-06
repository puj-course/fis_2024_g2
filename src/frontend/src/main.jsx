import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App';
import LoginPage from './pages/login/LoginPage'
import HomePage from './pages/home/Home';

import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";

import './index.css'
import Albums from './pages/albums/Albums';
import Artist from './pages/artist/Artist';


const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
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
        element: <Albums />
      },
      {
        path: 'artist',
        element: <Artist />
      }
    ]
  },
  {
    path: "/login",
    element: <LoginPage />
  }
]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)

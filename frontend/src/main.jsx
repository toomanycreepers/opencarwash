import React from 'react'
import ReactDOM from 'react-dom/client'
import { createBrowserRouter, RouterProvider, Navigate } from 'react-router-dom'
import './index.css'
import LoginPage from './pages/LoginPage/LoginPage.jsx'
import MainPage from './pages/MainPage/MainPage.jsx'
import BoxPage from './pages/BoxPage/BoxPage.jsx'
import CarWashlist from './components/CarWashList/CarWashlist.jsx'
import ProfilePage from './pages/ProfilePage/ProfilePage.jsx'

const router = createBrowserRouter([
  {
    path: '/',
    element: <MainPage />,
    errorElement: <div>Oops. We'll code it later...</div>,
    children: [
      {
        index: true,
        element: <CarWashlist />,
      },
      {
        path: '/:boxId',
        element: <BoxPage />
      }
    ]
  },
  {
    path: '/profile',
    element: <ProfilePage />,
  },
  {
    path: '/login',
    element: <LoginPage />
  }
])

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)

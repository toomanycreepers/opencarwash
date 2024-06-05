import React from 'react'
import ReactDOM from 'react-dom/client'
import { createBrowserRouter, RouterProvider, Navigate } from 'react-router-dom'
import './index.css'
import LoginPage from './pages/LoginPage/LoginPage.jsx'
import MainPage from './pages/MainPage/MainPage.jsx'
import BoxPage from './pages/BoxPage/BoxPage.jsx'
import CarWashlist from './components/CarWashList/CarWashlist.jsx'
import ProfilePage from './pages/ProfilePage/ProfilePage.jsx'
// import RegPage from './pages/RegPage/RegPage.jsx'
import ProfileBody from './components/ProfileBody/ProfileBody.jsx'
import HistoryPage from './pages/HistoryPage/HistoryPage.jsx'
import BoxSettings from './components/BoxSettings/BoxSettings.jsx'
import CarwashSettings from './components/CarwashSettings/CarwashSettings.jsx'
import ErrorPage from './pages/ErrorPage/ErrorPage.jsx'
import ServiceList from './components/ServiceList/ServiceList.jsx'
import TariffPage from './components/TariffPage/TariffPage.jsx'
import BoxSettingsPage from './pages/BoxSettingsPage/BoxSettingsPage.jsx'

const router = createBrowserRouter([
  {
    path: '/',
    element: <MainPage />,
    errorElement: <ErrorPage/>,
    children: [
      {
        index: true,
        element: <CarWashlist />,
      },
      {
        path: '/:carwashId/:boxId',
        element: <BoxPage />
      },
      {
        path: '/:boxId/history',
        element: <HistoryPage />
      }
    ]
  },
  {
    path: '/profile',
    element: <ProfilePage />,
    children: [
      {
        index: true,
        element: <ProfileBody /> 
        // Затестить
      },
      {
        path: '/profile/settings',
        element: <CarwashSettings />,
      },
    ]
  },
  {
    path: '/profile/settings/:carwashId/services',
    element: <ServiceList />
  },
  {
    path: '/settings/:boxId/options',
    element: <BoxSettingsPage />,
    children: [
      {
        index: true,
        element: <BoxSettings />
      },
      {
        path: 'tariffs',
        element: <TariffPage />
      }
    ]
  },
  {
    path: '/login',
    element: <LoginPage/>
  }
  // {
  //   path: '/registration',
  //   element: <RegPage />
  // }
])

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)

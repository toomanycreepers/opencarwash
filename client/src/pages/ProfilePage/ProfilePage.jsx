import ProfileHeader from '../../components/ProfileHeader/ProfileHeader';
import ProfileBody from '../../components/ProfileBody/ProfileBody';
import cl from './ProfilePage.module.css';
import { Outlet } from 'react-router-dom';
import { ToastContainer, toast } from "react-toastify";
import { Navigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
import AuthStore from '../../store/AuthStore';
import AuthorizationService from '../../API/AuthorizationService';

const ProfilePage = () => {
    const [authChecked, setAuthChecked] = useState(false);
    const [user, setUser] = useState({})

    useEffect(() => {
        const checkAuth = async () => {
            await AuthStore.checkAuth();
            setAuthChecked(true);
        };
        checkAuth();
    }, []);

    useEffect(() => {
        const getUser = async () => {
            try {
                const resp = await AuthorizationService.getUserInfo()
                if (resp.data) {
                    setUser(resp.data)
                }
            } catch {
                toast.error("Ошибка получения пользователя")
            }
        }

        getUser()
    }, [])

    if (!authChecked) {
        return <div>Проверка авторизации...</div>;
    } else {
        return (
            <>
                <ProfileHeader userInfo={user} />
                    <div id="detail">
                        {AuthStore.isAuth ? <Outlet /> : <Navigate to="/login" />}
                    </div>
                <ToastContainer />
            </>
        );
    }
}

export default ProfilePage;

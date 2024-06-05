import { useState, useEffect } from 'react';
import MainHeader from '../../components/MainHeader/MainHeader'
import AuthStore from '../../store/AuthStore';
import { Navigate, Outlet } from 'react-router-dom';
import { observer } from "mobx-react-lite";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const MainPage = observer (()=> {
    const [authChecked, setAuthChecked] = useState(false);

    useEffect(() => {
        const checkAuth = async () => {
            await AuthStore.checkAuth();
            setAuthChecked(true);
        };
        checkAuth();
    }, []);

    if (!authChecked) {
        return <div>Проверка авторизации...</div>;
    }

    return (
        <div>
            <MainHeader />
            <div id="detail">
                {AuthStore.isAuth ? <Outlet /> : <Navigate to="/login" />}
            </div>
            <ToastContainer />
        </div>
    );
})

export default MainPage;

import { Outlet } from "react-router-dom";
import AuthStore from "../../store/AuthStore";
import { useEffect, useState } from "react";

const BoxSettingsPage = () => {
    const [authChecked, setAuthChecked] = useState(false);

    useEffect(() => {
        const checkAuth = async () => {
            await AuthStore.checkAuth();
            setAuthChecked(true);
        };
        checkAuth();
    }, []);

    if (!authChecked) {
        return <div>Проверка авторизации...</div>; // или любой другой загрузочный индикатор
    }

    return (
        <div id="detail">
            {AuthStore.isAuth ? <Outlet /> : <Navigate to="/login" />}
        </div>
    );
}

export default BoxSettingsPage;

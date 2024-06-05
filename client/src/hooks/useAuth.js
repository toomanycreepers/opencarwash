import { useEffect } from "react";
import AuthStore from "../store/AuthStore";

useEffect(setAuthChecked => {
    const checkAuth = async () => {
        await AuthStore.checkAuth();
        setAuthChecked(true);
    };
    checkAuth();
}, []);
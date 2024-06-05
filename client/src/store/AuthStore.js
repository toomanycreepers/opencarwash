import { makeAutoObservable } from "mobx";
import AuthorizationService from "../API/AuthorizationService";


class AuthStore { 
    isAuth = false;
    isAuthInProgress = false;
    
    constructor() {
        makeAutoObservable(this, {}, { autoBind: true });
    }
    

    async login(phoneNumber, password) {
        this.isAuthInProgress = true;
        try {
            const resp = await AuthorizationService.login(phoneNumber, password);
            localStorage.setItem("token", resp.data.accessToken);
            localStorage.setItem("user", JSON.stringify(resp.data.user))
            this.isAuth = true;
        } catch (err) {
            console.error("login error", err);
        } finally {
            this.isAuthInProgress = false;
        } 
    }

    async checkAuth() {
        this.isAuthInProgress = true;
        try {
            const resp = await AuthorizationService.refreshToken();
            localStorage.setItem("token", resp.data.accessToken);
            // На серве:
            if (resp.data.accessToken) {
                this.isAuth = true;
            }
            // На клиенте:
            // this.isAuth = true
        } catch (err) {
            console.log("login error");
        } finally {
            this.isAuthInProgress = false;
        } 
    }

    async logout() {
        this.isAuthInProgress = true;
        try {
            await AuthorizationService.logout();
            this.isAuth = false;
            localStorage.removeItem("token");
        } catch (err) {
            console.log("logout error");
        } finally {
            this.isAuthInProgress = false;
        } 
    }
}

export default new AuthStore();
import { makeAutoObservable } from "mobx";
import AuthorizationService from "../API/AuthorizationService";


class ServiceStore { 
    isAuth = false;
    
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
}

export default new ServiceStore();
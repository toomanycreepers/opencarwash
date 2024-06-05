import AuthorizationService from "../API/AuthorizationService"

const authorizeUser = (login, password) => {
    try {
        const resp = AuthorizationService.login(login, password)
        
    } catch(e) {

    }
}

const checkAuth = (login, password) => {
    if (!login || !password) {
        return 1
    }

    if (password.length < 10) {
        return 2
    }


}
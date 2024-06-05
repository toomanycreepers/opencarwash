import $authApi from '../http'


export default class AuthorizationService {
    static async login(phoneNumber, password) {
        return $authApi.post('/api/auth/signIn', {phoneNumber, password})
    }
    static async logout() {
        return $authApi.post('/api/auth/logout')
    }
    static async refreshToken() {
        return $authApi.get('/api/auth/refresh')
    }
    static async getUserInfo() {
        return $authApi.get('/api/auth/current')
    }
}

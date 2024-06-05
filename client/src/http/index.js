import axios from 'axios'
import AuthStore from '../store/AuthStore'

export const AUTH_API_ULR = ''

const $authApi = axios.create({
    withCredentials: true,
    baseURL: AUTH_API_ULR
}) 

$authApi.interceptors.request.use((config) => {
    config.headers.Authorization = 'Bearer ' + localStorage.getItem('token') || ''
    return config
})

$authApi.interceptors.response.use(
    (config) => {
        return config
    },
    async (error) => {
        const originalRequest = {...error.config}
        originalRequest._isRetry = true
        console.log(error);
        // if (error.response.status === 401) {
        //     return
        // }
        if (error.response.status === 403 && 
                error.config &&
                !error.config._isRetry) {
            try {
                console.log(1);
                const resp = await AuthStore.checkAuth();
                localStorage.setItem("token", resp.data.accessToken);
                return instance.request(originalRequest);
            } catch (err) {
                console.log(error);
                console.log(1);
                console.log("AUTH ERROR");
            }
        }
        throw error
    }
)

export default $authApi
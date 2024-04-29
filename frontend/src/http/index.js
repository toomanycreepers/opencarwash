import axios from 'axios'

export const AUTH_API_ULR = ''

const $authApi = axios.create({
    withCredentials: true,
    baseURL: AUTH_API_ULR
}) 

$authApi.interceptors.request.use((config) => {
    config.headers.Authorization = 'Bearer' + localStorage.getItem('token') || ''
    return config
})

$authApi.interceptors.response.use(
    (config) => {
        return config
    },
    async (error) => {
        const originalRequest = {...error.config}
        originalRequest._isRetry = true
        if (error.response.status === 401 && 
                error.config &&
                !error.config._isRetry) {
            try {
                const resp = await $authApi.get("/api/refresh");
                localStorage.setItem("token", resp.data.accessToken);
                return instance.request(originalRequest);
            } catch (error) {
                console.log("AUTH ERROR");
            }
        }
        throw error
    }
)

export default $authApi
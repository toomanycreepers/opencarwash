import $authApi from '../http'


export default class CarwashService {
    static async getAllCarwashes(employeeId, date) {
        return $authApi.post('/api/box/getByEmployee', {
            employeeId,
            date
        })
    }
    static async getCarwashSettings() {
        return $authApi.get('/api/...')
    }
    static async getServicesById(id) {
        return $authApi.get(`/api/carwashService/cw/${id}`)
    }
    static async updateCarwashAE(carwashId, minutes) {
        return $authApi.put('/api/carwash/timeslot', {
            carwashId,
            minutes
        })
    }
    static async createService(name, price, description, duration, carwashId) {
        console.log({
            name,
            price,
            description,
            duration,
            carwashId,
        });
        return $authApi.post('/api/carwashService', {
            name,
            price,
            description,
            duration,
            carwashId,
        })
    }
    static async deleteService(id) {
        return $authApi.delete(`/api/carwashService/${id}`)
    }
    static async updateService(id, name, price, description, duration, carwashId) {
        return $authApi.put('/api/carwashService', {
            id,
            name,
            price,
            description,
            duration,
            carwashId,
        })
    }
    static async getCarwashById(cwId) {
        return $authApi.get(`/api/carwash/${cwId}`)
    }
}

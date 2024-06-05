import $authApi from '../http'


export default class OrderService {
    static async getTariffById(id) {
        return $authApi.get(`/api/tariff/${id}`)
    }
    static async createTariff(name, description, serviceIds) {
        return $authApi.post('/api/tariff', {
            name,
            description,
            serviceIds,
        })
    }
    static async addTariffToBox(boxId, tariffId) {
        return $authApi.put('/api/box/addTariff', {
            boxId,
            tariffId
        })
    }
    static async updateTariff(id, name, description, serviceIds, commentForEmployees, BufferTime) {
        console.log({id, name, description, serviceIds, commentForEmployees, BufferTime});
        return $authApi.put('/api/tariff', {
            id,
            name,
            description,
            serviceIds,
            commentForEmployees,
            BufferTime,
        })
    }
    static async setBufferTime(tariffId, buffertime) {
        return $authApi.put('/api/tariff/buffertime', {
            tariffId,
            buffertime,
        })
    }
}

import $authApi from '../http'


export default class BoxService {
    static async getById(id) {
        return $authApi.get(`/api/box/id/${id}`)
    }
    static async create(number, workWeekType, carwashId) {
        return $authApi.post('/api/box', {
            number, workWeekType, carwashId
        })
    }
    static async getBoxBH(boxId) {
        return $authApi.get(`/api/bHrs/box/${boxId}`)
    }
    static async getTariffsById(boxId) {
        return $authApi.get(`/api/box/${boxId}/tariffs`)
    }
    static async addTariff(boxId, tariffId) {
        return $authApi.put('/api/box/addTariff', {
            boxId, 
            tariffId
        })
    }
    static async removeTariff(boxId, tariffId) {
        console.log({
            boxId,
            tariffId
        });
        return $authApi.put('/api/box/removeTariff', {
            boxId,
            tariffId
        })
    }
    static async changeBusinessHours(businessHoursId, openingTime, closingTime) {
        return $authApi.put('/api/bHrs/time', {
            businessHoursId,
            openingTime,
            closingTime,
        }) 
    }
    static async openBox(boxId) {
        return $authApi.put(`/api/bHrs/open/${boxId}`)
    }
    static async closeBox(boxId) {
        return $authApi.put(`/api/bHrs/close/${boxId}`)
    }
}

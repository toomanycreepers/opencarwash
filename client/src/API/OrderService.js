import getDateInFormat from '../helper/getCurrentDate'
import $authApi from '../http'


export default class OrderService {
    static async cancelOrder(orderId) {
        return $authApi.put('/api/order/state', {
            orderId,
            state: 1,
        })
    }
    static async closeOrder(orderId) {
        return $authApi.put('/api/order/state', {
            orderId,
            state: 4,
        })
    }
    static async getCurrentOrders(boxId) {
        let date = getDateInFormat()
        return $authApi.post('/api/order/dateBox', {
            date,
            boxId,
        })
    }
    static async changeOrderState(state, orderId) {
        // 1 - отмена, 4 - завершение
        return $authApi.put('/api/order/state', {state, orderId})
    }
    static async getHistory(boxId) {
        console.log(boxId);
        return $authApi.get(`/api/order/history/${boxId}`)
    }
}

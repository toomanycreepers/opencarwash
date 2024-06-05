import CarwashService from "../../API/CarwashService";
import CarWashCard from "../CarWashCard/CarWashCard";
import { useEffect, useState } from "react";
import cl from './CarWashlist.module.css'
import { toast } from "react-toastify";
import { getDateInFormat } from "../../helper/getCurrentDate";
import AuthorizationService from "../../API/AuthorizationService";

const CarWashlist = () => {
    const [carwashes, setCarwashes] = useState([])

    const getAllCarwashes = async () => {
        try {
            const userResp = await AuthorizationService.getUserInfo()
            if (userResp.data.id) {
                const date = getDateInFormat()
                const resp = await CarwashService.getAllCarwashes(userResp.data.id, date)
                setCarwashes(resp.data)
            }
        } catch(e) {
            toast.error('Ошибка при получении данных автомоек. Попробуйте позже.', {position: 'top-right'})
        }
    }

    useEffect(() => {
        getAllCarwashes()
    }, [])

    return (
        <div className={cl.carwashList}>
            {carwashes.length ? <CarWashCard carwashes={carwashes} /> : <div className={cl.emptyArrHandler}>Нет доступных автомоек</div>}
        </div> 
    );
}

export default CarWashlist;

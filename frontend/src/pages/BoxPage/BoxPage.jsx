import { useEffect, useState } from "react";
import OrderCard from "../../components/OrderCard/OrderCard";
import BackButton from "../../components/UI/BackButton/BackButton";
import getCurrentDate from "../../helper/getCurrentDate";
import cl from './BoxPage.module.css'

const BoxPage = () => {
    const [workingDays, setWorkingDays] = useState([
        {
            date: '13.05.24',
            orders: [
                {
                    orderNumber: 321,
                    services: ['Услуга 1', 'Услуга 2', 'Услуга 3', 'Услуга 4'],
                    timeInterval: '12:00 - 13:00'
                },
            ]
        },
    ])

    useEffect(() => {
        // Фетч на получение инфы о рабочих днях
    }, [])

    return (
        <div className={cl.boxDetails}>  
            <BackButton navigateTo='/' />
            <div className={cl.dateBar}>
                <p className={cl.currentDate} >История</p>
            </div>
            <div className={cl.wrapper}>
                {workingDays.map(day => {
                    return (
                        <div className={cl.dayBlock}>
                            <h2 className={cl.date}>{day.date}</h2>
                            {day.orders.map(order => {
                                return (
                                    <OrderCard orderNumber={order.orderNumber} timeInterval={order.timeInterval} />
                                )
                            })}
                        </div>
                    )
                })}
            </div>
        </div>
    );
}

export default BoxPage;

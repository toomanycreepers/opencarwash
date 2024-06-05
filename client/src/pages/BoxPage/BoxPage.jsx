import { useEffect, useState } from "react";
import OrderCard from "../../components/OrderCard/OrderCard";
import BackButton from "../../components/UI/BackButton/BackButton";
import cl from './BoxPage.module.css'
import { Link, useParams } from "react-router-dom";
import OrderService from "../../API/OrderService";
import { toast } from "react-toastify";
import TariffService from '../../API/TariffService'
import AuthorizationService from "../../API/AuthorizationService";
import getDateInFormat from "../../helper/getCurrentDate";

const BoxPage = () => {
    const { carwashId, boxId} = useParams()
    let date = new Date()
    date = date.toLocaleDateString("de-DE")

    const [orders, setOrders] = useState([])

    const deleteOrders = (orderId) => {
        setOrders(prevOrders => prevOrders.filter(order => order.id !== orderId));
    }
    
    useEffect(() => {
        const getCurrentOrders = async () => {
            const date1 = getDateInFormat()
            try {
                const resp = await OrderService.getCurrentOrders(boxId)
                if (resp?.data) {
                    setOrders(resp.data)
                }
            } catch {
                toast.error('Ошибка при получении заказов. Попробуйте позже.', {
                    position: 'top-right',
                })
            }
        } 
    
        getCurrentOrders()
    }, [])
    

    return (
        <div className={cl.boxDetails}>  
            <BackButton navigateTo='/' />
            <div className={cl.dateBar}>
                <p className={cl.currentDate} >{date}</p>
                <Link to={`/${boxId}/history`} className={cl.historyLink}>История</Link>
            </div>

            {orders.length 
            ? <div className={cl.wrapper}>
                {orders.map((order, index) => {
                    return (       
                        <OrderCard 
                            key={index} 
                            order={order}
                            deleteOrders={deleteOrders}
                            isReadOnly={false}
                        /> 
                    )
                })}
            </div>
            : <div className={cl.emptyArrHandler}>На сегодня все заказы выполнены!</div>}
            
        </div>
    );
}

export default BoxPage;
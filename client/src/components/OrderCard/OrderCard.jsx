import { useState, useRef, useEffect } from 'react';
import Button from '../UI/Button/Button'
import cl from './OrderCard.module.css'
import { observer } from 'mobx-react-lite';
import { toast } from "react-toastify";
import OrderService from '../../API/OrderService';

const OrderCard = observer(({isReadOnly=true, order, deleteOrders}) => {
    const [isClosed, setIsClosed] = useState(false)
    const [isActive, setIsActive] = useState(false)
    const states = {
        0: 'На рассмотрении',
        1: 'Отклонен',
        2: 'Принят',
        3: 'Отозван',
        4: 'Завершен',
        5: 'Тех. неполадки'
    }

    const [contentHeight, setContentHeight] = useState(0)
    const contentRef = useRef(null)

    useEffect(() => {
        if (contentRef.current) {
            setContentHeight(contentRef.current.scrollHeight);
        }
    }, [isActive])

    const closeOrder = async () => {
        try {
            const resp = await OrderService.closeOrder(order.id);
            setIsActive(false)
            deleteOrders(order.id)
            toast.success(`Заказ №${order.id} закрыт!`, {
                position: "top-right"
            });
                // myRef.current.classList.add(cl.closedCard)
            setIsActive(false)
            
            setTimeout(() => {
                deleteOrders(order.id)
            }, 400)
        } catch {
            toast.error("Ошибка при закрытии заказа. Попробуйте позже.", {
                position: "top-right"
            });
        }
    }    

    const cancelOrder = async (event) => {
        event.stopPropagation()
        try {
            const resp = await OrderService.cancelOrder(order.id)
            if (resp) {
                setIsActive(false)
                deleteOrders(order.id)
                toast.success("Заказ отменен!", { position: "top-right" });
            }
        } catch {
            toast.error("Ошибка при отмене заказа. Попробуйте позже.", { position: "top-right" });
        }
    }

    return (
        <div className={`${cl.orderCard}`}>
            <div 
                className={isActive ? cl.cardHeader + ' ' + cl.orderCardActive : cl.cardHeader} 
                onClick={() => setIsActive(!isActive)}
            >
                <p className={cl.orderNumber}>Заказ №{order.id}</p>
                <p className={cl.timeInterval}>{states[order.state] ? states[order.state] : 'ERROR'}</p>
                <p className={cl.orderAbout}>Информация о заказе</p>
                {!isReadOnly &&
                    <button className={cl.cancelOrderBtn}  onClick={e => cancelOrder(e)}>Отменить заказ</button>
                }
            </div>
            <div 
                className={cl.orderBody} 
                ref={contentRef}
                style={{ maxHeight: isActive ? `${contentHeight}px` : '0' }}
            >
                <div className={cl.orderBodyWrapper}>
                    <p className={cl.tariff}>Тариф: {order.fullTariff.name}</p>
                    <p className={cl.services}>Услуги:</p>
                    <div className={cl.servicesWrapper}>
                        {order.fullTariff.services.map((service, key) => {
                            return (
                                <div key={key} className={cl.serviceWrapper}>
                                    <p className={cl.serviceName}>{service.name}</p>
                                    <p className={cl.serviceDuration}>{service.duration} мин</p>
                                </div>
                            )
                        })}
                    </div>
                    {!isReadOnly &&
                        <Button style={{background: '#FFF', margin: '20px 0 10px 0'}} onClick={() => closeOrder()} >Завершить заказ</Button>
                    }
                </div>
            </div>
        </div>
    );
})

export default OrderCard;
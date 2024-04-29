import { useState, useRef, useEffect } from 'react';
import OrderService from '../OrderService/OrderService';
import cl from './OrderCard.module.css'

const OrderCard = ({orderNumber, timeInterval, ...props}) => {
    const [isActive, setIsActive] = useState(false)

    const [test, setTest] = useState(false)

    const [contentHeight, setContentHeight] = useState(0)
    const contentRef = useRef(null)

    useEffect(() => {
        if (contentRef.current) {
            setContentHeight(contentRef.current.scrollHeight);
        }
    }, [isActive])
    

    return (
        <div className={cl.orderCard}>
            <div 
                className={isActive ? cl.cardHeader + ' ' + cl.orderCardActive : cl.cardHeader} 
                onClick={() => setIsActive(!isActive)}
            >
                <p className={cl.orderNumber}>Заказ №{orderNumber}</p>
                <p className={cl.timeInterval}>{timeInterval}</p>
                <p className={cl.orderAbout}>Информация о заказе</p>
                <button className={cl.cancelOrderBtn}  onClick={(e) => e.stopPropagation()}>Отменить заказ</button>
            </div>
            <div 
                className={cl.orderBody} 
                ref={contentRef}
                style={{ maxHeight: isActive ? `${contentHeight}px` : '0' }}
            >
                <div className={cl.orderBodyWrapper}>
                    <p className={cl.services}>Услуги:</p>
                    <div className={cl.servicesWrapper}>
                        <OrderService serviceName={"Услуга 1"} isActive={test} setIsActive={setTest} />
                        <OrderService serviceName={"Услуга 2"} isActive={test} setIsActive={setTest} />
                        <OrderService serviceName={"Услуга 3"} isActive={test} setIsActive={setTest} />
                        <OrderService serviceName={"Услуга 4"} isActive={test} setIsActive={setTest} />
                        <OrderService serviceName={"Услуга 5"} isActive={test} setIsActive={setTest} />
                        <OrderService serviceName={"Услуга 6"} isActive={test} setIsActive={setTest} />
                        <OrderService serviceName={"Услуга 7"} isActive={test} setIsActive={setTest} />
                    </div>
                </div>
            </div>
        </div>
    );
}

export default OrderCard;

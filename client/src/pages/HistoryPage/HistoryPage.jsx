import { useEffect, useState } from "react";
import OrderCard from "../../components/OrderCard/OrderCard";
import BackButton from "../../components/UI/BackButton/BackButton";
import getCurrentDate from "../../helper/getCurrentDate";
import cl from './HistoryPage.module.css'
import CarwashService from "../../API/CarwashService";
import { useNavigate, useParams } from "react-router-dom";
import OrderService from "../../API/OrderService";
import { ToastContainer, toast } from "react-toastify";

const HistoryPage = () => {
    const navigate = useNavigate()
    const {boxId} = useParams()

    const [history, setHistory] = useState([])

    useEffect(() => {
        // Фетч на получение инфы о сегоднящних заказах
        const getHistory = async () => {
            try {
                const resp = await OrderService.getHistory(boxId)
                console.log(resp);
                if (resp?.data) {
                    setHistory(resp.data)
                }
            } catch(e) {
                toast.error("История временно недостпуна. Попробуйте позже.", {position: 'top-right'})
            }
        }

        getHistory()
    }, [])

    const handleBackBtn = () => {
        if (window.history.length > 2) {
            navigate(-1);
        } else {
            navigate('/');
        }
    }

    return (
        <>
            <div className={cl.boxDetails}>  
                <BackButton onClick={handleBackBtn} />
                <div className={cl.dateBar}>
                    <p className={cl.currentDate} >История</p>
                </div>

                {history && history.length ? <div className={cl.wrapper}>
                    {history.map((day, index) => {
                        return (
                            <div className={cl.dayBlock} key={index}>
                                <h2 className={cl.date}>{day.startTime}</h2>
                                    <OrderCard key={index} order={day} />
                            </div>
                        )
                    })}
                </div>
                : <div className={cl.emptyArrHandler}>Здесь будет отображаться история ваших заказов</div>}
                
            </div>
            <ToastContainer />
        </>
    );
}

export default HistoryPage;

import { Link, Outlet, useParams } from 'react-router-dom';
import cl from './BoxSettings.module.css'
import { ToastContainer, toast } from 'react-toastify';
import { useEffect, useState } from 'react';
import BackButton from '../UI/BackButton/BackButton';
import TimeSettings from '../TimeSettings/TimeSettings';
import Button from '../UI/Button/Button';
import BoxService from '../../API/BoxService';

const BoxSettings = () => {
    const { boxId } = useParams()
    const [boxOptions, SetBoxOptions] = useState([])
    const [clone, setClone] = useState([])
    const [isBoxClosed, setIsBoxClosed] = useState()

    useEffect(() => {
        SetBoxOptions(boxOptions.sort((a, b) => a.weekday - b.weekday))
        setClone(JSON.parse(JSON.stringify(boxOptions)))
        setIsBoxClosed(boxOptions[0]?.isClosed)
    }, [boxOptions])

    useEffect(() => {
        // Получение сеттингов бокса по id
        const getBoxOptions = async () => {
            try {
                const resp = await BoxService.getBoxBH(boxId)
                if (resp.data) {
                    SetBoxOptions(resp.data)
                }
            } catch {
                toast.error("Ошибка при получении данных бокса. Попробуйте позже.", {position: 'top-right'})
            }
        }

        getBoxOptions()
    }, [boxId])

    const handleSubmit = async () => {
        try {
            // Проверка различий между clone и boxOptions
            const changes = clone.filter((item, index) => {
                return item.openingTime !== boxOptions[index].openingTime || item.closingTime !== boxOptions[index].closingTime;
            });
            // Проверка, есть ли в changes элементы, у которых openingTime больше или равно closingTime
            for (const change of changes) {
                const { openingTime, closingTime } = change;
                if (openingTime >= closingTime) {
                    toast.error("Ошибка: время открытия больше или равно времени закрытия.", {
                        position: "top-right"
                    });
                    return;
                }
            }
            // Отправка запросов на изменение для каждого отличающегося элемента
            for (const change of changes) {
                const { id, openingTime, closingTime } = change;
                await BoxService.changeBusinessHours(id, openingTime, closingTime);
            }
            SetBoxOptions([...clone])
        } catch {
            toast.error("Произошла ошибка. Попробуйте позже.", {
                position: "top-right"
            })
        }
    };
    
    const openBox = async () => {
        try {
            const resp = BoxService.openBox(boxId)
            setIsBoxClosed(false)
            if (resp) {
                toast.success("Бокс открыт!", {position: 'top-right'})
            }
        } catch {
            toast.error("Ошибка при открытии бокса. Попробуйте позже.", {position: 'top-right'})
        }
    }

    const closeBox = async () => {
        try {
            const resp = BoxService.closeBox(boxId)
            setIsBoxClosed(true)
            if (resp) {
                toast.success("Бокс закрыт!", {position: 'top-right'})
            }
        } catch {
            toast.error("Ошибка при закрытии бокса. Попробуйте позже.", {position: 'top-right'})
        }
    }

    return (
        <>
            <div className={cl.header}>
                <BackButton style={{backgroundColor: 'white'}} navigateTo='/profile/settings' />
                <span className={cl.headerSpan}>Настройки бокса</span>
            </div>
            <div className={cl.serviceListWrapper}>
                <TimeSettings clone={clone} setClone={setClone} boxOptions={boxOptions} />
                <Link to={`/settings/${boxId}/options/tariffs`} className={cl.link}>
                    <Button>Список тарифов</Button>
                </Link>
                <div className={cl.btnsWrapper}>
                    {isBoxClosed ? <Button onClick={openBox}>Открыть бокс</Button>
                        : <Button style={{ backgroundColor: 'rgba(231, 86, 86, 1)' }} onClick={closeBox}>Закрыть бокс</Button>
                    }
                    <Button onClick={handleSubmit}>Сохранить изменения</Button>
                </div>
            </div>
            <ToastContainer />
            <Outlet />
        </>
    );
}

export default BoxSettings;
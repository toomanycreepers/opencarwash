import { useEffect, useState } from 'react';
import BoxCard from '../UI/BoxCard/BoxCard';
import cl from './CarwashSettings.module.css'
import AddCard from '../UI/AddCard/AddCard';
import Button from '../UI/Button/Button';
import SettingModal from '../UI/SettingModal/SettingModal';
import TimeSettings from '../TimeSettings/TimeSettings';
import { Link, useNavigate } from 'react-router-dom';
import Input from '../UI/Input/Input';
import CarwashService from '../../API/CarwashService';
import { toast } from 'react-toastify';
import AuthorizationService from '../../API/AuthorizationService';
import { getDateInFormat } from "../../helper/getCurrentDate";

const CarwashSettings = () => {
    const navigate = useNavigate()
    const [isModalVisible, setIsModalVisible] = useState(false)
    const [chosenCarwash, setChosenCarwash] = useState(null)

    const [carwashSettings, setCarwashSettings] = useState({
        id: '123',
        name: 'Автомойка 3'
    })

    const [carwashes, setCarwashes] = useState([])

    const [carwashesAE, setCarwashesAE] = useState()
    const [timeSettings, setTimeSettings] = useState([]) 

    useEffect(() => {
        setCarwashesAE(carwashes.map(carwash => carwash.timeSlot))
    }, [carwashes])

    const getAllCarwashes = async () => {
        try {
            const userResp = await AuthorizationService.getUserInfo()
            if (userResp.data.id) {
                const date = getDateInFormat()
                const resp = await CarwashService.getAllCarwashes(userResp.data.id, date)
                setCarwashes(resp.data)
            }
        } catch {
            toast.error('Ошибка при получении данных автомоек. Попробуйте позже.', {position: 'top-right'})
        }
    }

    useEffect(() => {
        getAllCarwashes()
    }, [])

    const directToBoxSettings = (boxId) => {
        navigate(`/settings/${boxId}/options`)
    }

    const handleAEChange = (e, index) => {
        const value = e.target.value;
        if (value < 1 || value > 60 || !Number.isInteger(Number(value))) {
            return;
        }
        const newCarwashesAE = [...carwashesAE];
        newCarwashesAE[index] = value;
        setCarwashesAE(newCarwashesAE);
    }

    const changeAE = async (id, index) => {
        const minutes = carwashesAE[index]
        // Запрос на изменение атомарной единицы
        try {
            const resp = await CarwashService.updateCarwashAE(id, minutes)
            if(resp) {
                toast.success("Атомарная единица изменена!", {position: 'top-right'})
            }
        } catch {
            toast.error('Ошибка при изменении атомарной единицы', {position: "top-right"})
        }
        
    }

    const openCarwashModal = () => {
        setIsModalVisible(true)
    }

    return (
        <div className={cl.settingsSection}>
            {carwashes.map((carwash, index) => {
                return (
                    <div className={cl.carwashWrapper} key={carwash.id}>
                        <div className={cl.nameBlock}>
                            <div className={cl.wrapper}>
                                <p className={cl.carwashName}>{carwash.street} {carwash.building}</p>
                                <Link to={`/profile/settings/${carwash.id}/services`}>
                                    <Button >Услуги</Button>
                                </Link>
                            </div>
                            {carwashesAE 
                            ?
                                <div className={cl.wrapper}>
                                    <p className={cl.carwashAE}>Единица времени автомойки</p>
                                    <Input value={carwashesAE[index]} onChange={(e) => handleAEChange(e, index)} type='number' maxLength='2' style={{minWidth: '170px', maxWidth: 170, height: 59, fontSize: 20, fontWeight: 500}} />
                                    <Button style={{lineHeight: 1}} onClick={() => changeAE(carwash.id, index)}>
                                        <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#000000"><path d="M382-240 154-468l57-57 171 171 367-367 57 57-424 424Z"/></svg>
                                    </Button>
                                </div>
                            : ''
                            }
                        </div>
                        <div className={cl.cardWrapper}>
                            {carwash.boxes.map(box => {
                                return (
                                    <BoxCard 
                                        key={box.id} 
                                        cardInfo={box}  
                                        onClick={() => directToBoxSettings(box.id)}
                                    />
                                )
                            })}
                            {/* <AddCard onClick={openCarwashModal} /> */}
                        </div>
                    </div>
                )
            })}
            <SettingModal name={`${carwashSettings.name}. Создание бокса`} isVisible={isModalVisible} setIsVisible={setIsModalVisible} >
                {/* <TimeSettings daysSettings={timeSettings} setDaysSettings={setTimeSettings} clone={cloneSettings} setClone={setCloneSettings}  /> */}
            </SettingModal>
        </div>
    );
}

export default CarwashSettings;
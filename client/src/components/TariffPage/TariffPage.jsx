import { useParams } from 'react-router-dom';
import cl from './TariffPage.module.css'
import { ToastContainer, toast } from 'react-toastify';
import BackButton from '../UI/BackButton/BackButton';
import TariffCard from '../TariffCard/TariffCard';
import Button from '../UI/Button/Button';
import { useEffect, useRef, useState } from 'react';
import SettingModal from '../UI/SettingModal/SettingModal';
import Input from '../UI/Input/Input';
import BoxService from '../../API/BoxService';
import CarwashService from '../../API/CarwashService';
import Select from '../UI/Select/Select'
import Textarea from '../UI/Textarea/Textarea'
import ServiceIcon from '../ServiceIcon/ServiceIcon';
import TariffService from '../../API/TariffService'

const TariffPage = () => {
    const { boxId } = useParams()
    const [tariffs, setTariffs] = useState([])
    const [clone, setClone] = useState([])
    const [isModalVisible, setIsModalVisible] = useState(false)
    const [isSelectAdded, setIsSelectAdded] = useState(false)

    const [newTariffInfo, setNewTariffInfo] = useState({
        name: '',
        description: '',
        comment: '',
        services: [],
    })

    const [services, setServices] = useState([])

    useEffect(() => {
        const getServices = async () => {
            try {
                const resp = await BoxService.getById(boxId)
                const carwashId = resp.data.carwashId
                const servicesResp = await CarwashService.getServicesById(carwashId)
                setServices(servicesResp.data)
            } catch {
                // toast.error("Ошибка при загрузке услуг.")
            }
        }

        getServices()
    }, [])

    useEffect(() => {
        setClone([...tariffs])
    }, [tariffs])

    useEffect(() => {
        const getTariffs = async () => {
            try {
                // Запрос на получение тарифов бокса
                const resp = await BoxService.getTariffsById(boxId)
                if(resp.data) {
                    setTariffs(resp.data)
                }
            } catch {
            }
        }

        getTariffs()
    }, [])

    const handleServiceChange = (value) => {
        const selectedService = services.find(service => service.id === value);
        const isServiceAdded = newTariffInfo.services.some(service => service.id === selectedService.id);

        if (selectedService && selectedService.id !== 0 && !isServiceAdded) {
            setNewTariffInfo(prevState => ({
                ...prevState,
                services: [...prevState.services, selectedService]
            }));
            setIsSelectAdded(false)
        }
    }

    const submitCreation = async () => {
        try {
            // Удаление всех услуг, у которых id равен null
            const validServices = newTariffInfo.services.filter(service => service.id !== null);
            setNewTariffInfo({ ...newTariffInfo, services: validServices })

            const servicesIds = newTariffInfo.services.map(service => ({elem1: service.id, elem2: false}))
            const resp = await TariffService.createTariff(newTariffInfo.name, newTariffInfo.description, servicesIds)

            await TariffService.setBufferTime(resp.data, 1)
            await BoxService.addTariff(boxId, resp.data)
            if(resp) {
                setTariffs([...tariffs, {...newTariffInfo, id: resp.data}])
                setIsModalVisible(false)
                toast.success("Тариф успешно создан!", {position: 'top-right'})
            }
        } catch {
            toast.error("Ошибка создания заказа. Попробуйте позже.", {position: "top-right"})
        }
    }

    const deleteNewService = (serviceId) => {
        setNewTariffInfo(prevState => ({
            ...prevState,
            services: prevState.services.filter(service => service.id !== serviceId)
        }));
    }
    
    
    return (
        <>
            <div className={cl.header}>
                <BackButton style={{backgroundColor: 'white'}} navigateTo={`/settings/${boxId}/options`} />
                <span className={cl.headerSpan}>Тарифы</span>
            </div>
            <div className={cl.main}>
                <div className={cl.addWrapper}>
                    <Button onClick={() => setIsModalVisible(true)}>Добавить</Button>
                </div>
                <div className={cl.serviceListWrapper}>
                    {clone && 
                        clone.map((elem, index) => {
                            return (
                                <TariffCard key={elem.id} clone={clone} setClone={setClone} index={index} tariffsInfo={tariffs} setTariffsInfo={setTariffs} carwashServices={services}/>
                            )
                        })
                    }
                </div>
            </div>
            <SettingModal setIsVisible={setIsModalVisible} isVisible={isModalVisible} name="Создание тарифа">
                <div className={cl.creationSection}>
                    <Input placeholder='Название тарифа' style={{width: 400, height: 60, fontSize: 20}} value={newTariffInfo.name} onChange={(e) => setNewTariffInfo({...newTariffInfo, name: e.target.value})} />
                    <Textarea className={cl.textarea} placeholder='Описание тарифа' value={newTariffInfo.description} onChange={(e) => setNewTariffInfo({...newTariffInfo, description: e.target.value})} />
                    {/* <Textarea className={cl.textarea} placeholder='Комментарий для сотрудника' value={newTariffInfo.comment} onChange={(e) => setNewTariffInfo({...newTariffInfo, comment: e.target.value})} /> */}
                    <div className={cl.addedServices}>
                    {newTariffInfo.services &&
                        newTariffInfo.services.map(service => {
                            return(
                                <ServiceIcon key={service.id} service={service} isEditModeActive={true} deleteCallback={() => deleteNewService(service.id)} />
                            )
                        })
                    }
                    </div>
                    {isSelectAdded &&
                        <Select 
                            options={services} 
                            defaultValue={{value: '', name: 'Выберите услугу'}} 
                            value={services.id}
                            onChange={(e) => handleServiceChange(e.target.value)}
                        />
                    }
                    <Button onClick={() => setIsSelectAdded(true)}>Добавить услугу</Button>
                    <div className={cl.addSubmitBtn}>
                        <Button onClick={submitCreation}>Сохранить тариф</Button>
                    </div>
                </div>
            </SettingModal>
            <ToastContainer />
        </>
    );
}

export default TariffPage;
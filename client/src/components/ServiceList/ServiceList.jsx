import { Link, useNavigate, useParams } from 'react-router-dom';
import BackButton from '../UI/BackButton/BackButton';
import Button from '../UI/Button/Button';
import cl from './ServiceList.module.css'
import { useEffect, useState } from 'react';
import Service from '../Service/Service';
import SettingModal from '../UI/SettingModal/SettingModal';
import ServiceCreation from '../ServiceCreation/ServiceCreation';
import { ToastContainer, toast } from 'react-toastify';
import AuthStore from '../../store/AuthStore';
import { observer } from 'mobx-react-lite';
import CarwashService from '../../API/CarwashService';

const ServiceList = observer(() => {
    const { carwashId } = useParams()
    const navigate = useNavigate()
    const [services, setServices] = useState([])
    const [isModalVisible, setIsModalVisible] = useState(false)
    const [carwashAE, setCarwashAE] = useState(1)
    const [creationPendingService, setCreationPendingService] = useState({
        name: '',
        price: '',
        duration: '',
        description: '',
        durationInMin: ''
    })

    const [authChecked, setAuthChecked] = useState(false);

    useEffect(() => {
        const getCarwashAE = async () => {
            try {
                const resp = await CarwashService.getCarwashById(carwashId)
                if (resp.data) {
                    setCarwashAE(resp.data.timeSlot)
                }
            } catch {
                toast.error("Ошибка получения АЕ.", {position: 'top-right'})
            }
        }

        getCarwashAE()
    }, [])

    useEffect(() => {
        const getServices = async () => {
            try {
                const resp = await CarwashService.getServicesById(carwashId)
                if(resp.data) {
                    setServices(resp.data)
                }
            } catch {
                toast.error("Ошибка при получении услуг. Попробуйте позже.", {position: 'top-right'})
            }
        }

        getServices()
    }, [])

    useEffect(() => {
        const checkAuth = async () => {
            await AuthStore.checkAuth();
            if (!AuthStore.isAuth) {
                navigate('/login')
            }
            setAuthChecked(true);
        };
        checkAuth();
    }, []);

    const updateService = async (id, setter, updatedService) => {
        try {
            const resp = await CarwashService.updateService(id, updatedService.name, updatedService.price, updatedService.description, updatedService.duration, carwashId)
            
            if(resp) {
                setServices(services.map(service => service.id === id ? updatedService : service));
                setter(true)
                toast.success("Услуга обновлена.", {position: 'top-right'})
            }
        } catch {
            toast.error("Ошибка при обновлении услуги. Попробуйте позже", {position: 'top-right'})
        }
    }    

    const createNewService = async () => {
        try {
            const resp = await CarwashService.createService(creationPendingService.name, 
                creationPendingService.price, creationPendingService.description, creationPendingService.duration, carwashId)
            
            const servicesCopy = [...services]
            servicesCopy.push({...creationPendingService, id: resp.data.id})
            setServices(servicesCopy)
            setIsModalVisible(false)
        } catch {
            toast.error("Ошибка при создании услуги. Попробуйте позже", {position: 'top-right'})
        }
    }

    const deleteService = async (id) => {
        try {
            const resp = await CarwashService.deleteService(id)
    
            if(resp) {
                setServices(services.filter(service => service.id !== id))
                toast.success("Услуга удалена.", {position: 'top-right'})
            }
        } catch {
            toast.error("Ошибка при удалении услуги. Попробуйте позже", {position: 'top-right'})
        }
    }

    if (!authChecked) {
        return <div>Проверка авторизации...</div>;
    } else {
        return (
            <>
                <div className={cl.header}>
                    <BackButton style={{backgroundColor: 'white'}} navigateTo='/profile/settings' />
                    <span className={cl.headerSpan}>Услуги</span>
                </div>
                <div className={cl.serviceListWrapper}>
                    <div>
                        <Button style={{float: 'right'}} onClick={() => setIsModalVisible(true)}>Добавить</Button>
                    </div>
                    <div className={cl.servicesWrapper}>
                        {services.length > 0 ? services.map(service => {
                            return (
                                <Service key={service.id} service={service} updateService={updateService} carwashAE={carwashAE} deleteService={deleteService} />
                            )
                        })
                        : <div>Нет добавленных услуг</div>
                        }
                    </div>
                </div>
                <SettingModal name="Добавление услуги" isVisible={isModalVisible} setIsVisible={setIsModalVisible}>
                    <ServiceCreation service={creationPendingService} setService={setCreationPendingService} createNewService={createNewService} carwashAE={carwashAE} />
                </SettingModal>
                <ToastContainer />   
            </>
        );
    }
})

export default ServiceList;
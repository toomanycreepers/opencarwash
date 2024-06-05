import { useEffect, useRef } from 'react';
import Button from '../UI/Button/Button';
import Input from '../UI/Input/Input';
import cl from './ServiceCreation.module.css'
import { toast } from 'react-toastify'

const ServiceCreation = ({service, setService, createNewService, carwashAE}) => {
    const textareaRef = useRef(null)

    const handleChange = (event) => {
        const value = event.target.value;
        if (['price', 'durationInAE', 'duration'].includes(event.target.name)) {
            if (value !== '' && (isNaN(value) || (event.target.name === 'durationInAE' && (value.includes('.') || value <= 0 || value > 60/carwashAE)))) {
                if (event.target.name === 'durationInAE' && value > (60 / carwashAE)) {
                    toast.error('Длительность не может быть больше 60 минут');
                }
                return;
            }
        }

        // Дополнительные проверки для price
        if (event.target.name === 'price') {
            if (value > 500000 || (value.includes('.') && (value.split('.')[1].length > 2 || parseFloat(value) === 500000))) {
                toast.error('Цена должна быть меньше 500000 и содержать не более двух цифр после десятичной точки.');
                return;
            }
        }

        let updatedService = {
            ...service,
            [event.target.name]: value
        };

        if (event.target.name === 'duration' && value !== '') {
            updatedService.durationInMin = value * carwashAE;
        }

        if (event.target.name === 'durationInAE' && value !== '') {
            updatedService.duration = value;
        }

        setService(updatedService);
    };

    const handleKeyPress = (event) => {
        if (!/[0-9]/.test(event.key)) {
            event.preventDefault();
        }
    };

    useEffect(() => {
        if (textareaRef.current) {
            textareaRef.current.style.height = 'auto';
            textareaRef.current.style.height = `${textareaRef.current.scrollHeight}px`;
        }
    }, [service.description])

    return (
        <div className={cl.contentWrapper}>
            <div className={cl.mainInfo}>
                <div className={cl.inputWrapper}>
                    <p className={cl.label}>Название</p>
                    <Input placeholder='Название' name='name' value={service.name} onChange={handleChange} style={{width: 390, height: 48, fontSize: 20}} />
                </div>
                <div className={cl.inputWrapper}>
                    <p className={cl.label}>Цена</p>
                    <Input placeholder='Цена' name='price' value={service.price} onChange={handleChange} style={{width: 390, height: 48, fontSize: 20}} />
                </div>
            </div>
            <div className={cl.mainInfo}>
                <div className={cl.inputWrapper}>
                    <p className={cl.label}>Время в единицах автомойки</p>
                    <Input type='number' placeholder='Длительность' name='durationInAE' value={service.duration} onChange={handleChange} onKeyPress={handleKeyPress} style={{width: 390, height: 48, fontSize: 20}} />
                </div>
                <div className={cl.inputWrapper}>
                    <p className={cl.label}>Время в минутах</p>
                    <Input disabled placeholder='Длительность' name='duration' value={service.duration * carwashAE} onChange={handleChange} style={{width: 390, height: 48, fontSize: 20}} />
                </div>
            </div>
            <div className={cl.inputWrapper}>
                <p className={cl.label}>Описание услуги</p>
                <textarea ref={textareaRef} placeholder='Описание' name='description' value={service.description} onChange={handleChange} className={cl.description} />
            </div>
            <Button style={{position: 'absolute', bottom: 0, right: 0}} onClick={createNewService}>Добавить услугу</Button>
        </div>
    );
}

export default ServiceCreation;

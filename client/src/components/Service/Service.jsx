import { useState, useEffect, useRef } from 'react';
import cl from './Service.module.css'
import Button from '../UI/Button/Button';

const Service = ({ service, updateService, carwashAE, deleteService }) => {
    const [currentService, setCurrentService] = useState(service);
    const [isDisabled, setIsDisabled] = useState(true)
    const textareaRef = useRef(null);

    useEffect(() => {
        if (textareaRef.current) {
            textareaRef.current.style.height = 'auto';
            textareaRef.current.style.height = `${textareaRef.current.scrollHeight}px`;
        }
    }, [currentService.description]);

    const handleChange = (event) => {
        const value = event.target.value;
        if (event.target.name === 'duration') {
            if (!value || isNaN(value) || value.length > 3) {
                return;
            }
        }
        if (event.target.name === 'price') {
            if (!value || isNaN(value) || value.length > 6) {
                return;
            }
        }

        setCurrentService({
            ...currentService,
            [event.target.name]: event.target.value
        });
    };

    const handleCancel = () => {
        setCurrentService(service)
        setIsDisabled(true)
    }

    return (
        <div className={cl.serviceWrapper}>
            <div className={cl.header}>
                <input 
                    type="text" 
                    name="name" 
                    value={currentService.name} 
                    onChange={handleChange} 
                    className={cl.name + ' ' + cl.inp}
                    disabled={isDisabled}
                    maxLength={23}
                />
                <div className={cl.durationWrapper}>
                    <input 
                        type="number" 
                        name="duration" 
                        max='500'
                        min='1'
                        value={currentService.duration} 
                        onChange={handleChange} 
                        className={cl.duration + ' ' + cl.inp}
                        disabled={isDisabled}
                    />
                    <span className={cl.subSpan}>АЕ</span>
                </div>
                <div className={cl.priceWrapper}>
                    <input 
                        type="number" 
                        name="price" 
                        max='500000'
                        min='1'
                        value={currentService.price} 
                        onChange={handleChange} 
                        className={cl.price + ' ' + cl.inp}
                        disabled={isDisabled}
                    />
                    <span className={cl.subSpan}>руб</span>
                </div>
                {isDisabled 
                ? 
                <div className={cl.editWrapper}>
                    <div className={cl.editBtn} onClick={() => setIsDisabled(false)}>
                        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M1.91759 16.5552C2.5154 15.7495 14.4197 4.02797 14.4197 4.02797L20.0339 9.64182C20.0339 9.64182 7.79178 21.8313 7.55784 22.0131C7.3239 22.195 6.78655 22.5202 6.25825 22.7149C5.76442 22.8969 1.39777 23.9624 1.2418 23.9883C1.08583 24.0142 0.561188 24.0197 0.280105 23.7544C-0.0441812 23.4483 -0.0318542 22.9226 0.0461785 22.6368C0.124211 22.351 1.18982 18.1406 1.18982 18.1406C1.18982 18.1406 1.31978 17.3608 1.91759 16.5552Z" fill="#06060C"/>
                            <path d="M17.4607 0.935158C16.1611 2.23457 15.8492 2.57253 15.8492 2.57253L21.5154 8.16039C21.5154 8.16039 22.2172 7.43267 23.2049 6.44504C24.1926 5.45742 24.398 2.74209 23.023 1.24704C21.5502 -0.354197 18.7603 -0.364253 17.4607 0.935158Z" fill="#06060C"/>
                        </svg>
                    </div> 
                </div>
                : 
                <div className={cl.controlBtns}>
                    <div className={cl.cancel} onClick={() => handleCancel()}>
                        <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#000"><path d="m256-200-56-56 224-224-224-224 56-56 224 224 224-224 56 56-224 224 224 224-56 56-224-224-224 224Z"/></svg>
                    </div>
                    <div className={cl.submit} onClick={() => updateService(service.id, setIsDisabled, currentService)}>
                        <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#000"><path d="M382-240 154-468l57-57 171 171 367-367 57 57-424 424Z"/></svg>
                    </div>
                </div>
                }
                
            </div>
            <textarea 
                ref={textareaRef}
                name="description" 
                value={currentService.description} 
                onChange={handleChange} 
                className={cl.description}
                disabled={isDisabled}
                style={{ overflow: 'hidden', resize: 'none' }}
            />
            <div>
                <Button onClick={() => deleteService(service.id)}>Удалить</Button>
            </div>
        </div>
    );
}

export default Service
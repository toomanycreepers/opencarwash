import cl from './TariffCard.module.css'
import Button from '../UI/Button/Button'
import { useRef, useState, useEffect } from 'react';
import { toast } from 'react-toastify';
import AddPlus from '../UI/AddPlus/AddPlus';
import TransparentTextarea from '../UI/TransparentTextarea/TransparentTextarea';
import Select from '../UI/Select/Select';
import ServiceIcon from '../ServiceIcon/ServiceIcon';
import TariffService from '../../API/TariffService'
import BoxService from '../../API/BoxService';
import { useParams } from 'react-router-dom';

const TariffCard = ({ clone, setClone, index, tariffsInfo, setTariffsInfo, carwashServices }) => {
    const bodyRef = useRef(null)
    const {boxId} = useParams()
    const [isBodyVisible, setIsBodyVisible] = useState(false)
    const [contentHeight, setContentHeight] = useState(0);
    const [isEditModeActive, setIsEditModeActive] = useState(false)
    const [isSelectAdded, setIsSelectAdded] = useState(false)

    useEffect(() => {
        if (bodyRef.current) {
            setContentHeight(bodyRef.current.scrollHeight);
        }
    }, [isBodyVisible]);

    useEffect(() => {
        if (bodyRef.current) {
            setContentHeight(bodyRef.current.scrollHeight);
        }
    }, [isBodyVisible, clone[index].description, clone[index].comment, isSelectAdded, isEditModeActive]);

    const handleChange = (event) => {
        const updatedClone = [...clone];
        if (event.target.name === 'name' && event.target.value.length > 25) {
            return;
        }
        if ((event.target.name === 'description' || event.target.name === 'commentForEmployees') && event.target.value.length > 254) {
            return;
        }
        updatedClone[index] = {...updatedClone[index], [event.target.name]: event.target.value};
        setClone(updatedClone);
    }

    const handleCancel = () => {
        setIsEditModeActive(false)
        setIsSelectAdded(false)
        const updatedClone = [...clone]
        updatedClone[index] = {...tariffsInfo[index]}
        setClone(updatedClone)
    }

    const deleteService = (id) => {
        const updatedClone = JSON.parse(JSON.stringify(clone));
        updatedClone[index].services = updatedClone[index].services.filter(service => service.id !== id);
        setClone(updatedClone);
    }
    

    const handleSelectChange = (selectedOption) => {
        const value = selectedOption.target.value
        const optionName = selectedOption.target.options[selectedOption.target.selectedIndex].text
        if (selectedOption.value !== '') {
            const updatedClone = JSON.parse(JSON.stringify(clone))
            const isServiceAlreadyAdded = updatedClone[index].services.some(service => service.id === value);
            if (isServiceAlreadyAdded) {
                toast.error("Услуга уже добавлена", {
                    position: 'top-right'
                })
            } else {
                updatedClone[index].services = [...updatedClone[index].services, { id: value, name: optionName }] 
                setClone(updatedClone);
                setIsSelectAdded(false)
            }
        }
    }

    const saveChanges = async () => {
        setIsBodyVisible(!isBodyVisible)
        const newValue = [...tariffsInfo]
        newValue[index] = clone[index]
        try {
            const resp = await TariffService.updateTariff(
                newValue[index].id,
                newValue[index].name,
                newValue[index].description,
                newValue[index].services.map(elem => elem.id),
                newValue[index].commentForEmployees,
                0
            )
            setTariffsInfo(newValue) 
            toast.success("Тариф изменен!", {
                position: 'top-right'
            })
        } catch {
            toast.error("Ошибка при изменении тарифа", {
                position: 'top-right'
            })
        }
    }

    const deleteTariff = async () => {
        try {
            await BoxService.removeTariff(boxId, clone[index].id)
            toast.success("Тариф удален!", {
                position: 'top-right'
            })
            // Удаление элемента из массива clone
            const updatedClone = [...clone]
            updatedClone.splice(index, 1)
            setClone(updatedClone)
            // Удаление элемента из массива tariffsInfo
            const updatedTariffsInfo = [...tariffsInfo]
            updatedTariffsInfo.splice(index, 1)
            setTariffsInfo(updatedTariffsInfo)
        } catch {
            toast.error("Ошибка при удалении тарифа", {
                position: 'top-right'
            })
        }
    }    

    return (
        <div className={cl.tariffCard}>
            <div className={cl.header}>
                <input className={cl.name + ' ' + cl.input} value={clone[index].name} onChange={handleChange} disabled={!isBodyVisible} />
                <p className={cl.bufferTime}>
                    <span className={cl.bufferTime} ></span>
                </p>
                <div className={cl.openerWrapper}>
                    <button className={cl.opener} onClick={() => setIsBodyVisible(!isBodyVisible)}>
                        {!isBodyVisible ? <svg xmlns="http://www.w3.org/2000/svg" height="35px" viewBox="0 -960 960 960" width="35px" fill="#000000"><path d="M480-344 240-584l56-56 184 184 184-184 56 56-240 240Z"/></svg>
                        : <svg xmlns="http://www.w3.org/2000/svg" height="35px" viewBox="0 -960 960 960" width="35px" fill="#000000"><path d="M480-528 296-344l-56-56 240-240 240 240-56 56-184-184Z"/></svg>
                        }
                    </button>
                </div>
            </div>
            <div className={cl.main} ref={bodyRef} style={{ maxHeight: isBodyVisible ? `${contentHeight}px` : '0' }}>
                <div className={cl.descriptionWrapper}>Описание: 
                    <TransparentTextarea placeholder="Описание тарифа" name='description' value={clone[index].description} onChange={handleChange} disabled={!isEditModeActive} />
                </div>
                <div className={cl.commentWrapper}>Комментарий:   
                    <TransparentTextarea placeholder="Комментарий для сотрудников" name='commentForEmployees' value={clone[index].commentForEmployees} onChange={handleChange} disabled={!isEditModeActive} />
                </div>
                <div className={cl.serviceList}>
                    {clone[index].services &&
                        clone[index].services.map(service => {
                            return(
                                <ServiceIcon key={service.id} service={service} isEditModeActive={isEditModeActive} deleteCallback={deleteService} />
                            )
                        })
                    }
                    {isSelectAdded &&
                        <Select style={{maxWidth: 350, height: 48}} options={carwashServices} defaultValue={{value: '', name: 'Выберите услугу'}} value={carwashServices.id} onChange={handleSelectChange}   />
                    }
                    {isEditModeActive &&
                        <AddPlus onClick={() => setIsSelectAdded(true)} />
                    }
                </div>
                <div className={cl.editWrapper}>
                    {isEditModeActive ?
                        <div className={cl.cancelWrapper}>
                            <Button style={{backgroundColor: '#E75656'}} onClick={deleteTariff} >Удалить тариф</Button>
                            <Button onClick={handleCancel}>Отмена</Button>
                            <Button onClick={saveChanges}>Сохранить</Button>
                        </div>
                        : <Button onClick={() => setIsEditModeActive(true)}>Редактировать</Button> 
                    }
                </div>
            </div>
        </div>
    );
}

export default TariffCard;
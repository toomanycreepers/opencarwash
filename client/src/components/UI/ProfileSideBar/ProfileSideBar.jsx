import { useState } from 'react';
import BackButton from '../BackButton/BackButton';
import Button from '../Button/Button';
import cl from './ProfileSideBar.module.css'
import WorkingDays from '../WorkingDays/WorkingDays'
import Select from '../Select/Select';

const ProfileSideBar = ({isVisible, setIsVisible, settingsObj, setEditObj=null, setChosenObj=null}) => {
    const [isEditMode, setIsEditMode] = useState(false)
    const [editedSettings, setEditedSettings] = useState({...settingsObj})

    const closeSidebar = () => {
        setIsVisible(false)
        setEditObj(null)
        setChosenObj(null)
    }

    const statusOptions = [
        {value: 'active', name: 'РАБОТАЕТ'},
        {value: 'waiting', name: 'ОЖИДАЕТ'},
        {value: 'stoped', name: 'ОСТАНОВЛЕНА'}
    ];

    const defaultStatus = statusOptions.find(option => option.value === settingsObj.status);
    const remainingOptions = statusOptions.filter(option => option.value !== settingsObj.status);

    return (
        <div className={!isVisible ? cl.sideBarBody : cl.sideBarBody + ' ' + cl.active}>
            <div className={cl.sideBarHeader}>
                <BackButton direction='right' onClick={() => closeSidebar()} />
                <span className={cl.name}>{settingsObj.name}</span>
                <Button onClick={() => setIsEditMode(!isEditMode)} >{isEditMode ? "Сохранить" : "Редактировать"}</Button>
            </div>
            <WorkingDays isActive={isEditMode} activeDays={editedSettings.workingDays} />
            <div className={cl.statusWrapper}>
                <p className={cl.name}>Статус</p>
                <Select

                    defaultValue={defaultStatus}
                    options={remainingOptions}
                />
            </div>
        </div>
    );
}

export default ProfileSideBar;

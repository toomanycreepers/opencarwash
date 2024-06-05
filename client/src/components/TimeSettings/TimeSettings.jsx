import DaySettings from '../DaySettings/DaySettings';
import cl from './TimeSettings.module.css';
import Button from '../UI/Button/Button';
import { useEffect, useState } from 'react';
import { toast } from 'react-toastify'

const TimeSettings = ({ clone, setClone, boxOptions }) => {
    const DAYS = ["Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"];

    return (
        <>
            <div className={cl.daysWrapper}>
                {clone.map((day, index) => (
                    <DaySettings
                        day={DAYS[day.weekday - 1]}
                        key={index}
                        index={index}
                        clone={clone}
                        setClone={setClone}
                        boxOptions={boxOptions[index]}
                    />
                ))}
            </div>
        </>
    );
};

export default TimeSettings;

import { useState } from 'react';
import CalendarBtn from '../CalendarBtn/CalendarBtn';
import cl from './WorkingDays.module.css'

const WorkingDays = ({isActive, activeDays}) => {
    const [activeDay, setActiveDay] = useState(null);

    const daysOfWeek = ["ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ", "ВС"];

    return (
        <div className={cl.wrapper}>
            <p className={cl.title}>Дни работы</p>
            <div className={cl.dayBtns}>
                {daysOfWeek.map(day => (
                    <CalendarBtn 
                        key={day} 
                        disabled={!isActive} 
                        isActive={activeDay === day}
                        setIsActive={() => setActiveDay(day)}
                    >
                        {day}
                    </CalendarBtn>
                ))}
            </div>
        </div>
    );
}

export default WorkingDays;

import { useState } from 'react';
import cl from './CalendarBtn.module.css'

const CalendarBtn = ({children, isActive, setIsActive, ...props}) => {

    return (
        <button className={!isActive ? cl.btn : cl.btn + " " + cl.active} {...props} onClick={() => setIsActive(!isActive)}>
            {children}
        </button>
    );
}

export default CalendarBtn;

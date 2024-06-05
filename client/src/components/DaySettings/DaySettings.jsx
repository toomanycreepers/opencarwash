import cl from './DaySettings.module.css';
import Switch from '../UI/Switch/Switch';
import Input from '../UI/Input/Input';
import { useRef, useState, useEffect } from 'react';
import AddPlus from '../UI/AddPlus/AddPlus';
import BoxService from '../../API/BoxService';

const DaySettings = ({ day, index, clone, setClone, boxOptions }) => {
    const dayCardRef = useRef(null);
    const [isBodyVisible, setIsBodyVisible] = useState(true);
    const [contentHeight, setContentHeight] = useState(0);

    useEffect(() => {
        if (dayCardRef.current) {
            setContentHeight(dayCardRef.current.scrollHeight);
        }
        const updatedClone = [...clone]
        updatedClone[index] = {...clone[index], openingTime: boxOptions.openingTime, closingTime: boxOptions.closingTime}
        setClone([...updatedClone])
    }, [isBodyVisible]);
    
    const switchBoxState = async () => {
        try {
            console.log(clone[index]);
            // const resp = await BoxService
        } catch {

        }
    }

    return (
        <div className={cl.dayCard}>
            <div className={cl.header}>
                <div className={cl.wrapper}>
                    {/* <Switch
                        isActive={clone[index].isClosed}
                        onClick={() => {
                            let newClone = [...clone];
                            newClone[index].isClosed = !newClone[index].isClosed;
                            setClone(newClone);
                        }}
                    /> */}
                    <p className={cl.day}>{day}</p>
                </div>
                {/* <div className={cl.opener} onClick={() => setIsBodyVisible(true)}>
                    Настройки
                    <svg width="21" height="12" viewBox="0 0 21 12" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path opacity="0.8" d="M19.6774 1.16143C20.2469 1.73092 20.2469 2.65434 19.6774 3.22382L12.5429 10.3513C11.4037 11.4894 9.5577 11.4889 8.41903 10.3504L1.28735 3.21857C0.717721 2.64909 0.717721 1.72567 1.28735 1.15616C1.85682 0.586638 2.78024 0.586638 3.34972 1.15616L9.45372 7.26019C10.0232 7.82982 10.9466 7.82967 11.5161 7.26019L17.615 1.16143C18.1845 0.591903 19.1078 0.591903 19.6774 1.16143Z" fill="#040A0A"/>
                    </svg>
                </div> */}
            </div>
            <div className={cl.body} style={{ maxHeight: isBodyVisible ? `${contentHeight + 30}px` : '0' }} ref={dayCardRef}>
                <div className={cl.periodBlock} key={clone[index].id}>
                    <Input
                        value={clone[index].openingTime}
                        style={{ width: 'clamp(500px, 45%, 700px)' }}
                        type='time'
                        onChange={(e) => {
                            let newClone = [...clone];
                            newClone[index].openingTime = e.target.value;
                            setClone(newClone);
                        }}
                    />
                    <span className={cl.p}>до</span>
                    <Input
                        value={clone[index].closingTime}
                        style={{ width: 'clamp(500px, 45%, 700px)' }}
                        type='time'
                        onChange={(e) => {
                            let newClone = [...clone];
                            newClone[index].closingTime = e.target.value;
                            setClone(newClone);
                        }}
                    />
                        {/* <button className={cl.deleteBtn}>
                            <svg width="21" height="24" viewBox="0 0 21 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M4.125 5.33333V16.6667C4.125 18.5336 4.125 19.4669 4.47244 20.18C4.77801 20.8072 5.26559 21.3171 5.86538 21.6367C6.54729 22 7.43979 22 9.225 22H11.775C13.5602 22 14.4528 22 15.1346 21.6367C15.7344 21.3171 16.222 20.8072 16.5276 20.18C16.875 19.4669 16.875 18.5336 16.875 16.6667V5.33333M19 5.33333H2M6.25 5.33333L6.53751 4.43119C6.81621 3.55694 6.95561 3.11982 7.21401 2.79664C7.44223 2.51126 7.73527 2.29036 8.06634 2.1542C8.4413 2 8.88181 2 9.76305 2H11.2369C12.1182 2 12.5587 2 12.9336 2.1542C13.2647 2.29036 13.5578 2.51126 13.786 2.79664C14.0444 3.11982 14.1838 3.55694 14.4624 4.43119L14.75 5.33333" stroke="#E75656" strokeWidth="3" strokeLinecap="round" strokeLinejoin="round"/>
                            </svg>
                        </button> */}
                    </div>
            </div>
        </div>
    );
};

export default DaySettings;

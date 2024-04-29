import CarWashCard from "../CarWashCard/CarWashCard";
import { useState } from "react";

const CarWashlist = () => {
    const [carWashes, setCarWashes] = useState([
        {
            name: '1',
            boxes: [
                {
                    name: 'Бокс 1',
                    periods: [],
                    startTime: "12:00",
                    endTime: "24:00",
                    status: true,
                },
                {
                    name: 'Бокс 2',
                    periods: [],
                    startTime: "12:00",
                    endTime: "24:00",
                    status: false
                },
            ]
        },
        {
            name: '2',
            boxes: [
                {
                    name: 'Бокс 1',
                    periods: [],
                    startTime: "12:00",
                    endTime: "24:00",
                    status: true
                },
                {
                    name: 'Бокс 2',
                    periods: [],
                    startTime: "12:00",
                    endTime: "24:00",
                    status: false
                },
            ]
        }
    ])

    return (
        <div className="carwashList">
            <CarWashCard carWashes={carWashes} />
        </div> 
    );
}

export default CarWashlist;

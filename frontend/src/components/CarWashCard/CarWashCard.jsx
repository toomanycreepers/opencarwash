import cl from './CarWashCard.module.css'
import BoxCard from '../BoxCard/BoxCard';
import { Link } from 'react-router-dom';

const CarWashCard = ({carWashes, ...props}) => {
    return (
        <>
            {carWashes.map((carWash, key) => {
                return (
                    <div key={key}>
                        <p className={cl.carWashName}>Автомойка {carWash.name}</p>
                        <div className={cl.boxWrapper}>
                            {carWash.boxes.map((box, index) => {
                                return (
                                    <Link to={`/${box.name}`}  key={index}>
                                        <BoxCard boxName={box.name} />
                                    </Link>
                                )
                            })}
                        </div>
                    </div>
                )
            })}
        </>
    );
}

export default CarWashCard;

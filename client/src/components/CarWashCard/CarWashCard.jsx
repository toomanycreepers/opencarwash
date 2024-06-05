import cl from './CarWashCard.module.css'
import BoxCard from '../BoxCard/BoxCard';
import { Link } from 'react-router-dom';

const CarWashCard = ({carwashes, ...props}) => {
    return (
        <>
            {carwashes.map((carwash, key) => {
                return (
                    <div key={key}>
                        <p className={cl.carWashName}>{carwash.street} {carwash.building}</p>
                        <div className={cl.boxWrapper}>
                            {carwash.boxes.map((box, index) => {
                                return (
                                    <Link to={`/${carwash.id}/${box.id}`} key={index}>
                                        <BoxCard boxName={`Бокс ${box.number}`} />
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

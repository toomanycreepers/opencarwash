import cl from './BoxCard.module.css'

const BoxCard = ({boxName, boxTimes, ...props}) => {
    return (
        <div className={cl.card}>
            <p className={cl.cardName} >{boxName}</p>
            <div className={cl.timeLine}>

            </div>
        </div>
    );
}

export default BoxCard;

import Checkbox from '../UI/Checkbox/Checkbox';
import cl from './OrderService.module.css'

const OrderService = ({serviceName, index, isReadOnly, handleCheckbox, isChecked}) => {
    
    return (
        <div className={cl.sevice}>
            <p className={cl.serviceName}>{serviceName}</p>
            {!isReadOnly &&
                <Checkbox isChecked={isChecked} onClick={() => handleCheckbox(index)} />
            }
        </div>
    );
}

export default OrderService;

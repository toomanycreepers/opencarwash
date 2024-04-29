import Checkbox from '../UI/Checkbox/Checkbox';
import cl from './OrderService.module.css'

const OrderService = ({serviceName, isActive, setIsActive, ...props}) => {
    return (
        <div className={cl.sevice}>
            <p className={cl.serviceName}>{serviceName}</p>
            {/* <input className={cl.checkbox} type='checkbox' /> */}
            <Checkbox isChecked={isActive} setIsChecked={setIsActive} onClick={() => setIsActive(!isActive)} />
        </div>
    );
}

export default OrderService;

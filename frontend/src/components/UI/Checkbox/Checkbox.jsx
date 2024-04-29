import cl from './Checkbox.module.css'
const Checkbox = ({isChecked, setIsChecked, ...props}) => {
    return (
        <div className={cl.checkbox} {...props}>
            {isChecked &&
                <svg width="27" height="18" viewBox="0 0 27 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M1.36328 11.6015L6.56715 16.5274C7.23279 17.1575 8.29378 17.1575 8.95942 16.5274L25.3633 1" stroke="#040A0A" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                </svg>
            }
        </div>
    );
}

export default Checkbox;

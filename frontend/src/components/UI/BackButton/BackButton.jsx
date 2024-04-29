import { Link } from 'react-router-dom';
import cl from './BackButton.module.css'

const BackButton = ({navigateTo, ...props}) => {
    return (
        
        <Link className={cl.backButton} to={navigateTo} {...props}>
            <svg width="22" height="19" viewBox="0 0 22 19" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path fillRule="evenodd" clipRule="evenodd" d="M10.6583 0.508331C11.1139 0.963949 11.1139 1.70264 10.6583 2.15825L4.48324 8.33329H20.3333C20.9777 8.33329 21.5 8.85561 21.5 9.49996C21.5 10.1443 20.9777 10.6666 20.3333 10.6666H4.48324L10.6583 16.8417C11.1139 17.2973 11.1139 18.036 10.6583 18.4916C10.2027 18.9472 9.46397 18.9472 9.00838 18.4916L0.841705 10.3249C0.62292 10.1062 0.5 9.80936 0.5 9.49996C0.5 9.19056 0.62292 8.89376 0.841705 8.67501L9.00838 0.508331C9.46397 0.0527243 10.2027 0.0527243 10.6583 0.508331Z" fill="#040A0A"/>
            </svg>
        </Link>
    );
}

export default BackButton;

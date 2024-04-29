import { Link } from 'react-router-dom';
import Button from '../UI/Button/Button';
import Input from '../UI/Input/Input';
import cl from './MainHeader.module.css'

const MainHeader = () => {
    return (
        <div className={cl.headerWrapper} >
            <Input placeholder="Поиск" style={{background: "white", marginLeft: 10}} />
            <div className={cl.headerBtnWrapper}>
                <button className={cl.bellBtn}>
                    <svg width="35" height="36" viewBox="0 0 35 36" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path opacity="0.8" d="M17.5296 4.74365C12.7025 4.74365 8.7796 8.66657 8.7796 13.4937V17.7082C8.7796 18.5978 8.40043 19.9541 7.94835 20.7124L6.27127 23.4978C5.23585 25.2186 5.95043 27.1291 7.84627 27.7707C14.1317 29.8707 20.913 29.8707 27.1984 27.7707C28.963 27.1874 29.7359 25.102 28.7734 23.4978L27.0963 20.7124C26.6588 19.9541 26.2796 18.5978 26.2796 17.7082V13.4937C26.2796 8.68115 22.3421 4.74365 17.5296 4.74365Z" stroke="#040A0A" strokeWidth="2" strokeMiterlimit="10" strokeLinecap="round"/>
                        <path opacity="0.8" d="M20.2269 5.1666C19.7748 5.03535 19.3081 4.93327 18.8269 4.87493C17.4269 4.69993 16.0852 4.80202 14.8311 5.1666C15.254 4.08743 16.304 3.3291 17.529 3.3291C18.754 3.3291 19.804 4.08743 20.2269 5.1666Z" stroke="#040A0A" strokeWidth="2" strokeMiterlimit="10" strokeLinecap="round" strokeLinejoin="round"/>
                        <path opacity="0.8" d="M21.9043 28.296C21.9043 30.7023 19.9355 32.671 17.5293 32.671C16.3334 32.671 15.2251 32.1752 14.4376 31.3877C13.6501 30.6002 13.1543 29.4919 13.1543 28.296" stroke="#040A0A" strokeWidth="2" strokeMiterlimit="10"/>
                    </svg>
                </button>
                <Link to='/profile'>
                    <div className={cl.profileWrapper}>
                        <img src='' alt='' />
                    </div>
                </Link>
            </div>
        </div>
    );
}

export default MainHeader;

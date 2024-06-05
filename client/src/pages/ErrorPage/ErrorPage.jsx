import cl from './ErrorPage.module.css'
import { Link, useNavigate } from 'react-router-dom';

const ErrorPage = () => {
    return (
        <div className={cl.bg}>    
            <div className={cl.wrapper}>
                <div className={cl.imageWrapper}>
                <svg width="246" height="303" viewBox="0 0 246 303" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path opacity="0.8" fillRule="evenodd" clipRule="evenodd" d="M0.214844 275.75L36.4534 0.857117L183.244 18.2071L245.358 102.239L218.459 302.571L0.214844 275.75ZM153.619 112.727L169.723 34.8696L53.3734 22.592L20.4098 258.147L201.529 281.394L223.413 122.574L153.619 112.727ZM72.5247 216.537C71.2635 214.797 60.0772 204.975 57.9731 201.937C87.742 167.17 160.433 167.602 180.621 213.597C177.259 214.899 162.24 221.718 159.296 223.454C150.873 197.787 101.541 189.691 72.5247 216.537ZM128.497 121.486L116.233 131.168L97.7843 107.869L72.6719 122.006L62.5392 104.224L85.3836 92.2026L70.2432 73.0775L88.6418 60.1685L103.327 82.7558L129.483 68.9873L137.365 85.5832L113.791 98.8535L128.497 121.486Z" fill="#040A0A"/>
                </svg>
                </div>
                <div className={cl.contentWrapper}>
                    <p className={cl.errorCode}>404</p>
                    <p className={cl.mainText}>Что-то пошло не так</p>
                    <p className={cl.subText}>Эта страница отсутствует или вы неправильно указали ссылку</p>
                    <Link to='/' className={cl.subText + ' ' + cl.ref}>
                        Перейти на главную страницу
                    </Link>
                </div>
            </div>
        </div>
    );
}

export default ErrorPage;
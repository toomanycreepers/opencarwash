import { useEffect, useState } from 'react';
import cl from './ErrorMessage.module.css'

const ErrorMessage = ({message, removeError, index, ...props}) => {
    const [isVisible, setIsVisible] = useState(false)

    useEffect(() => {
        setIsVisible(true)
        const timer = setTimeout(() => {
            setIsVisible(false)
            removeError(index)
        }, 3000)

        return () => clearTimeout(timer)
    }, [])

    const handleClick = () => {
        setIsVisible(false)
    }

    return (
        <div className={isVisible ? cl.errorContainer + ' ' + cl.hide : cl.errorContainer} onClick={handleClick}>
            {message}
        </div>
    );
}

export default ErrorMessage;

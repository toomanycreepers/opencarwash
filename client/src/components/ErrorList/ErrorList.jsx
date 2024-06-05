import { useEffect } from 'react';
import ErrorMessage from '../ErrorMessage/ErrorMessage';
import cl from './ErrorList.module.css'
import { observer } from 'mobx-react-lite';
import ErrorStore from '../../store/ErrorStore';

const ErrorList = observer(() => {
    const removeError = (errorIndex) => {
        setErrors(errors.filter((_, index) => index !== errorIndex))
    }

    useEffect(() => {
        console.log(errors);
    }, [errors])

    return (
        <div className={cl.errorList}>
            {ErrorStore.errors.map((error, index) => {
                return <ErrorMessage key={index} message={error} removeError={removeError} index={index} />
            })}
        </div>
    );
})

export default ErrorList;

import cl from './AddPlus.module.css'

const AddPlus = (props) => {
    return (
        <button className={cl.addBtn} {...props}>
            <svg width="15" height="15" viewBox="0 0 13 13" fill="none" xmlns="http://www.w3.org/2000/svg"><path opacity="1" fillRule="evenodd" clipRule="evenodd" d="M6.5 0C7.0156 0 7.43357 0.417975 7.43357 0.933573V5.56643H12.0664C12.582 5.56643 13 5.9844 13 6.5C13 7.0156 12.582 7.43357 12.0664 7.43357H7.43357V12.0664C7.43357 12.582 7.0156 13 6.5 13C5.9844 13 5.56643 12.582 5.56643 12.0664V7.43357H0.933573C0.417975 7.43357 0 7.0156 0 6.5C0 5.9844 0.417975 5.56643 0.933573 5.56643H5.56643V0.933573C5.56643 0.417975 5.9844 0 6.5 0Z" fill="#040A0A"/></svg>
        </button>
    );
}

export default AddPlus;

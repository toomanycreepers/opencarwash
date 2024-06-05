import cl from './SettingModal.module.css'

const SettingModal = ({isVisible, setIsVisible, name, children}) => {
    return (
        isVisible &&
            <div className={cl.bg}>
                <div className={cl.modalBody}>
                    <div className={cl.header}>
                        <p className={cl.name}>{name}</p>
                        <div className={cl.closer} onClick={() => setIsVisible(false)}>
                            <svg width="27" height="27" viewBox="0 0 27 27" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M25 2L2 25M2 2L25 25" stroke="#06060C" strokeWidth="3" strokeLinecap="round"/>
                            </svg>
                        </div>
                    </div>
                        {children}
                </div>
            </div>
        
    );
}

export default SettingModal;

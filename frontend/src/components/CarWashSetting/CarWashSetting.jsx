import cl from './CarWashSetting.module.css'

const CarWashSetting = () => {
    return (
        <div className={cl.card}>
            <p className={cl.cardName}></p>
            <div className={cl.funcWrapper}>
                <div className={cl.workload}>
                    <div></div>
                    <div></div>
                    <div></div>
                </div>
                <button className={cl.settingBtn}>

                </button>
            </div>
        </div>
    );
}

export default CarWashSetting;

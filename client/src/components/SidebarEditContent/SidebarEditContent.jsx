import cl from './SidebarEditContent.module.css'
import WorkingDays from '../UI/WorkingDays/WorkingDays'

const SidebarEditContent = ({isEditMode}) => {
    return (
        <>
            <WorkingDays isActive={isEditMode} />
            <div className={cl.statusWrapper}>
                <p className={cl.name}>Статус</p>
                
            </div>
        </>
    );
}

export default SidebarEditContent;

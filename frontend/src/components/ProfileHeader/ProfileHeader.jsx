import BackButton from '../UI/BackButton/BackButton';
import cl from './ProfileHeader.module.css'

const ProfileHeader = () => {
    return (
        <div className={cl.headerWrapper}>
            <BackButton navigateTo={'/'} style={{background: '#fff', boxShadow: '0 0 5px 1px rgba(0, 0, 0, .3)'}} />
            <div className={cl.headerInfo}>
                <p className={cl.name}>Иван Пермяков</p>
                <p className={cl.position}>Lead</p>
            </div>
            <div className={cl.profileImageWrapper}>
                <img className={cl.profileImage} src='../../assets/test.png' alt='' />
            </div>
        </div>
    );
}

export default ProfileHeader;

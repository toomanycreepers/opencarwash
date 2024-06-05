import { useNavigate } from 'react-router-dom';
import AuthStore from '../../store/AuthStore';
import Button from '../UI/Button/Button';
import cl from './ProfileBody.module.css'

const ProfileBody = () => {
    const navigate = useNavigate()

    // Геттим при загрузке всё говно отображем в цикле карточками
    const logout = async () => {
        await AuthStore.logout()
        navigate('/login')
    }

    return (
        <div className={cl.profileBody}>
            <Button onClick={logout}>Выйти</Button>
        </div>
    );
}

export default ProfileBody;

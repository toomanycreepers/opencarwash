import { useState } from 'react';
import cl from './RegPage.module.css'
import Input from '../../components/UI/Input/Input';
import { Link, useNavigate } from 'react-router-dom';
import Button from '../../components/UI/Button/Button';
import AuthStore from '../../store/AuthStore.js'

const RegPage = () => {
    const navigate = useNavigate()

    const [userData, setUserData] = useState({
        phoneNumber: '',
        firstName: '',
        lastName: '',
        password: '',
        confirmedPassword: '',
    })

    const submitRegistration = () => {
        if (!userData.phoneNumber || !userData.password || !userData.confirmedPassword || !userData.firstName || !userData.lastName) {
            console.log("All fields are required");
            return
        } 
        
        if (userData.password !== userData.confirmedPassword) {
            console.log("Passwords do not match");
            return
        }

        AuthStore.registrate(userData.lastName, userData.firstName, userData.phoneNumber, userData.password)
            .then(() => {
                if (AuthStore.isAuth)
                    navigate('/')
            })
            .catch(err => {
                console.error("Registation failed(RegPage.jsx): ", err)
            })
    }

    return (
        <div className={cl.background}>
            <div className={cl.formWrapper}>
                <div className={cl.regForm}>
                    <h2 className={cl.formHeader}>Регистрация</h2>
                    <Input 
                        style={{height: 56}} 
                        placeholder="Фамилия" 
                        value={userData.lastName} 
                        onChange={e => setUserData({...userData, lastName: e.target.value})} 
                    />
                    <Input 
                        style={{height: 56}} 
                        placeholder="Имя" 
                        value={userData.firstName} 
                        onChange={e => setUserData({...userData, firstName: e.target.value})} 
                    />
                    <Input 
                        style={{height: 56}} 
                        placeholder="Номер телефона" 
                        value={userData.phoneNumber} 
                        onChange={e => setUserData({...userData, phoneNumber: e.target.value})} 
                    />
                    <Input 
                        style={{height: 56}} 
                        placeholder="Пароль" 
                        value={userData.password} 
                        type='password'
                        onChange={e => setUserData({...userData, password: e.target.value})} 
                    />
                    <Input 
                        style={{height: 56}} 
                        placeholder="Подтверждение пароля" 
                        value={userData.confirmedPassword} 
                        onChange={e => setUserData({...userData, confirmedPassword: e.target.value})} 
                    />
                    
                    <Button onClick={() => submitRegistration()}>Продолжить</Button>
                    
                    <Link to='/login'>
                        <p className={cl.loginRef} >Войти</p>
                    </Link>
                </div>
            </div>
        </div>
    );
}

export default RegPage;

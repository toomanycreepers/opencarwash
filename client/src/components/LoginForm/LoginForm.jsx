import { useState } from 'react';
import { Link, useNavigate } from "react-router-dom";
import Input from '../UI/Input/Input';
import cl from './LoginForm.module.css'
import Button from '../UI/Button/Button';
import AuthStore from '../../store/AuthStore';
import { toast } from "react-toastify";

const LoginForm = () => {
    const navigate = useNavigate()
    const [authData, setAuthData] = useState({phoneNumber: '', password: ''})

    const submitAuth = () => {
        if (!authData.phoneNumber || !authData.password) {
            toast.error("Заполните все поля!", {
                position: "top-right"
            })
            return
        }

        AuthStore.login(authData.phoneNumber, authData.password)
            .then((response) => {
                console.log(response);
                navigate('/')
            })
            .catch(err => {
                console.error("Login failed(LoginForm.jsx): ", err)
                toast.error("Ошибка входа. Попробуйте позже.", {
                    position: "top-right"
                })

            })
    }

    return (
        <div className={cl.loginForm}>
            <h2 className={cl.formHeader}>Войти</h2>
            <Input 
                style={{height: 56}} 
                placeholder="Номер телефона" 
                value={authData.phoneNumber} 
                onChange={e => setAuthData({...authData, phoneNumber: e.target.value})} 
            />
            <Input 
                style={{height: 56}} 
                placeholder="Пароль" 
                value={authData.password} 
                onChange={e => setAuthData({...authData, password: e.target.value})} 
            />
    
            <Button onClick={() => submitAuth()}>Продолжить</Button>
            
            {/* <Link to='/registration'>
                <p className={cl.registrationRef} >Зарегистрироваться</p>
            </Link> */}
        </div>
    );
}

export default LoginForm;

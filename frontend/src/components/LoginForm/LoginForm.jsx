import { useState } from 'react';
import { Link } from "react-router-dom";
import Input from '../UI/Input/Input';
import cl from './LoginForm.module.css'
import Button from '../UI/Button/Button';

const LoginForm = () => {
    const [authData, setAuthData] = useState({login: '', password: ''})

    return (
        <div className={cl.loginForm}>
            <h2 className={cl.formHeader}>Войти</h2>
            <Input 
                style={{height: 56}} 
                placeholder="Login" 
                value={authData.login} 
                onChange={e => setAuthData({...authData, login: e.target.value})} 
            />
            <Input 
                style={{height: 56}} 
                placeholder="Password" 
                value={authData.password} 
                onChange={e => setAuthData({...authData, password: e.target.value})} 
            />
            <Link to='/'>
                <Button onClick={() => {console.log(authData)}}>Продолжить</Button>
            </Link>
            <p className={cl.registrationRef} >Зарегистрироваться</p>
        </div>
    );
}

export default LoginForm;

import LoginForm from "../../components/LoginForm/LoginForm";
import cl from './LoginPage.module.css'

const LoginPage = () => {
    return (
        <div className={cl.background}>
            <div className={cl.formWrapper}>
                <LoginForm />
            </div>
        </div>
    );
}

export default LoginPage;

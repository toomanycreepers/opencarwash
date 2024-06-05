import LoginForm from "../../components/LoginForm/LoginForm";
import cl from './LoginPage.module.css'
import { ToastContainer } from "react-toastify";

const LoginPage = () => {
    return (
        <>
            <div className={cl.background}>
                <div className={cl.formWrapper}>
                    <LoginForm />
                </div>
            </div>
            <ToastContainer />
        </>
    );
}

export default LoginPage;

import cl from './Button.module.css'

const Button = ({children, ...props}) => {
    return (
        <button className={cl.myButton} {...props}>
            {children}
        </button>
    );
}

export default Button;

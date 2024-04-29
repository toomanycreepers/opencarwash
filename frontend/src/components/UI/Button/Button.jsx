import cl from './Button.module.css'

const Button = ({props, children}) => {
    return (
        <button className={cl.myButton} {...props}>
            {children}
        </button>
    );
}

export default Button;

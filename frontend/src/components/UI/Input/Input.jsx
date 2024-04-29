import cl from './Input.module.css'

const Input = (props) => {
    return (
        <input className={cl.myInput} {...props} />
    );
}

export default Input;

import { useRef, useEffect } from 'react';
import cl from './Textarea.module.css'

const TransparentTextarea = (props) => {
    const textareaRef = useRef(null)

    useEffect(() => {
        if (textareaRef.current) {
            textareaRef.current.style.height = 'auto';
            textareaRef.current.style.height = `${textareaRef.current.scrollHeight}px`;
        }
    }, [props.value]);

    return (
        <textarea {...props} className={cl.textarea} ref={textareaRef} />
    );
}

export default TransparentTextarea;

import cl from './AddCard.module.css'

const AddCard = (props) => {
    return (
        <div className={cl.addCard} {...props}>
            <svg width="40" height="40" viewBox="0 0 40 40" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path opacity="0.8" fillRule="evenodd" clipRule="evenodd" d="M20 0C21.5865 0 22.8725 1.28608 22.8725 2.87253V17.1275H37.1275C38.7139 17.1275 40 18.4135 40 20C40 21.5865 38.7139 22.8725 37.1275 22.8725H22.8725V37.1275C22.8725 38.7139 21.5865 40 20 40C18.4135 40 17.1275 38.7139 17.1275 37.1275V22.8725H2.87253C1.28608 22.8725 0 21.5865 0 20C0 18.4135 1.28608 17.1275 2.87253 17.1275H17.1275V2.87253C17.1275 1.28608 18.4135 0 20 0Z" fill="#040A0A"/>
            </svg>
        </div>
    );
}

export default AddCard;

import cl from './Select.module.css'

const Select = ({options, defaultValue, value, ...props}) => {
    return (
        <select
            value={value}
            {...props}
            className={cl.select}
        >
				<option value={defaultValue.value}>{defaultValue.name}</option>
                {options.map(option => 
                    <option key={option.id} value={option.id}>
                        {option.name}
                    </option>
                )}
		</select>
    )
}

export default Select

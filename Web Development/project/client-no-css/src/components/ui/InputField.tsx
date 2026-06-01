
interface InputFieldProps {
	label: string;
	name: string;
	type?: string;
	value: string;
	onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
	required?: boolean;
	error?: string;
	placeholder?: string;
}

function InputField({ label, name, type = "text", value, onChange, required = false, error, placeholder }: InputFieldProps) {
	return (
		<div>
			<label htmlFor={name}>{label}</label>
			<input
				id={name}
				name={name}
				type={type}
				value={value}
				onChange={onChange}
				required={required}
				placeholder={placeholder}
			/>
			{error && <span>{error}</span>}
		</div>
	);
}

export default InputField;


interface ButtonProps {
	label: string;
	onClick?: () => void;
	variant?: "primary" | "secondary" | "danger";
	disabled?: boolean;
	type?: "button" | "submit";
}

function Button({ label, onClick, disabled = false, type = "button" }: ButtonProps) {
	return (
		<button
			onClick={onClick}
			disabled={disabled}
			type={type}
		>
			{label}
		</button>
	);
}

export default Button;

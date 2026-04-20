import "./Button.css";

interface ButtonProps {
	label: string;
	onClick?: () => void;
	variant?: "primary" | "secondary" | "danger";
	disabled?: boolean;
	type?: "button" | "submit";
}

function Button({ label, onClick, variant = "primary", disabled = false, type = "button" }: ButtonProps) {
	return (
		<button
			className={`btn btn--${variant}`}
			onClick={onClick}
			disabled={disabled}
			type={type}
		>
			{label}
		</button>
	);
}

export default Button;

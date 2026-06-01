
interface PortionScalerProps {
	portion: number;
	onChange: (n: number) => void;
}

function PortionScaler({ portion, onChange }: PortionScalerProps) {
	return (
		<div>
			<span>Portionen:</span>
			<button
				onClick={() => onChange(Math.max(1, portion - 1))}
				disabled={portion <= 1}
			>
				-
			</button>
			<span>{portion}</span>
			<button onClick={() => onChange(portion + 1)}>
				+
			</button>
		</div>
	);
}

export default PortionScaler;

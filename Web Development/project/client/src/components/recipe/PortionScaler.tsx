import "./PortionScaler.css";

interface PortionScalerProps {
  portion: number;
  onChange: (n: number) => void;
}

function PortionScaler({ portion, onChange }: PortionScalerProps) {
  return (
    <div className="portion-scaler">
      <span className="portion-scaler__label">Portionen:</span>
      <button
        className="portion-scaler__btn"
        onClick={() => onChange(Math.max(1, portion - 1))}
        disabled={portion <= 1}
      >
        -
      </button>
      <span className="portion-scaler__value">{portion}</span>
      <button className="portion-scaler__btn" onClick={() => onChange(portion + 1)}>
        +
      </button>
    </div>
  );
}

export default PortionScaler;

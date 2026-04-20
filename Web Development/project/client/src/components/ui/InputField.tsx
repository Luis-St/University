import "./InputField.css";

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
    <div className="input-field">
      <label htmlFor={name}>{label}</label>
      <input
        id={name}
        name={name}
        type={type}
        value={value}
        onChange={onChange}
        required={required}
        placeholder={placeholder}
        className={error ? "input-field__input--error" : ""}
      />
      {error && <span className="input-field__error">{error}</span>}
    </div>
  );
}

export default InputField;

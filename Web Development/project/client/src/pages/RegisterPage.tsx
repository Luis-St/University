import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { signupThunk } from "../store/slices/authSlice";
import InputField from "../components/ui/InputField";
import Button from "../components/ui/Button";
import "./RegisterPage.css";

function RegisterPage() {
	const [form, setForm] = useState({
		firstname: "",
		lastname: "",
		street: "",
		postcode: "",
		city: "",
		country: "",
		phone: "",
		email: "",
		password: "",
	});
	const dispatch = useAppDispatch();
	const navigate = useNavigate();
	const { loading, error } = useAppSelector((state) => state.auth);
	
	const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		setForm({ ...form, [e.target.name]: e.target.value });
	};
	
	const handleSubmit = async (e: React.FormEvent) => {
		e.preventDefault();
		const result = await dispatch(signupThunk(form));
		if (signupThunk.fulfilled.match(result)) {
			navigate("/");
		}
	};
	
	return (
		<div className="register-page">
			<form className="register-page__form" onSubmit={handleSubmit}>
				<h1>Registrieren</h1>
				{error && <p className="register-page__error">{error}</p>}
				<div className="register-page__row">
					<InputField label="Vorname" name="firstname" value={form.firstname} onChange={handleChange} required/>
					<InputField label="Nachname" name="lastname" value={form.lastname} onChange={handleChange} required/>
				</div>
				<InputField label="Straße" name="street" value={form.street} onChange={handleChange} required/>
				<div className="register-page__row">
					<InputField label="PLZ" name="postcode" value={form.postcode} onChange={handleChange} required/>
					<InputField label="Stadt" name="city" value={form.city} onChange={handleChange} required/>
				</div>
				<InputField label="Land" name="country" value={form.country} onChange={handleChange} required/>
				<InputField label="Telefon" name="phone" value={form.phone} onChange={handleChange} required/>
				<InputField label="E-Mail" name="email" type="email" value={form.email} onChange={handleChange} required/>
				<InputField label="Passwort" name="password" type="password" value={form.password} onChange={handleChange} required/>
				<Button label={loading ? "Laden..." : "Registrieren"} type="submit" disabled={loading}/>
				<p className="register-page__link">
					Bereits ein Konto? <Link to="/login">Einloggen</Link>
				</p>
			</form>
		</div>
	);
}

export default RegisterPage;

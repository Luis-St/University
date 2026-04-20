import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { loginThunk } from "../store/slices/authSlice";
import InputField from "../components/ui/InputField";
import Button from "../components/ui/Button";
import "./LoginPage.css";

function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const dispatch = useAppDispatch();
  const navigate = useNavigate();
  const { loading, error } = useAppSelector((state) => state.auth);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const result = await dispatch(loginThunk({ email, password }));
    if (loginThunk.fulfilled.match(result)) {
      navigate("/");
    }
  };

  return (
    <div className="login-page">
      <form className="login-page__form" onSubmit={handleSubmit}>
        <h1>Login</h1>
        {error && <p className="login-page__error">{error}</p>}
        <InputField
          label="E-Mail"
          name="email"
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <InputField
          label="Passwort"
          name="password"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <Button label={loading ? "Laden..." : "Einloggen"} type="submit" disabled={loading} />
        <p className="login-page__link">
          Noch kein Konto? <Link to="/register">Registrieren</Link>
        </p>
      </form>
    </div>
  );
}

export default LoginPage;

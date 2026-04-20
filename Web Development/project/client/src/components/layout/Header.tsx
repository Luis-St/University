import { Link, useNavigate } from "react-router-dom";
import { useAppSelector, useAppDispatch } from "../../store/hooks";
import { logoutThunk } from "../../store/slices/authSlice";
import { useState } from "react";
import "./Header.css";

function Header() {
  const { isAuthenticated } = useAppSelector((state) => state.auth);
  const dispatch = useAppDispatch();
  const navigate = useNavigate();
  const [menuOpen, setMenuOpen] = useState(false);

  const handleLogout = async () => {
    await dispatch(logoutThunk());
    setMenuOpen(false);
    navigate("/");
  };

  return (
    <header className="header">
      <div className="header__container">
        <Link to="/" className="header__logo">
          Kochbuch
        </Link>
        <button className="header__toggle" onClick={() => setMenuOpen(!menuOpen)}>
          ☰
        </button>
        <nav className={`header__nav ${menuOpen ? "header__nav--open" : ""}`}>
          <Link to="/" className="header__link" onClick={() => setMenuOpen(false)}>
            Rezepte
          </Link>
          {isAuthenticated ? (
            <>
              <Link to="/search" className="header__link" onClick={() => setMenuOpen(false)}>
                Suche
              </Link>
              <Link to="/create" className="header__link" onClick={() => setMenuOpen(false)}>
                Erstellen
              </Link>
              <Link to="/my-recipes" className="header__link" onClick={() => setMenuOpen(false)}>
                Meine Rezepte
              </Link>
              <Link to="/saved" className="header__link" onClick={() => setMenuOpen(false)}>
                Gemerkt
              </Link>
              <button className="header__link header__logout" onClick={handleLogout}>
                Logout
              </button>
            </>
          ) : (
            <>
              <Link to="/login" className="header__link" onClick={() => setMenuOpen(false)}>
                Login
              </Link>
              <Link to="/register" className="header__link" onClick={() => setMenuOpen(false)}>
                Registrieren
              </Link>
            </>
          )}
        </nav>
      </div>
    </header>
  );
}

export default Header;

import { Link, useNavigate } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "../../store/hooks";
import { logoutThunk } from "../../store/slices/authSlice";
import { useState } from "react";

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
		<header>
			<div>
				<Link to="/">
					Kochbuch
				</Link>
				<button onClick={() => setMenuOpen(!menuOpen)}>
					☰
				</button>
				<nav>
					<Link to="/" onClick={() => setMenuOpen(false)}>
						Rezepte
					</Link>
					{isAuthenticated ? (
						<>
							<Link to="/search" onClick={() => setMenuOpen(false)}>
								Suche
							</Link>
							<Link to="/create" onClick={() => setMenuOpen(false)}>
								Erstellen
							</Link>
							<Link to="/my-recipes" onClick={() => setMenuOpen(false)}>
								Meine Rezepte
							</Link>
							<Link to="/saved" onClick={() => setMenuOpen(false)}>
								Gemerkt
							</Link>
							<button onClick={handleLogout}>
								Logout
							</button>
						</>
					) : (
						<>
							<Link to="/login" onClick={() => setMenuOpen(false)}>
								Login
							</Link>
							<Link to="/register" onClick={() => setMenuOpen(false)}>
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

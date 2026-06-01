import { Navigate, Outlet } from "react-router-dom";
import { useAppSelector } from "../../store/hooks";
import LoadingSpinner from "../ui/LoadingSpinner";

function ProtectedRoute() {
	const { isAuthenticated, loading } = useAppSelector((state) => state.auth);
	
	if (loading) return <LoadingSpinner/>;
	if (!isAuthenticated) return <Navigate to="/login" replace/>;
	
	return <Outlet/>;
}

export default ProtectedRoute;

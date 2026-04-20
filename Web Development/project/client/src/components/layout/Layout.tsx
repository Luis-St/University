import { Outlet } from "react-router-dom";
import { useEffect } from "react";
import { useAppDispatch } from "../../store/hooks";
import { checkAuthThunk } from "../../store/slices/authSlice";
import { fetchCategoriesThunk } from "../../store/slices/categoriesSlice";
import Header from "./Header";
import Footer from "./Footer";
import "./Layout.css";

function Layout() {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(checkAuthThunk());
    dispatch(fetchCategoriesThunk());
  }, [dispatch]);

  return (
    <div className="layout">
      <Header />
      <main className="layout__main">
        <Outlet />
      </main>
      <Footer />
    </div>
  );
}

export default Layout;

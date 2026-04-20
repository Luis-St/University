import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { loginUser } from "../../api/loginUser";
import { signupUser } from "../../api/signupUser";
import { logoutUser } from "../../api/logoutUser";
import { fetchSavedRecipes } from "../../api/fetchSavedRecipes";
import type { LoginPayload, SignupPayload } from "../../types/AuthPayload";

interface AuthState {
  isAuthenticated: boolean;
  userEmail: string | null;
  loading: boolean;
  error: string | null;
}

const initialState: AuthState = {
  isAuthenticated: false,
  userEmail: null,
  loading: false,
  error: null,
};

export const loginThunk = createAsyncThunk(
  "auth/login",
  async (payload: LoginPayload) => {
    await loginUser(payload);
    return payload.email;
  }
);

export const signupThunk = createAsyncThunk(
  "auth/signup",
  async (payload: SignupPayload) => {
    await signupUser(payload);
    return payload.email;
  }
);

export const logoutThunk = createAsyncThunk("auth/logout", async () => {
  await logoutUser();
});

export const checkAuthThunk = createAsyncThunk("auth/check", async () => {
  const user = await fetchSavedRecipes();
  return user.email;
});

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(loginThunk.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(loginThunk.fulfilled, (state, action) => {
        state.loading = false;
        state.isAuthenticated = true;
        state.userEmail = action.payload;
      })
      .addCase(loginThunk.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message || "Login fehlgeschlagen";
      })
      .addCase(signupThunk.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(signupThunk.fulfilled, (state, action) => {
        state.loading = false;
        state.isAuthenticated = true;
        state.userEmail = action.payload;
      })
      .addCase(signupThunk.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message || "Registrierung fehlgeschlagen";
      })
      .addCase(logoutThunk.fulfilled, (state) => {
        state.isAuthenticated = false;
        state.userEmail = null;
      })
      .addCase(checkAuthThunk.fulfilled, (state, action) => {
        state.isAuthenticated = true;
        state.userEmail = action.payload;
      })
      .addCase(checkAuthThunk.rejected, (state) => {
        state.isAuthenticated = false;
        state.userEmail = null;
      });
  },
});

export default authSlice.reducer;

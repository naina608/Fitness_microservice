/**
 * Redux Store Configuration
 *
 * This file creates and exports the Redux store using Redux Toolkit.
 * The store holds the global state of the application.
 *
 * Current state structure:
 * {
 *   auth: { ... }  // Managed by authSlice
 * }
 */
import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./authSlice";
/**
 * The Redux store instance.
 *
 * - `reducer` defines different slices of the global state.
 * - `auth` is the key under which authentication state is stored.
 * - `authReducer` contains the logic for updating auth-related data.
 */
export const store = configureStore({
    reducer: {
        auth: authReducer,
    },
});
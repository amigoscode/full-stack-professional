import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import {ChakraProvider} from '@chakra-ui/react'
import {createStandaloneToast} from '@chakra-ui/toast'
import {createBrowserRouter, RouterProvider,} from "react-router-dom";
import './index.css'
import {Login} from "./components/login/Login.jsx";
import ProtectedRoute from "./components/shared/ProtectedRoute.jsx";
import AuthProvider from "./components/context/AuthContext";

const {ToastContainer} = createStandaloneToast()

const router = createBrowserRouter([
    {
        path: "/",
        element: <Login/>,
    },
    {
        path: "dashboard",
        element: <ProtectedRoute><App/></ProtectedRoute>,
    },
]);

ReactDOM
    .createRoot(document.getElementById('root'))
    .render(
        <React.StrictMode>
            <AuthProvider>
                <ChakraProvider>
                    <RouterProvider router={router}/>
                    <ToastContainer/>
                </ChakraProvider>
            </AuthProvider>
        </React.StrictMode>,
    )

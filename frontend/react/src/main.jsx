import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import {ChakraProvider} from "@chakra-ui/react";
import { createStandaloneToast } from '@chakra-ui/toast';

const { ToastContainer } = createStandaloneToast()

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <ChakraProvider>
            <App/>
            <ToastContainer/>
        </ChakraProvider>
    </React.StrictMode>,
)

import {useEffect} from 'react'
import {useAuth} from "../context/AuthContext.jsx";
import {useNavigate} from 'react-router-dom';

const ProtectedRoute = ({ children }) => {
    const { isUserAuthenticated } = useAuth()

    const navigate = useNavigate()

    useEffect(() => {
        if (!isUserAuthenticated()) {
            navigate('/')
        }
    })

    return isUserAuthenticated() ? children : "";
}

export default ProtectedRoute;
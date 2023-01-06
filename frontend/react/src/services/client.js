import axios from "axios";

export const getCustomers = async () => {
    try {
        return await axios.get(
            `${import.meta.env.VITE_API}/api/v1/customers`
        )
    } catch (e) {
        throw e
    }
}

export const saveCustomer = async (data) => {
    try {
        return await axios.post(
            `${import.meta.env.VITE_API}/api/v1/customers`,
            data
        )
    } catch (e) {
        throw e
    }
}
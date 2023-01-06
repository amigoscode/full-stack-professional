import SimpleSidebar from "./shared/Wrapper.jsx";
import Card from "./customer/Card.jsx";
import {Wrap, WrapItem} from "@chakra-ui/react";
import DrawerForm from "./customer/Drawer.jsx";
import React, {useEffect, useState} from "react";
import profilePictureGenerator from "./services/profile-picture-generator.js";
import {getCustomers} from "./services/client.js";

function App() {

    const [customers, setCustomers] = useState([]);
    const [error, setError] = useState("");
    const [isLoading, setLoading] = useState(false);

    const fetchCustomers = () => {
        setLoading(true);

        getCustomers().then(res => {
            setCustomers(res.data)
        }).catch(err => {
            console.log(err)
            setError(err);
        }).finally(() => {
            setLoading(false);
        })
    }

    useEffect(() => {
        fetchCustomers();
    }, []);

    if (error) {
        return "opps an error occurred";
    }

    if (isLoading) {
        return "loading"
    }

    return (
        <SimpleSidebar>
            <DrawerForm
                fetchCustomers={fetchCustomers}
            />
            <Wrap
                spacing='30px'
                justify='center'>
                {customers.map((customer, index) => {
                    return (
                        <WrapItem key={index}>
                            <Card
                                customer={{
                                    id: customer.id,
                                    name: customer.name,
                                    email: customer.email,
                                    age: customer.age,
                                    gender: "MALE",
                                    profilePicture: profilePictureGenerator("MALE")
                                }}
                            />
                        </WrapItem>
                    )
                })}
            </Wrap>
        </SimpleSidebar>
    )
}

export default App

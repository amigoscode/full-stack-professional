import {
    Button,
    Drawer,
    DrawerBody,
    DrawerCloseButton,
    DrawerContent,
    DrawerHeader,
    DrawerOverlay,
    useDisclosure
} from "@chakra-ui/react";
import React, {useRef} from "react";
import Form from "./Form.jsx";

function AddIcon() {
    return '+';
}

const DrawerForm = ({fetchCustomers}) => {
    const {isOpen, onOpen, onClose} = useDisclosure()

    const nameField = useRef()

    return (
        <>
            <Button leftIcon={<AddIcon/>} colorScheme='teal' onClick={onOpen}>
                Create customer
            </Button>
            <Drawer
                isOpen={isOpen}
                onClose={onClose}
                placement='right'
                initialFocusRef={nameField}
                size={"lg"}>

                <DrawerOverlay/>
                <DrawerContent>
                    <DrawerCloseButton/>
                    <DrawerHeader>Create customer</DrawerHeader>
                    <DrawerBody>
                        <Form
                            closeDrawer={onClose}
                            fetchCustomers={fetchCustomers}

                        />
                    </DrawerBody>
                </DrawerContent>
            </Drawer>
        </>
    )
}

export default DrawerForm;


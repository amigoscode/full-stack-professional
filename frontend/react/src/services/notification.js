import {createStandaloneToast} from '@chakra-ui/toast'

const {toast} = createStandaloneToast()

const notification = (title, description, status) => {
    toast({
        title,
        description,
        status,
        duration: 4000,
        isClosable: true,
    })
}

const successNotification = (title, description) => {
    notification(
        title,
        description,
        "success"
    );
}


const errorNotification = (title, description) => {
    notification(
        title,
        description,
        "error"
    );
}

export {
    successNotification,
    errorNotification
}
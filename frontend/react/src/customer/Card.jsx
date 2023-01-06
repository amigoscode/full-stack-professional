import {
    AlertDialog,
    AlertDialogBody,
    AlertDialogContent,
    AlertDialogFooter,
    AlertDialogHeader,
    AlertDialogOverlay,
    Avatar,
    Box,
    Button,
    Center,
    Flex,
    Heading,
    Image,
    Stack,
    Text,
    useColorModeValue,
    useDisclosure,
    useToast,
} from '@chakra-ui/react';

import React from "react";
import {successNotification} from "../services/notification.js";

export default function Card({ customer, fetchCustomers }) {
    const {isOpen, onOpen, onClose} = useDisclosure()
    const cancelRef = React.useRef()

    return (
        <Center py={6}>
            <Box
                minW={'300px'}
                w={'full'}
                _hover={{
                    boxShadow: 'lg',
                }}
                bg={useColorModeValue('white', 'gray.800')}
                rounded={'md'}
                overflow={'hidden'}>
                <Image
                    h={'120px'}
                    w={'full'}
                    src={
                        'https://images.unsplash.com/photo-1612865547334-09cb8cb455da?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80'
                    }
                    objectFit={'cover'}/>
                <Flex justify={'center'} mt={-12}>
                    <Avatar
                        size={'xl'}
                        src={
                            `https://user-images.githubusercontent.com/40702606/210880158-e7d698c2-b19a-4057-b415-09f48a746753.png`
                        }
                        alt={'Author'}
                        css={{
                            border: '2px solid white',
                        }}
                    />
                </Flex>
                <Box p={6}>
                    <Stack spacing={0} align={'center'}>
                        <Heading fontSize={'2xl'} fontWeight={500} fontFamily={'body'}>
                            {customer.name}
                        </Heading>
                        <Text color={'gray.500'}>{customer.email}</Text>
                        <Text fontWeight={600}>{customer.age}</Text>
                    </Stack>
                    <Stack direction={'row'} justify={'center'} spacing={6}>
                        <Stack spacing={0} align={'center'}>
                            <Button
                                w={'full'}
                                mt={8}
                                _focus={{
                                    bg: 'gray.200',
                                }}
                                rounded={'full'}
                                _hover={{
                                    transform: 'translateY(-2px)',
                                    boxShadow: 'lg',
                                }}>
                                Edit
                            </Button>
                        </Stack>
                        <Stack>
                            <Button
                                mt={8}
                                bg={'red.400'}
                                color={'white'}
                                _focus={{
                                    bg: 'grey.500',
                                }}
                                rounded={'full'}
                                _hover={{
                                    transform: 'translateY(-2px)',
                                    boxShadow: 'lg',
                                }}
                                onClick={onOpen}>
                                Delete
                            </Button>
                            <AlertDialog
                                isOpen={isOpen}
                                motionPreset='slideInBottom'
                                isCentered
                                leastDestructiveRef={cancelRef}
                                onClose={onClose}>
                                <AlertDialogOverlay>
                                    <AlertDialogContent>
                                        <AlertDialogHeader fontSize='lg' fontWeight='bold'>
                                            Wait
                                        </AlertDialogHeader>

                                        <AlertDialogBody>
                                            Are you sure you want to delete {customer.name} ? You can't undo this action afterwards.
                                        </AlertDialogBody>

                                        <AlertDialogFooter>
                                            <Button ref={cancelRef} onClick={onClose}>
                                                Cancel
                                            </Button>
                                            <Button colorScheme='red'
                                                    onClick={() => {
                                                        successNotification(
                                                            'Customer deleted.',
                                                            `${customer.name} was successfully delete`,
                                                        )
                                                        onClose();
                                                    }} ml={3}>
                                                Delete
                                            </Button>
                                        </AlertDialogFooter>
                                    </AlertDialogContent>
                                </AlertDialogOverlay>
                            </AlertDialog>
                        </Stack>
                    </Stack>
                </Box>
            </Box>
        </Center>
    );
}
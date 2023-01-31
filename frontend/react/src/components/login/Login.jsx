import {Alert, AlertIcon, Box, Button, Flex, Text, FormLabel, Image, Input, Link, Stack} from '@chakra-ui/react'
import * as React from 'react'
import {useEffect} from 'react'
import {Form, Formik, useField} from "formik";
import * as Yup from "yup";
import {useNavigate} from "react-router-dom";
import {useAuth} from "../context/AuthContext.jsx";

const MyTextInput = ({label, ...props}) => {
    // useField() returns [formik.getFieldProps(), formik.getFieldMeta()]
    // which we can spread on <input>. We can use field meta to show an error
    // message if the field is invalid and it has been touched (i.e. visited)
    const [field, meta] = useField(props);
    return (
        <Box>
            <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
            <Input className="text-input" {...field} {...props} />
            {meta.touched && meta.error ? (
                <Alert className="error" status={"error"} mt={2}>
                    <AlertIcon/>
                    {meta.error}
                </Alert>
            ) : null}
        </Box>
    );
};

const CreateCustomerForm = () => {
    let navigate = useNavigate();
    const {login} = useAuth();

    return (
        <>
            <Formik
                validateOnMount={true}
                initialValues={{
                    username: '',
                    password: '',
                }}
                validationSchema={Yup.object({
                    username: Yup.string()
                        .email('Must be 20 characters or less')
                        .required('Required'),
                    password: Yup.string()
                        .max(15, 'Must be 15 characters or less')
                        .required('Required'),
                })}
                onSubmit={(values, {setSubmitting}) => {
                    login(values).then(() => {
                        setSubmitting(true)
                        navigate('/dashboard');
                    }).catch(err => {
                        console.log(err)
                    }).finally(() => {
                        setSubmitting(false)
                    })
                }}
            >
                {({isValid, isSubmitting}) => (
                    <Form>
                        <Stack spacing={"24px"}>
                            <MyTextInput
                                label="Email"
                                name="username"
                                type="email"
                                placeholder="hello@amigoscode.com"
                            />

                            <MyTextInput
                                label="Password"
                                name="password"
                                type="password"
                                placeholder="password123"
                            />

                            <Button disabled={!isValid || isSubmitting} type="submit">Login</Button>
                        </Stack>
                    </Form>
                )}
            </Formik>
        </>
    );
}

export const Login = () => {

    const {user} = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
        if (user) {
            navigate("/dashboard")
        }
    })

    return (
        <Stack minH={'100vh'} direction={{base: 'column', md: 'row'}}>
            <Flex p={8} flex={1} align={'center'} justify={'center'}>
                <Stack spacing={4} w={'full'} maxW={'md'}>
                    <Image
                        alignSelf={"center"}
                        borderRadius='full'
                        boxSize='200px'
                        src='https://user-images.githubusercontent.com/40702606/210880158-e7d698c2-b19a-4057-b415-09f48a746753.png'
                        alt='Amigoscode'
                    />
                    <CreateCustomerForm/>
                </Stack>
            </Flex>
            <Flex flex={1}
                  p={10}
                  flexDirection={"column"}
                  alignItems={"center"}
                  justifyContent={"center"}
                  bgGradient={{ sm: 'linear(to-r, blue.600, purple.600)' }}>

                <Text fontSize={"6xl"} color={'white'} fontWeight={"bold"} mb={5}>
                    <Link href='#'>
                        Enrol now
                    </Link>
                </Text>
                <Image
                    alt={'Login Image'}
                    objectFit={'scale-down'}
                    src={
                        'https://user-images.githubusercontent.com/40702606/215539167-d7006790-b880-4929-83fb-c43fa74f429e.png'
                    }
                />

            </Flex>
        </Stack>
    );
}
import {Alert, AlertIcon, Box, Button, FormLabel, Input, Select, Stack} from "@chakra-ui/react";
import React from "react";
import {Form, Formik, useField} from "formik";
import * as Yup from "yup";
import {errorNotification, successNotification} from "../services/notification.js";
import {saveCustomer} from "../services/client.js";

const MySelect = ({label, ...props}) => {
    const [field, meta] = useField(props);
    return (
        <Box>
            <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
            <Select {...field} {...props} />
            {meta.touched && meta.error ? (
                <Alert mt={2} status='error'>
                    <AlertIcon/>
                    {meta.error}
                </Alert>
            ) : null}
        </Box>
    );
};

// And now we can use these

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
                <Alert mt={2} status='error'>
                    <AlertIcon/>
                    {meta.error}
                </Alert>
            ) : null}
        </Box>
    );
};


const CustomerForm = ({closeDrawer, fetchCustomers}) => {
    return (
        <>
            <Formik
                initialValues={{
                    name: '',
                    age: 0,
                    email: '',
                    gender: '', // added for our select
                }}
                validationSchema={Yup.object({
                    name: Yup.string()
                        .max(20, 'Must be 15 characters or less')
                        .required('Required'),
                    email: Yup.string()
                        .email('Invalid email address')
                        .required('Required'),
                    age: Yup.number()
                        .min(16, "Must be at least 16 years")
                        .max(100, 'Must not be more than 100')
                        .required('Required'),
                    gender: Yup.string()
                        .oneOf(
                            ['MALE', 'FEMALE']
                        )
                        .required('Required'),
                })}
                onSubmit={async (values, {setSubmitting}) => {
                    alert(JSON.stringify(values, null, 2));
                    try {
                        await saveCustomer(values);
                        closeDrawer()
                        successNotification(
                            "Customer added",
                            `${values.name} is now a customer`)
                        fetchCustomers()
                    } catch (err) {
                        console.log(err)
                        errorNotification(
                            err?.code,
                            err?.response?.data?.message
                        );

                    } finally {
                        setSubmitting(false);
                    }

                }}
            >
                {({
                      isSubmitting,
                      isValid,
                      dirty
                  }) => (

                    <Form>
                        <Stack spacing='24px'>
                            <MyTextInput
                                label="Name"
                                name="name"
                                type="text"
                                placeholder="Jane"
                            />

                            <MyTextInput
                                label="Email Address"
                                name="email"
                                type="email"
                                placeholder="jane@formik.com"
                            />

                            <MyTextInput
                                label="Age"
                                name="age"
                                type="number"
                                placeholder="20"
                            />

                            <MySelect label="Gender" name="gender">
                                <option value="Male or Female"></option>
                                <option value="MALE">Male</option>
                                <option value="FEMALE">Female</option>
                            </MySelect>

                            <Button disabled={isSubmitting || !isValid || !dirty} type="submit">Submit</Button>
                        </Stack>
                    </Form>
                )}
            </Formik>
        </>
    );
};

export default CustomerForm;
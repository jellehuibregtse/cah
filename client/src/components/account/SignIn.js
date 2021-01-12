import React, {useState} from 'react';
import {
    Box,
    Button,
    Center,
    FormControl,
    FormHelperText,
    FormLabel,
    IconButton,
    Input,
    InputGroup,
    InputLeftElement,
    InputRightElement,
    Stack,
    Text
} from '@chakra-ui/react';

import {InfoIcon, LockIcon, ViewIcon, ViewOffIcon} from '@chakra-ui/icons';
import Auth from './Auth';
import Validate from './Validate';

export default function SignIn() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [showPassword, setShowPassword] = useState(false);

    const isInvalid = username === '' || password === '' || password.length < 8;

    const handleSignIn = async (event) => {
        event.preventDefault();

        if (Validate.isValidPassword(password)) {
            await Auth.handleSignIn(username, password).then(result => {
                if (result !== null) {
                    localStorage.setItem('token', result);
                    document.location.href = "/";
                } else {
                    alert('Incorrect email or password!')
                }
            }).catch(result => alert(result))
        }
    };

    return (
        <Box mt='2rem'>
            <Center>
                <form method='POST' onSubmit={handleSignIn}>
                    <Stack minWidth={600} maxWidth={600} margin='1rem' spacing={5}>
                        <Text fontSize='3xl'><strong>Sign In</strong></Text>
                        <FormControl>
                            <FormLabel htmlFor='username'>Username</FormLabel>
                            <InputGroup>
                                <InputLeftElement>
                                    <InfoIcon/>
                                </InputLeftElement>
                                <Input
                                    isRequired
                                    type='username'
                                    id='username'
                                    value={username}
                                    onChange={({target}) => setUsername(target.value)}
                                />
                            </InputGroup>
                            <FormHelperText>
                                The user account username.
                            </FormHelperText>
                        </FormControl>
                        <FormControl>
                            <FormLabel htmlFor='password'>Password</FormLabel>
                            <InputGroup>
                                <InputLeftElement>
                                    <LockIcon/>
                                </InputLeftElement>
                                <Input
                                    isRequired
                                    type={showPassword ? 'text' : 'password'}
                                    id='password'
                                    value={password}
                                    onChange={({target}) => setPassword(target.value)}
                                />
                                <InputRightElement width='3rem'>
                                    <IconButton
                                        height='1.75rem'
                                        size='sm'
                                        onClick={() => setShowPassword(!showPassword)}
                                        icon={showPassword ? <ViewOffIcon/> : <ViewIcon/>}/>
                                </InputRightElement>
                            </InputGroup>
                            <FormHelperText>
                                The password you used to sign up with.
                            </FormHelperText>
                        </FormControl>
                        <FormControl>
                            <Button type='submit' disabled={isInvalid}>
                                Sign In
                            </Button>
                        </FormControl>
                    </Stack>
                </form>
            </Center>
        </Box>
    );
}
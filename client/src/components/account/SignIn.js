import React, { useState } from 'react';
import {
  Stack,
  FormHelperText,
  Button,
  IconButton,
  FormControl,
  FormLabel,
  Input,
  InputGroup,
  InputRightElement,
  InputLeftElement,
  Center,
  Text,
  Box
} from '@chakra-ui/react';

import { ViewIcon, ViewOffIcon, LockIcon, InfoIcon } from '@chakra-ui/icons';

export default function SignIn() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);

  const isInvalid = username === '' || password === '' || password.length < 8;

  const handleSignIn = (event) => {
    event.preventDefault();

    console.log('username', username);
    console.log('password', password);

    console.log('account was submitted!');
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
                  <InfoIcon />
                </InputLeftElement>
                <Input
                  isRequired
                  type='username'
                  id='username'
                  value={username}
                  onChange={({ target }) => setUsername(target.value)}
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
                  <LockIcon />
                </InputLeftElement>
                <Input
                  isRequired
                  type={showPassword ? 'text' : 'password'}
                  id='password'
                  value={password}
                  onChange={({ target }) => setPassword(target.value)}
                />
                <InputRightElement width='3rem'>
                  <IconButton
                    height='1.75rem'
                    size='sm'
                    onClick={() => setShowPassword(!showPassword)}
                    icon={showPassword ? <ViewOffIcon /> : <ViewIcon />} />
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
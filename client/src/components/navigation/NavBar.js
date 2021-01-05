import React from 'react';
import {
  Flex,
  Box,
  Heading,
  Spacer,
  Button,
  Center,
} from '@chakra-ui/react';
import { ColorModeSwitcher } from '../theme/ColorModeSwitcher';

export default function NavBar(props) {

  return (
    <Box boxShadow='sm'>
      <Box
        h='0.40rem'
        bg='blue.500' />
      <Flex
        as='nav'
        h='4.5rem'>
        <Center>
          <Heading ml='3' size='lg'>
            CAH Online
          </Heading>
        </Center>
        <Spacer />
        <Center>
          <ColorModeSwitcher mx='2' justifySelf='flex-end' />
          {!props.loggedIn ?
            <>
              <Button variant='outline' onClick={() => window.location.href = '/sign-in'} mx='1'>Sign In</Button>
              <Button colorScheme='blue' onClick={() => window.location.href = '/sign-up'} ml='1' mr='3'>Sign
                Up</Button>
            </> :
            <Button variant='outline' onClick={() => {
              localStorage.clear();
              window.location.href = '/';
            }} mx='1' mr='3'>Sign Out</Button>
          }
        </Center>
      </Flex>
    </Box>
  );
}
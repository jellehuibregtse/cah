import React from 'react';
import { Badge, Box, Button, Center } from '@chakra-ui/react';
import { ArrowForwardIcon } from '@chakra-ui/icons';

export default function (props) {
    return (
        <Box maxW='sm' borderWidth='1px' borderRadius='lg' overflow='hidden'>
            <Box p='6'>
                <Box d='flex' alignItems='baseline'>
                    <Badge borderRadius='full' px='2' colorScheme={props.started ? 'blue' : 'gray'}>
                        {props.started ? 'In Progress' : 'Not Started'}
                    </Badge>
                    <Box
                        color='gray.500'
                        fontWeight='semibold'
                        letterSpacing='wide'
                        fontSize='xs'
                        textTransform='uppercase'
                        ml='2'
                    >
                        {props.players}/{props.maxPlayers} players &bull; round {props.currentRound}/{props.rounds}
                    </Box>
                </Box>

                <Box
                    mt='1'
                    fontWeight='semibold'
                    as='h4'
                    lineHeight='tight'
                    isTruncated
                >
                    {props.creator}'s Game
                </Box>

                <Box>
                    Extra information
                </Box>

                <Center mt='3'>
                    <Button w='sm' rightIcon={<ArrowForwardIcon/>}>Join</Button>
                </Center>
            </Box>
        </Box>
    );
}

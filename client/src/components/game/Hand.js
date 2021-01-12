import React from 'react';
import Card from './Card';
import { Box, HStack } from '@chakra-ui/react';

export default function Hand(props) {
    return (
        <HStack spacing={3}{...props} ref={props.innerRef}>
            {props.items.map((item, index) => {
                return (
                    <Box>
                        <Card
                            id={item.id.toString()}
                            key={item.id.toString()}
                            index={index}
                            cardText={item.cardText}
                            cardType={item.cardType}/>
                    </Box>
                );
            })}
        </HStack>
    );
}
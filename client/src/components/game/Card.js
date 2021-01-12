import React from 'react';
import { Draggable } from 'react-beautiful-dnd';
import { Box } from '@chakra-ui/react';

export default function Card(props) {
    if (props.id === null || props.index === null || typeof props.index === 'undefined') {
        return (
            <Box
                borderWidth='1px'
                borderRadius='lg'
                width='250px'
                height='250px'
                overflow='hidden'
                bg={props.cardType === 'WHITE' ? 'white' : 'black'}
                color={props.cardType === 'WHITE' ? 'black' : 'white'}>
                <Box p='6'>
                    <Box
                        mt='1'
                        fontWeight='bold'
                        as='h3'
                        lineHeight='tight'
                    >
                        {props.cardText}
                    </Box>
                </Box>
            </Box>
        );
    } else {
        return (
            <Draggable draggableId={props.id} index={props.index}>
                {(provided) => (
                    <Box
                        {...provided.draggableProps}
                        {...provided.dragHandleProps}
                        ref={provided.innerRef}
                        borderWidth='1px'
                        borderRadius='lg'
                        width='250px'
                        height='250px'
                        overflow='hidden'
                        bg={props.cardType === 'WHITE' ? 'white' : 'black'}
                        color={props.cardType === 'WHITE' ? 'black' : 'white'}>
                        <Box p='6'>
                            <Box
                                mt='1'
                                fontWeight='bold'
                                as='h3'
                                lineHeight='tight'
                            >
                                {props.cardText}
                            </Box>
                        </Box>
                    </Box>
                )}
            </Draggable>
        );
    }
}
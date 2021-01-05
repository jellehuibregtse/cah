import React, { useEffect, useState } from 'react';
import { DragDropContext, Droppable } from 'react-beautiful-dnd';
import { Box, Flex, Text, VStack } from '@chakra-ui/react';
import Card from './Card';
import Hand from './Hand';

const CardStatus = {
    NOT_PLAYED: 'NOT_PLAYED',
    PLAYED: 'PLAYED'
};

export default function Game(props) {

    const [playedCards, setPlayedCards] = useState([]);
    const [handCards, setHandCards] = useState([]);

    useEffect(() => {
        setPlayedCards(props.cards.filter((item) => item.status === CardStatus.PLAYED));
        setHandCards(props.cards.filter((item) => item.status === CardStatus.NOT_PLAYED));
    }, [props.cards]);


    const reorder = (list, startIndex, endIndex) => {
        const result = Array.from(list);
        const [removed] = result.splice(startIndex, 1);
        result.splice(endIndex, 0, removed);

        return result;
    };

    const move = (source, destination, droppableSource, droppableDestination) => {
        const sourceClone = Array.from(source);
        const destClone = Array.from(destination);
        const [removed] = sourceClone.splice(droppableSource.index, 1);

        destClone.splice(droppableDestination.index, 0, removed);

        const result = {};
        result[droppableSource.droppableId] = sourceClone;
        result[droppableDestination.droppableId] = destClone;

        return result;
    };

    const onDragEnd = (result) => {
        const {destination, source} = result;

        if (!destination) return;

        if (destination.droppableId === source.droppableId) {
            if (destination.droppableId === 'hand') {
                setHandCards(reorder(handCards, source.index, destination.index));
            } else if (destination.droppableId === 'played') {
                setPlayedCards(reorder(playedCards, source.index, destination.index));
            }
        } else {
            if (destination.droppableId === 'hand') {
                result = move(playedCards, handCards, source, destination);
                setPlayedCards(result.played);
                setHandCards(result.hand);
            } else if (destination.droppableId === 'played') {
                result = move(handCards, playedCards, source, destination);
                setPlayedCards(result.played);
                setHandCards(result.hand);
            }
        }
    };

    return (
        <DragDropContext onDragEnd={onDragEnd}>
            <Flex>
                <VStack
                    p='6'
                    m='3'
                    align='left'
                    borderWidth='1px'
                    borderRadius='lg'>
                    <Box>
                        <Text fontSize='xs'>The black card for this round is:</Text>
                    </Box>
                    <Card cardType="BLACK"
                          cardText="When I am Prime Minister of Canada, I will create the Ministry of ____."/>
                </VStack>
                <VStack
                    p='6'
                    m='3'
                    align='left'
                    borderWidth='1px'
                    borderRadius='lg'
                    w='79.4%'>
                    <Text fontSize='xs'>The white cards played this round is:</Text>
                    <Droppable droppableId={'played'} direction="horizontal">
                        {(provided) => (
                            <Hand
                                key={1}
                                id={1}
                                name="Played"
                                items={playedCards}
                                innerRef={provided.innerRef}
                                {...provided.droppableProps}
                            >
                                {provided.placeholder}
                            </Hand>
                        )}
                    </Droppable>
                </VStack>
            </Flex>
            <Flex>
                <Box p='6'
                     m='3'
                     align='left'
                     borderWidth='1px'
                     borderRadius='lg'
                     w='100%'
                     h='300px'>
                    <Droppable droppableId={'hand'} direction="horizontal">
                        {(provided) => (
                            <Hand
                                key={2}
                                id={2}
                                name="Hand"
                                items={handCards}
                                innerRef={provided.innerRef}
                                {...provided.droppableProps}
                            >
                                {provided.placeholder}
                            </Hand>
                        )}
                    </Droppable>
                </Box>
            </Flex>
        </DragDropContext>
    );
}
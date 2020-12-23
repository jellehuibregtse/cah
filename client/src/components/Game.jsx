import React, {useEffect, useState} from "react";
import {DragDropContext, Droppable} from "react-beautiful-dnd";
import Card from "./fragments/Card";
import "../css/Card.css"
import PlayerHand from "./PlayerHand";
import GameHand from "./GameHand";
import {Button, Grid} from "@material-ui/core";

const CardStatus = {
    NOT_PLAYED: "NOT_PLAYED",
    PLAYED: "PLAYED"
};

const Game = (props) => {

    const [playedCards, setPlayedCards] = useState([]);
    const [handCards, setHandCards] = useState([]);

    useEffect(() => {
        setPlayedCards(props.cards.filter((item) => item.status === CardStatus.PLAYED));
        setHandCards(props.cards.filter((item) => item.status === CardStatus.NOT_PLAYED));
    }, [props.cards])


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
    }

    return (
        <DragDropContext onDragEnd={onDragEnd}>
            <Grid container>
                <Grid container item spacing={1}>
                    <div className="game_left_side">
                        <div className="game_black_card_wrapper">
                            <p className="ml-1">The black card for this round is:</p>
                            <Card cardType="BLACK"
                                  cardText="When I am Prime Minister of Canada, I will create the Ministry of ____."/>

                        </div>
                        <div className="justify-content-center">
                            <Button variant="contained"
                                    size="medium">Confirm selection</Button>
                        </div>
                    </div>
                    <div className="game_right_side">
                        <div className="game_right_side_box game_white_card_wrapper">
                            <span className="ml-3">The white cards played this round are:</span>
                            <div className="game_white_cards game_right_side_cards">
                                <Droppable droppableId={"played"} direction="horizontal">
                                    {(provided) => (
                                        <GameHand
                                            key={1}
                                            id={1}
                                            name="Played"
                                            items={playedCards}
                                            innerRef={provided.innerRef}
                                            {...provided.droppableProps}
                                        >
                                            {provided.placeholder}
                                        </GameHand>
                                    )}
                                </Droppable>

                            </div>
                        </div>
                    </div>
                </Grid>

                <Grid container item>
                    <div className="your_hand">
                        <Droppable droppableId={"hand"} direction="horizontal">
                            {(provided) => (
                                <PlayerHand
                                    key={2}
                                    id={2}
                                    name="Hand"
                                    items={handCards}
                                    innerRef={provided.innerRef}
                                    {...provided.droppableProps}
                                >
                                    {provided.placeholder}
                                </PlayerHand>
                            )}
                        </Droppable>
                    </div>
                </Grid>
            </Grid>
        </DragDropContext>
    );
}

export default Game;
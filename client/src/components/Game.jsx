import React, {useEffect, useState} from "react";
import {DragDropContext, Droppable} from "react-beautiful-dnd";
import Card from "./fragments/Card";
import Deck from "./Deck";
import "../css/Card.css"

const CardStatus = {
    NOT_PLAYED: "NOT_PLAYED",
    PLAYED: "PLAYED"
};

const Game = (props) => {
    const {cards} = props;

    const [items, setItems] = useState([]);
    const playedItems = items.filter((item) => item.status === CardStatus.PLAYED);
    const notPlayedItems = items.filter((item) => item.status === CardStatus.NOT_PLAYED);

    useEffect(() => {
        if (cards.length > 0) {
            setItems(cards.map((card, index) => {
                card.index = index;
                item.parentId = card.id;
                item.cardType = card.cardType;
                item.cardText = card.cardText;
                return item;
            })).flat());
        }
    }, [cards]);

    const onDragEnd = (result) => {
        const {destination, source, draggableId} = result;

        if (!destination) return;

        if (destination.droppableId === source.droppableId && destination.index === source.index) return;

        const card = cards.find((card) => card.id === items.find((item) => item.id.toString() === draggableId).parentId);

        if (destination.droppableId === 'play') {
            moveCard(card, draggableId, CardStatus.PLAYED);
        }
    }

    const moveCard = (card, draggableId, status) => {
        card.items[items.find((item) => item.id.toString() === draggableId).index].status = status;
        //MessagingService.fetchHandler("PUT", "/card-service/card/" + card.id, card).then().catch(() => {});
        setItems(cards.map((card) => card.items.map((item, index) => {
            item.index = index;
            item.parentId = card.id;
            item.cardType = card.cardType;
            item.cardText = card.cardText;
            return item;
        })).flat());
    }

    return (
        <div>
            <div className="game_left_side">
                <div className="game_black_card_wrapper">
                    <p className="game_black_card_round_indicator">The black card for this round is:</p>
                    <Card cardType="BLACK"
                          cardText="When I am Prime Minister of Canada, I will create the Ministry of ____."/>
                </div>
            </div>
            <div className="game_right_side">
                <div className="game_right_side_box game_white_card_wrapper">
                    <span tabIndex="0">The white cards played this round are:</span>
                    <div className="game_white_cards game_right_side_cards">
                        <DragDropContext onDragEnd={onDragEnd}>
                            <Droppable droppableId={"cards"}>
                                {(provided) => (
                                    <Deck
                                        key={1}
                                        id={1}
                                        name="Hand"
                                        items={notPlayedItems}
                                        innerRef={provided.innerRef}
                                        {...provided.droppableProps}
                                    >
                                        {provided.placeholder}
                                    </Deck>
                                )}
                            </Droppable>
                        </DragDropContext>
                    </div>
                </div>
            </div>
            {/*<div className="game_right_side">*/}
            {/*    <div className="game_right_side_box game_white_card_wrapper">*/}
            {/*        <span tabIndex="0">The white cards played this round are:</span>*/}
            {/*        <div className="game_white_cards game_right_side_cards">*/}
            {/*            <Card cardType="WHITE"*/}
            {/*                  cardText="The EDL."/>*/}

            {/*            <Card cardType="WHITE"*/}
            {/*                  cardText="The Big Bang."/>*/}

            {/*            <Card cardType="WHITE"*/}
            {/*                  cardText="Half a kilo of pure China White heroin."/>*/}

            {/*            <Card cardType="WHITE"*/}
            {/*                  cardText="Pauline Hanson."/>*/}

            {/*            <Card cardType="WHITE"*/}
            {/*                  cardText="Seeing my village burned and my family slaughtered before my eyes."/>*/}

            {/*            <Card cardType="WHITE"*/}
            {/*                  cardText="Some god damn peace and quiet."/>*/}

            {/*            <Card cardType="WHITE"*/}
            {/*                  cardText="Everything."/>*/}
            {/*        </div>*/}
            {/*    </div>*/}
            {/*</div>*/}
        </div>
    );
}

export default Game;
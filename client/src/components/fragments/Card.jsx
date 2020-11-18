import React from "react";
import {Draggable} from "react-beautiful-dnd";

const Card = (props) => {

    const getCardStyle = () => {
        return props.cardType === "WHITE" ? "card white_card" : "card black_card";
    }

    if (!props.id || !props.index) {
        return (
            <div className="card_holder">
                <div className={getCardStyle()}>
                    <span className="card_text">{props.cardText}</span>
                </div>
            </div>
        )
    } else {
        return (
            <Draggable draggableId={props.id} index={props.index}>
                {(provided) => (
                    <div className="card_holder"
                         {...provided.draggableProps}
                         {...provided.dragHandleProps}
                         ref={provided.innerRef}>
                        <div className={getCardStyle()}>
                            <span className="card_text">{props.cardText}</span>
                        </div>
                    </div>
                )}
            </Draggable>
        )
    }
}
export default Card;
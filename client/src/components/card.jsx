import React, {Component} from "react";

class Card extends Component {
    getCardColor() {
        const isBlackCard = true;
        let color = "card ";
        color += isBlackCard ? "black_card" : "white_card";
        return color;
    }

    render() {
        return (
            <div className="container">
                <div className={this.getCardColor()}>
                    <span className="card_text">Hey Reddit! I'm ____________________. Ask me anything.</span>
                </div>
            </div>

        );
    }
}

export default Card;
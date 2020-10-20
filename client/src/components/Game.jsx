import React, {Component} from "react";
import Card from "./Card";
import "../css/Card.css"

class Game extends Component {
    render() {
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
                            <Card cardType="WHITE"
                                  cardText="The EDL."/>

                            <Card cardType="WHITE"
                                  cardText="The Big Bang."/>

                            <Card cardType="WHITE"
                                  cardText="Half a kilo of pure China White heroin."/>

                            <Card cardType="WHITE"
                                  cardText="Pauline Hanson."/>

                            <Card cardType="WHITE"
                                  cardText="Seeing my village burned and my family slaughtered before my eyes."/>

                            <Card cardType="WHITE"
                                  cardText="Some god damn peace and quiet."/>

                            <Card cardType="WHITE"
                                  cardText="Everything."/>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Game;
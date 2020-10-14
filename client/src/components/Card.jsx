import React, {Component} from "react";

class Card extends Component {
    constructor(props) {
        super(props);

        this.state = {
            error: null,
            isLoaded: false,
            cardInformation: []
        };
    }

    componentDidMount() {
        fetch("http://localhost:8101/card/get/Hey Reddit! I'm ____________________. Ask me anything.")
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        isLoaded: true,
                        cardInformation: result
                    });
                },
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            );
    }

    getCardColor() {
        const isBlackCard = true;
        let color = "card ";
        color += isBlackCard ? "black_card" : "white_card";
        return color;
    }

    render() {
        const {error, isLoaded, cardInformation} = this.state;

        if (error) {
            return <div>Error: {error.message}</div>;
        } else if (!isLoaded) {
            return <div>Loading...</div>;
        } else {
            return (
                <div className="container">
                    <div className={this.getCardColor()}>
                        <span className="card_text">{cardInformation.cardText}</span>
                        {/*<span className="card_text">Hey Reddit! I'm ____________________. Ask me anything.</span>*/}
                    </div>
                </div>

            );
        }
    }
}

export default Card;
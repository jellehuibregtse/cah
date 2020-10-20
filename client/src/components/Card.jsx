import React, {Component} from "react";

const Card = ({cardType, cardText}) => {

    const getCardStyle = () => {
        return cardType === "WHITE" ? "card white_card" : "card black_card";
    }

    return (
        <div className="card_holder">
            <div className={getCardStyle()}>
                <span className="card_text">{cardText}</span>
            </div>
        </div>
    )
}

// class Card extends Component {
//     constructor(props) {
//         super(props);
//
//         this.state = {
//             error: null,
//             isLoaded: false,
//             cardInformation: []
//         };
//     }
//
//     componentDidMount() {
//         fetch("http://localhost:8101/api/cards/6")
//             .then(res => res.json())
//             .then(
//                 (result) => {
//                     this.setState({
//                         isLoaded: true,
//                         cardInformation: result
//                     });
//                 },
//                 (error) => {
//                     this.setState({
//                         isLoaded: true,
//                         error
//                     });
//                 }
//             );
//
//     }
//
//     getCardColor() {
//         const {cardInformation} = this.state;
//
//         const isBlackCard = cardInformation.cardType === "BLACK";
//         let color = "card ";
//         color += isBlackCard ? "black_card" : "white_card";
//         return color;
//     }
//
//     render() {
//         const {error, isLoaded, cardInformation} = this.state;
//
//         if (error) {
//             return <div>Error: {error.message}</div>;
//         } else if (!isLoaded) {
//             return <div>Loading...</div>;
//         } else {
//             return (
//                 <div className="game_black_card" tabIndex="0">
//                     <div id="card_40" className="card_holder">
//                         <div id="black_up_40" className="card blackcard">
//                                             <span className="card_text"
//                                                   aria-label={cardInformation.cardText}>{cardInformation.cardText}</span>
//
//                         </div>
//                     </div>
//                 </div>
//             );
//         }
//     }
// }
//
export default Card;
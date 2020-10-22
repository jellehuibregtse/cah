import React, {useEffect, useState} from "react";
import Card from "./Card";

const CreateCard = () => {

    const [text, setText] = useState("");
    const [color, setColor] = useState("BLACK");

    const updateText = event => {
        setText(event.target.value);
    }

    const updateColor = event => {
        setColor(event.target.value);
    }

    return (
        <div className="container mt-5">
            <div className="row justify-content-center align-items-center">
                <div className="col-8 m-auto">
                    <form>
                        <div className="form-group">
                            <label htmlFor="cardText">Card Text</label>
                            <textarea rows="8" className="form-control" id="cardText"
                                   placeholder="The text on the card" value={text} onChange={updateText}/>
                        </div>
                        <div className="form-group">
                            <label htmlFor="cardColor">Card Color</label>
                            <select className="form-control" id="cardColor"
                                    value={color} onChange={updateColor}>
                                <option>BLACK</option>
                                <option>WHITE</option>
                            </select>
                        </div>
                    </form>
                </div>
                <div className="col-2 m-auto">
                    <Card cardType={color.toString()}
                          cardText={text}/>
                </div>
            </div>
        </div>
    )
}

export default CreateCard;
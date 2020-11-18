import React, {useEffect, useState} from "react";
import Card from "./fragments/Card";
import {post} from "axios";

export default function CreateCard() {

    const [text, setText] = useState("");
    const [color, setColor] = useState("BLACK");

    const updateText = event => {
        setText(event.target.value);
    }

    const updateColor = event => {
        setColor(event.target.value);
    }

    const handleSubmit = event => {
        event.preventDefault();

        console.log("submit")

        if (text.trim() === "") {
            return false;
        }

        post('http://localhost/8101/cards/', {cardText: text, cardType: color})
            .then(response => {
                console.log(response)
            });
    }

    return (
        <div className="container mt-5">
            <div className="row justify-content-center align-items-center">
                <div className="col-8 m-auto">
                    <form onSubmit={handleSubmit} className="needs-validation">
                        <div className="form-group">
                            <label htmlFor="cardText">Card Text</label>
                            <textarea rows="8" className="form-control" id="cardText"
                                      placeholder="The text on the card" value={text} onChange={updateText}
                                      required autoFocus/>
                            <div className="invalid-feedback" style={{width: "100%"}}>
                                Card text cannot be empty.
                            </div>
                        </div>
                        <div className="form-group">
                            <label htmlFor="cardColor">Card Color</label>
                            <select className="form-control" id="cardColor"
                                    value={color} onChange={updateColor} required>
                                <option>BLACK</option>
                                <option>WHITE</option>
                            </select>
                        </div>
                    </form>
                </div>
                <div className="col-2 m-auto">
                    <Card cardType={color.toString()}
                          cardText={text}/>
                    <hr className="mb-4"/>
                </div>
            </div>
            <div className="row mt-5 justify-content-center align-items-center">
                <button className="btn btn-primary btn-lg" type="submit">Create Card</button>
            </div>
        </div>
    )
}
import React, {Component} from "react";
import Card from "./Card";

class Hand extends Component {
    render() {
        return (
            <div className="container-fluid mt-4">
                <h3 className="m-3 justify-content-start">Your hand</h3>
                <div className="row justify-content-start">
                    <div className="col-auto mb-3">
                        <Card/>
                    </div>
                    <div className="col-auto mb-3">
                        <Card/>
                    </div>
                    <div className="col-auto mb-3">
                        <Card/>
                    </div>
                    <div className="col-auto mb-3">
                        <Card/>
                    </div>
                    <div className="col-auto mb-3">
                        <Card/>
                    </div>
                    <div className="col-auto mb-3">
                        <Card/>
                    </div>
                </div>
            </div>
        );
    }
}

export default Hand;
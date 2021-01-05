import React from 'react';
import Card from './Card';

export default function GameHand(props) {
    const cards = props.items.map((item, index) => {
        return (
            <div className='col-auto mb-3'>
                <Card
                    id={item.id.toString()}
                    key={item.id.toString()}
                    index={index}
                    cardText={item.cardText}
                    cardType={item.cardType}/>
            </div>
        );
    });

    return (
        <div className='container-fluid mt-3' {...props} ref={props.innerRef}>
            {/*<h3 className="m-3 justify-content-start">{props.name}</h3>*/}
            <div className='row justify-content-start'>
                {cards}
            </div>
        </div>
    );
}
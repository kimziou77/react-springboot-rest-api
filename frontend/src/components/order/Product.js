import React from "react";

export function Product(props) {
    const id = props.id;
    const name = props.name;
    const category = props.category;
    const quantity = props.quantity;
    const imagePath = "https://storage.googleapis.com/su-image-storage/"+props.imagePath;
    const price = props.price;

    const handleAddBtnClicked = e=>{
        props.onAddClick(id);
    };

    const handleImgError = (e)=> {
        e.target.src = "https://storage.googleapis.com/su-image-storage/error.png";
    }

    return (
        <>
            <div className="col-1">
                <img src={imagePath}
                     onError={handleImgError}/>
            </div>

            <div className="col-3">
                <div className="row-cols-1">{name}</div>
            </div>

            <div className="col-2">
                <div className="row text-muted">{category}</div>
            </div>

            <div className="col-1">
                <div className="row">{quantity} 개</div>
            </div>

            <div className="col-2">{price} 원</div>

            <div className="col-2 action">
                <button onClick={handleAddBtnClicked} className="btn btn-small btn-outline-dark">추가</button>
            </div>

        </>
    )
}
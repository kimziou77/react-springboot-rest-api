import React from "react";
import {Link} from "react-router-dom";

export function Home() {
    return (
        <>
            <div className="card">
                <div className="justify-content-center m-5">
                    <h1>ITEM-SHOP</h1>
                    <br/>
                    <div>
                        <img className="storeImage" src="https://storage.googleapis.com/su-image-storage/store.png" alt="아이템"/>
                    </div>

                    <button className="btn btn-lg btn-outline-dark m-5"> <Link className="link" to="/order">주문하러가기</Link> </button>
                </div>
            </div>
        </>
    );
}

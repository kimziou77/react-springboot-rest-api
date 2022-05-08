import React from 'react';
import {Product} from "./Product";

export function ProductList({products = [], onAddClick}) {
    return (
        <React.Fragment>
            <h5 className="flex-grow-0"><b>상품 목록</b></h5>
            <ul className="list-group products">
                <li className="list-group-item d-flex mt-1">
                    <div className="col-1">
                        <div className="row-cols-1">이미지</div>
                    </div>
                    <div className="col-3">
                        <div className="row-cols-1">상품명</div>
                    </div>
                    <div className="col-2">
                        <div className="row text-muted">종류</div>
                    </div>
                    <div className="col-1">
                        <div className="row">개수</div>
                    </div>
                    <div className="col-2">가격</div>
                </li>
                {products.map(v =>
                    <li key={v.id} className="list-group-item d-flex mt-1">
                        <Product {...v} onAddClick={onAddClick}/>
                    </li>
                )}
            </ul>

        </React.Fragment>
    )
}
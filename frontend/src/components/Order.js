import React, { useState, useEffect } from "react";
import {ProductList} from "./order/ProductList";
import {Summary} from "./order/Summary";
import axios from "axios";
import {Link} from "react-router-dom";

export function Order(){
    const [products, setProducts] = useState([]);
    const [items, setItems] = useState([]);

    const handleAddClicked = id=>{
        const product = products.find(product => product.id ===id);
        const found = items.find(product=> product.id===id);
        const updatedItems = found ? items.map(product => (product.id ===id)?{ ...product, count: product.count+1}: product):[...items, {...product, count:1}]
        setItems(updatedItems);
    }

    const handleOrderSubmit = (order) =>{

        if(items.length === 0){
            alert("아이템을 추가해 주세요")
            return
        }

        const data = {
            email: order.email,
            address: order.address,
            orderProducts: items.map((item) => ({
                productId: item.id,
                count: item.count,
                totalPrice: item.price*item.count
            })),
            totalPrice: order.totalPrice
        }
        console.log('data', data);

        axios.post("http://localhost:8080/api/v1/orders", data)
            .then(v =>{alert("주문이 정상적으로 접수되었습니다.");window.location.href = '/order'},
                  e =>{alert("주문에 실패했습니다.");console.error(e);})

    }

    useEffect(()=>{
        axios.get('http://localhost:8080/api/v1/products')
            .then(product => setProducts(product.data));
    },[])


    return (
        <>
            <div className="container-fluid">
                <div className="card">
                    <div className="row">

                        <div className="col-md-8 mt-4 d-flex flex-column align-items-start p-4 pt-3">
                            <ProductList products={products} onAddClick={handleAddClicked}/>
                        </div>

                        <div className="col-md-4 summary p-4">
                            <Summary items={items} onOrderSubmit={handleOrderSubmit}/>
                        </div>

                    </div>
                </div>

            </div>
            <div className="justify-content-center m-5">
                <button className="btn btn-lg btn-outline-dark"><Link className="link" to="/">홈으로</Link></button>
            </div>
        </>
    )
}
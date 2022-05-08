import {SummaryItem} from "./SummaryItem";
import React, {useState} from 'react';

export function Summary({items = [], onOrderSubmit}) {
    const totalPrice = items.reduce((prev, curr) => prev + (curr.price * curr.count), 0);

    const [order, setOrder] = useState({
        email: "", address: "" , totalPrice:0
    });

    const handleEmailInputChanged = (e) => {
        setOrder({...order, email: e.target.value})
    }
    const handleAddressInputChanged = (e) => {
        setOrder({...order, address: e.target.value})
    }

    const handleSubmit = (e) =>{

        if (order.address ==="" || order.email === ""){
            alert("입력값을 확인해주세요!")
        }else{
            order.totalPrice =items.reduce((prev, curr) => prev + (curr.price * curr.count), 0)
            onOrderSubmit(order)
        }
    }

    return (
        <>
            <div>
                <h5 className="m-0 p-0"><b>Summary</b></h5>
            </div>
            <hr/>
            {items.map(v => <SummaryItem key={v.id} count={v.count} name={v.name} price={v.price}/>)}

            <form>
                <div className="mb-3">
                    <label htmlFor="email" className="form-label">이메일</label>
                    <input type="email" className="form-control mb-1" value={order.email}
                           onChange={handleEmailInputChanged} id="email"/>
                </div>
                <div className="mb-3">
                    <label htmlFor="address" className="form-label">주소</label>
                    <input type="text" className="form-control mb-1" value={order.address}
                           onChange={handleAddressInputChanged} id="address"/>
                </div>
                <div>당신의 정보가 서버로 날아갑니다.</div>
                <br/>
            </form>

            <div className="row pt-2 pb-2 border-top">
                <h5 className="col">총금액</h5>
                <h5 className="col text-end">{totalPrice} 원</h5>
            </div>
            <button className="btn btn-dark col-12" onClick={handleSubmit}>결제하기</button>
        </>
    )
}
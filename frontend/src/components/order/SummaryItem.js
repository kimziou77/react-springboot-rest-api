import React from "react";

export function SummaryItem({name, count, price}) {
    return (
        <div className="row">
            <h6 className="p-0">{name}
                &nbsp;&nbsp;
                <span className="badge bg-dark text-"> {count}개</span>
                <span className="text-"> : {price*count}원</span>
            </h6>
        </div>
    )
}
import React from "react";
import {Link} from "react-router-dom";
import {Counter} from "../features/counter/Counter";
import {AddPostForm} from "../features/posts/AddPostForm";

export function About() {
    return (
        <>
            <div className="card">
                <div className="justify-content-center m-5">
                    <button className="btn btn-lg btn-outline-dark m-5"> <Link className="link" to="/">Home</Link> </button>
                    <button className="btn btn-lg btn-outline-dark m-5"> <Link className="link" to="/order">Order</Link> </button>
                </div>
            </div>

            <AddPostForm />
            {/*<PostsList />*/}
            <Counter />
        </>
    );
}
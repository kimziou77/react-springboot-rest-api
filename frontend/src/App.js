import './App.css';
import 'bootstrap/dist/css/bootstrap.css'
import { Routes, Route } from "react-router-dom";
import * as PropTypes from "prop-types";
import {Order} from "./components/Order";
import {Home} from "./components/Home";
import {About} from "./components/About";
import React from "react";


Routes.propTypes = {children: PropTypes.node};

function App() {

  return (
      <div className="App">
          <div className="row justify-content-center m-5">
              <h1 className="text-center">🏴‍☠ 데부코스 풀호젝트 🏴‍☠️</h1>
          </div>
          <Routes>
              <Route path="/" element={<Home />} />
              <Route path="about" element={<About />} />
              <Route path="order" element={<Order />} />
          </Routes>

      </div>
  );
}

export default App;

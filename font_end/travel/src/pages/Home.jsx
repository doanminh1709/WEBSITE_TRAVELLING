import React, { useRef, useState } from "react";
import Footer from '../components/footer/Footer'
import Header from '../components/header/Header'
import CardSale from '../components/cardSale/CardSale'
import MeetUs from '../components/MeetUs/MeetUs'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import { Navigate, useNavigate } from 'react-router-dom'
import InfoLocation from '../components/infoLocation/InfoLocation'
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import CommentCard from '../components/comment/CommentCard'
import CardHotel from '../components/cardHotel/CardHotel'
import ListHotel from '../components/listHotel/ListHotel'
import Blogs from '../components/blogs/Blogs'

import CardInfo from '../components/cardInfo/CardInfo'
import Banner from "../components/banner/Banner";

const Home = () => {

    return (
        <div>
            <Header />
            <Banner />
            <CardSale />
            <CardHotel />
            <InfoLocation />
            <ListHotel />
            <CommentCard />
            <MeetUs />
            <Blogs />
            <Footer />

        </div>
    )
}

export default Home
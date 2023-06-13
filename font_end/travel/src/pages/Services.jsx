import Banner from '../components/banner/Banner'
import React from 'react'
import Header from '../components/header/Header'
import CardSale from '../components/cardSale/CardSale'
import ListHotel from '../components/listHotel/ListHotel'
import Footer from '../components/footer/Footer'
import CardHotel from '../components/cardHotel/CardHotel'
const Services = () => {
    return (
        <div>
            <Header />
            <Banner />
            <CardSale />
            <CardHotel />
            <ListHotel />
            <Footer />
        </div>
    )
}

export default Services
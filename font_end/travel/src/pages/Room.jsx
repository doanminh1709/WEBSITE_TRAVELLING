import ChooseRom from '../components/chooseRoom/ChooseRoom'
import React from 'react'
import Header from '../components/header/Header'
import SeeHotel from '../components/seeHotel/SeeHotel'
import Evaluate from '../components/evaluate/Evaluate'
import Footer from '../components/footer/Footer'

const Room = () => {
  return (
    <div>
      <Header />
      <SeeHotel />
      <ChooseRom />
      <Evaluate />
      <Footer />
    </div>
  )
}

export default Room
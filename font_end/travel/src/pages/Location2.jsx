
import React from 'react'
import Header from '../components/header/Header'
import Footer from '../components/footer/Footer'
import RelatedPlaces from '../components/relatedPlaces/RelatedPlaces'
import SmallLocation from '../components/smallLocation/SmallLocation'
  
const Service = () => {
  return (
    <div>
      <Header />
      <SmallLocation />
      <RelatedPlaces />
      <Footer />
    </div>
  )
}

export default Service
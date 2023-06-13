import Header from '../components/header/Header'
import Location from '../components/location/Location'
import TopPosts from '../components/topPosts/TopPosts'
import RecentPosts from '../components/recentPosts/RecentPosts'
import Footer from '../components/footer/Footer'
import React from 'react'
import Banner from '../components/banner/Banner'

const Locations = () => {
  return (
    <div>
      <Header />
      <Banner />
      <Location />
      <TopPosts />
      {/* <RecentPosts /> */}
      <Footer />

    </div>
  )
}

export default Locations

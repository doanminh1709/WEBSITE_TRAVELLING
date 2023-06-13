import React from 'react'
import Header from '../components/header/Header'
import Blogg from '../components/blog/Blogg'
import Blogs from '../components/blogs/Blogs'
import Footer from '../components/footer/Footer'
import Banner from '../components/banner/Banner'

const Blog = () => {
  return (
    <div>
      <Header />
      <Banner />
      <Blogg />
      <Blogs />
      <Footer />
    </div>
  )
}

export default Blog
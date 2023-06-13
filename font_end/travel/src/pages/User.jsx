import React, { useEffect } from 'react'
import Header from '../components/header/Header'
import User1 from '../components/user1/User1'
import Footer from '../components/footer/Footer'
import { useParams } from 'react-router-dom'
const User = (props) => {
  const { id } = useParams();
  useEffect(() => {
  }, []);
  console.log(id);
  return (
    <div>
      <Header />
      <User1 />
      <Footer />
    </div>
  )
}

export default User
import axios from 'axios'
import React, { useState } from 'react'
import { useEffect } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom'
import storageService from '../../services/storage.service'
import { dataUser1 } from './dataUser1'
import './User1.scss'
const User1 = () => {
  const { id } = useParams();
  useEffect(() => {
  }, []);
  // const [user, setUser] = useState([]);

  // const fetchUser = () => {
  //   return axios.get(`https://api-travell.herokuapp.com/api/v1/users/`)
  //     .then((response) => {
  //       // console.log(response.data);
  //       setUser(response.data)
  //     })
  // }
  const navigate = useNavigate()
  const [cus, setCus] = useState([]);
  const idUser = storageService.get('id')
  const fetchCus = () => {
    return axios.get(`https://api-travell.herokuapp.com/api/v1/users/${idUser}`)
      .then((response) => {
        // console.log(response.data);
        // const cus = response.data.find(item => item.id == 1);
        console.log(cus);
        setCus(response.data)
      })
  }
  const handleUser = () => {
    navigate(`/user/${idUser}`)
  }
  const handleUser2 = () => {
    navigate(`/user2/${idUser}`)
  }
  const handleUser3 = () => {
    navigate(`/user3/${idUser}`)
  }
  const handleUser4 = () => {
    navigate(`/user4/${idUser}`)
  }
  const handleUser5 = () => {
    navigate(`/user5/${idUser}`)
  }
  useEffect(() => {
    fetchCus()
  }, [])
  return (
    <div className='user1'>
      <div className='menu'>
        <div onClick={handleUser}><p id='user'>Quản lí tài khoản</p></div>
        <div onClick={handleUser2}><p>Đơn Phòng</p></div>
        <div onClick={handleUser3}><p>Bài viết</p></div>
        <div onClick={handleUser4}><p>Đánh giá</p></div>
        <div onClick={handleUser5}><p>Khuyến mại</p></div>
      </div>

      <div className='tvt'>

        <div className='all-info' >
          <div> <img className='img-avt' src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTm1cLph9YNyTYiTN2tlwUHeoX5fxVAkqPuUg&usqp=CAU' alt="" /> </div>
          <div className='info-user'>
            <h5>Họ tên</h5>
            <p>{cus.fullName}</p>
            <h6>Email</h6>
            <p>{cus.email}</p>
            <h6>Số điện thoại</h6>
            <p>{cus.phoneNumber}</p>
            <h6>Địa chỉ</h6>
            <p>{cus.address}</p>
            <div><button className='w-[210px] h-[44px] bg-[#FC5981] border-none hover:ease-in  hover:duration-300 hover:text-pink-500 hover:bg-stone-100  font-bold mt-7'>Lưu lại</button></div>

          </div>

        </div>


      </div>
    </div>
  )
}

export default User1
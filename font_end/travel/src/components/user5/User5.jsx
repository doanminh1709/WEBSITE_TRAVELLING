import React from 'react'
import { Link, useNavigate } from 'react-router-dom'
import storageService from '../../services/storage.service';
import './User5.scss'
const User5 = () => {
  const navigate = useNavigate();
  const idUser = storageService.get('id')
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
  return (
    <div className='user5'>
      <div className='menu'>
        <div onClick={handleUser}><p >Quản lí tài khoản</p></div>
        <div onClick={handleUser2}><p >Đơn Phòng</p></div>
        <div onClick={handleUser3}><p >Bài viết</p></div>
        <div onClick={handleUser4}><p >Đánh giá</p></div>
        <div onClick={handleUser5}><p id='user'>Khuyến mại</p></div>
      </div>
      <div className='info-user5'>
        <div className='icon-user5'><img src="https://seami.vn/wp-content/uploads/2019/05/Close_Icon_Dark-512.png" alt="" /></div>
        <p>Bạn không có khuyến mại nào</p>
      </div>
    </div>
  )
}

export default User5
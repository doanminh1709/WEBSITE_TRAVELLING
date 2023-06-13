import { ListItem } from '@mui/material'
import React from 'react'
import { Link, useNavigate } from 'react-router-dom'
import storageService from '../../services/storage.service'
import { dataUser2 } from './dataUser2'
import './User2.scss'
const User2 = () => {
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
    <div className='user2'>
      <div className='menu'>
        <div onClick={handleUser}><p >Quản lí tài khoản</p></div>
        <div onClick={handleUser2}><p id='user'>Đơn Phòng</p></div>
        <div onClick={handleUser3}><p>Bài viết</p></div>
        <div onClick={handleUser4}><p>Đánh giá</p></div>
        <div onClick={handleUser5}><p>Khuyến mại</p></div>
      </div>

      <div className='info-room'>
        {dataUser2.map((item, index) => {
          return (
            <div key={index} className="all-info-romm" >
              <div className='img-room'>
                <div><img src={item.img} alt="" /></div>
                <div><h6>{item.ten}</h6></div>
              </div>
              <div className='timeNhan'>
                <h6>Nhận phòng</h6>
                <p>{item.timeNhan}</p>
              </div>
              <div className='timeTra'>
                <h6>Trả phòng</h6>
                <p>{item.timeTra}</p>
              </div>
              <div className='all-money'>
                <h6>Tổng tiền</h6>
                <p>{item.money}</p>
              </div>
            </div>
          )
        })}
      </div>
      <div className='but'><button className='w-[120px] h-[44px] bg-[#FC5981] border-none hover:ease-in  hover:duration-300 hover:text-pink-500 hover:bg-stone-100  font-bold mt-7 '>Xem tất cả</button></div>

    </div>
  )
}

export default User2
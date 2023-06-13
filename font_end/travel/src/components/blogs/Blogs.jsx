import React from 'react'
import { dataBlogs } from './dataBlogs'
import { Link } from 'react-router-dom'
import './Blogs.scss'

const Blogs = () => {
  return (
    <div className="container-blog">
      <p className='title text-[20px]'>Cảm hứng cho những chuyến đi</p>
      <p className='desc-title'>Những câu chuyện thú vị đằng sau mỗi bài viết</p>
      <div className="list-news grid xl:grid-cols-2 md:grid-cols-1 sm:grid-cols-1 gap-3 ">
        <div className="cols-span-1">

          <div className="general-news">
            <img className='img-left' src="https://motortrip.vn/wp-content/uploads/2021/10/cam-trai-da-lat-4-1.jpg" alt="" />
            <b className='nav'>Top 12 khu cắm trại Đà Lạt có tọa độ săn mây tuyệt đỉnh</b>
          </div>
        </div>

        <div className="grid xl:grid-cols-2 xl:cols-span-1 md:grid-cols-1 md:cols-span-1 sm:grid-cols-1 sm:cols-span-1 gap-3">
          {dataBlogs.map((item, index) => {
            return (
              <div key={index} className="general-news item-news col-span-1">
                <img className='img-right' src={item.img} alt="" />
                <p className='title-right font-bold '>{item.title}</p>
              </div>
            )
          })}
        </div>
      </div>

      <div className="text-center mt-10 text-xl ">
        <Link to='/blog'>
          <button className="px-5 py-2 text-pink-400 back bg-white border-pink-400">Xem tất cả</button>
        </Link>

      </div>
    </div>
  )
}

export default Blogs
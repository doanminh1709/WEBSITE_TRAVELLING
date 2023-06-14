
import React from 'react'
import { Link } from 'react-router-dom'
import './Blog.scss'
import { dataBlog } from './dataBlog'
import { dataBlogg } from './dataBlogg'

const Blogg = () => {

  return (
    <div className='blog'>
      <div className='blogg-left'>
        <p className='heading '> Những lăng kính mới</p>
        <p className='heading-small mb-2'>Những góc nhìn mới qua từng câu chuyện</p>
        <Link to='/insideBlog'>
          <div>
            {dataBlog.map((item, index) => {
              return (
                <div key={index} className="">
                  <div className='blog-right  '>
                    <img className='imgCXG mb-4' src={item.imgCXG} alt="" />
                    <div>
                      <p className='theme-blog ml-2'>{item.title}</p>
                      <div className='line-offer '>
                        <div className='nameDS ml-2'>{item.nameDS}</div>
                        <div className='icon-time ml-5'>
                          <i class="fa-regular fa-clock"></i>
                          <p className='time-ac ml-1'>{item.time}</p>
                        </div>
                        <div className='icon-ac ml-5'>
                          <i class="fa-regular fa-comment"></i>
                          <p className='ml-1'>{item.amountCmt}</p>
                        </div>
                      </div>
                      <p className='cmt-blog ml-2'>{item.cmt}</p>
                    </div>
                  </div>
                </div>
              )
            })}
          </div>
        </Link>

      </div>

      <div className='blog-info-right'>
        <div >
          <p className='posts-outstanding mb-2'>Bài viết nổi bật</p>
          <div className='posts-outstanding-p'>
            <img className='posts-outstanding-img mb-4' src="https://dulichviet.net.vn/wp-content/uploads/2019/09/ho-guom.jpg" alt="" />
            {/* <p> Gợi ý 5 trải nghiệm thú vị tại Hà Nội cho ngày cuối tuần đẹp trời thư giãn</p> */}
          </div>
        </div>

        <div className='notable'>
          <p className='theme-new mb-2'>Bài viết mới đăng</p>
          <div className='theme-new-img-p'>
            <img className='theme-new-img mb-2' src="https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/09/anh15-9.png" alt="" />
            {/* <p>Điểm danh những công viên nước hot nhất hè 2022 tại Việt Nam</p> */}
          </div>
          {dataBlogg.map((item, index) => {
            return (
              <div key={index} className='info-new mb-3'>
                <img className='imgNew mt-2' src={item.imgNew} alt="" />
                <div className='ml-2'>
                  <p className='themeNew mt-2'>{item.themeNew}</p>
                  <div className='icon-date mt-2'>
                    <i class="fa-solid fa-pen "></i>
                    <p className='dateNew'>{item.dateNew}</p>
                  </div>
                </div>
              </div>
            )
          })}
        </div>

      </div>
    </div>
  )
}

export default Blogg
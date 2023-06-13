import React from 'react'
import {dataUserMeetUs} from './dataMeetUs'
import './MeetUs.scss'
import { imgs } from '../assets/img'
const MeetUs = () => {
  return (
    <div className='meet-us'>
        <div className="container-comment">
            <h2 className="title-content">
                Gặp gỡ chúng tôi
            </h2>
            <div className="grid  xl:grid-cols-5 sm:grid-cols-1 md:grid-cols-3 gap-5">
                {dataUserMeetUs.map((item, index) => {
                    return (
                        <div key={index} className="user-comment xl:col-span-1 sm:col-span-1 lg:col-span-1 ">
                            <img className='img-cover' src={item.imgCover} alt="" />
                            <img className='img-avt' src={item.imgAvt} alt="" />
                            <p className="name-user">{item.name}</p>
                            <p className="comment-user">{item.comment}</p>
                            <a href={item.linkFB}>
                                <i class="fa-brands fa-facebook icon-social"></i>
                            </a>
                            <a href={item.linkIns}>
                                <i class="fa-brands fa-instagram icon-social"></i>
                            </a>
                            <a href="#">
                                <i class="fa-brands fa-twitter icon-social"></i>
                            </a>
                        </div>
                    )
                })}
              </div>
              
              <p className="intro-company">
                Trải qua nhiều năm hoạt động, Hello Travel đã không ngừng phát triền kinh doanh trên lĩnh vực Du lịch, nâng cao chất lượng, đa dạng hoá các hạng mục dịch vụ, nhằm mục đích cao nhất là làm hài lòng mọi yêu cầu của Quý khách hàng. Chúng tôi tin tưởng rằng với uy tín đã tạo được trên thị trường, cùng với sự định hướng và chỉ đạo của Ban lãnh đạo Công ty TNHH Du Lịch và Dịch Vụ HelloTravel luôn đem đến cho khách hàng những sản phẩm, dịch vụ đạt chất lượng tốt nhất với giá cả cạnh tranh. Nguyên tắc hoạt động kinh doanh của HelloTravel là: “Sự hài lòng của Quý khách luôn là tiêu chí hàng đầu của HelloTravel Cùng với đội ngũ cán bộ, nhân viên đầy kinh nghiệm, chúng tôi hi vọng HelloTravel đã và sẽ là địa chỉ lựa chọn các dịch vụ du lịch đáng tin cậy hàng đầu của Quý khách
              </p>
        </div>
    </div>
  )
}

export default MeetUs
import React from 'react'
import './CommentCard.scss'
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from 'react-slick'
import { dataComment } from './dataComment';
import { Stack, Rating } from '@mui/material'
import { Link } from 'react-router-dom';
const CommentCard = () => {
    const settings = {
        dots: true,
        infinite: false,
        speed: 500,
        slidesToShow: 4,
        slidesToScroll: 1,
        initialSlide: 0,

        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 1,
                    infinite: true,
                    dots: true
                },
            },
            {
                breakpoint: 600,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 1,
                    initialSlide: 2
                },
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1
                },
            }
        ]
    };
    return (
        <div className='comment-container xl:pl-32 xl:pr-32 ' >
            <h1 h1 className='font-bold text-3xl pt-5 mb-5' > Khách hàng nói gì về chúng tôi</h1 >
            <Slider {...settings} >
                {dataComment.map((item) => (
                    <div className='cardComment '>
                        <div className='cardComment-item pl-3 pr-1 ' >
                            <h1 className='font-bold text-4xl ml-3 '>"</h1>
                            <div className='cardComment-item_cmt ml-3 mr-2 mb-5 font-medium  h-[160px]'>{item.cmt}</div>
                            <div className='cardComment-item_cus flex justify-around '>
                                <div >
                                    <img className='rounded-[50%]' src={item.linkImg} alt="" />
                                </div>
                                <div className='cardComment-item_cus--info mt-12 '>
                                    <h1 className='font-bold mb-2'>{item.title}</h1>
                                    <Rating className=''
                                        name="size-small" defaultValue={5} size="small" />
                                    <h2 className='font-bold'>{item.work}</h2>
                                </div>
                            </div>
                        </div>

                    </div>
                ))}
            </Slider>
            <div className="text-center mt-10 text-xl ">
                <Link to='/blog'>
                    <button className="px-5 pt-1 py-2 text-pink-400 back bg-white border-pink-400 ">Xem thêm</button>
                </Link>

            </div>

        </div >
    )
}

export default CommentCard
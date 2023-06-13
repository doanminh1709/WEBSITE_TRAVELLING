import React, { useEffect, useState } from 'react'
import Slider from 'react-slick'
import { dataHotel } from './dataHotel'
import './CardHotel.scss'
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import { Stack, Rating } from '@mui/material'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faLocation, faLocationDot, faUmbrellaBeach } from '@fortawesome/free-solid-svg-icons'
import { Link } from 'react-router-dom'
import axios from 'axios';
const CardHotel = () => {

    const [hotel, setHotel] = useState([]);

    const fetchHotel = () => {
        return axios.get("https://api-travell.herokuapp.com/api/v1/hotels")
            .then((response) => {
                // console.log(response.data);
                setHotel(response.data)
            })
    }

    useEffect(() => {
        fetchHotel()
    }, [])
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
                breakpoint: 800,
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
        <>
            <div className='slickHotel xl:pl-32 xl:pr-32'>
                <div className='slickHotel_title'>
                    <h1 className='text-3xl pt-1 font-bold '>Khách sạn đang thịnh hành</h1>
                    <h2 className='mt-1 mb-2 font-normal text-base'>Các khách sạn được tìm kiếm & đặt nhiều nhất do HelloTravelHelloTravel đề xuất</h2>
                </div>

                <Slider {...settings} >
                    {hotel && hotel.map((item, index) => (
                        <div className='cardHotel'>
                            <div className='cardHotel-item '>
                                <div className='cardHotel-top '>
                                    <img src={dataHotel[index].linkImg} />
                                    <div className=' h-[55px] flex'>
                                        <Link to={`/hotel/${item.id}`} className='text-black text-lg pl-2 font-bold hover:ease-in  hover:duration-300 hover:text-blue-400'>
                                            {item.name}
                                        </Link>
                                    </div>
                                    <Rating className='pl-2'
                                        name="size-small" defaultValue={5} size="small" />
                                </div>
                                <div className='cardHotel-bottom pl-2  '>
                                    <div className='flex mb-2'>
                                        <div className='mr-2 text-sm'><FontAwesomeIcon icon={faLocationDot} className='text-sm' /></div>
                                        <div><h3 className='text-[16px] font-medium'>{item.location.address}</h3></div>

                                    </div>
                                    <div className='flex'>
                                        <button className='mr-1 mb-2 h-6 w-14 bg-slate-200 border-neutral-100 text-pink-400'><FontAwesomeIcon icon={faUmbrellaBeach} /> {dataHotel[index].point}</button>
                                        <h3 className=''>{dataHotel[index].cmt}</h3>
                                    </div>
                                    <span className='bg-slate-200 border-neutral-100 rounded-sm text-blue-500 text-sm'>{dataHotel[index].time}</span>
                                    {/* <h2 className="flex justify-end  line-through  font-thin right-0 mr-2 text-sm">{dataHotel[index].price}</h2>
                                    <h1 className='flex justify-end text-base font-medium mr-2 '>{dataHotel[index].pay}</h1> */}
                                    <div>
                                        <div className=' flex justify-between border-dashed border-2 rounded-md border-slate-200  p-1 mr-2 mt-7'>
                                            <div>
                                                <h2 className='text-sm'>Mã: <span className='text-sm text-blue-400'>DONNHE <span className='bg-blue-500 w-10 border-none text-white px-2 py-0.5 rounded-[5px]'>-5%</span></span></h2>
                                            </div>
                                            <div>
                                                {/* <h1 className='font-medium text-lg text-pink-400 '>{dataHotel[index].pay}</h1> */}
                                            </div>
                                        </div>
                                    </div>


                                </div>

                            </div>
                        </div>
                    ))
                    }
                </Slider>


            </div >

        </>
    )
}

export default CardHotel
import React, { useState, useEffect } from 'react'
import Slider from "react-slick";
import { dataBestSeller } from './data';
import './CardSale.scss'
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import { Stack, Rating } from '@mui/material'
import { imgs } from '../assets/img'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faLocation, faLocationDot, faUmbrellaBeach } from '@fortawesome/free-solid-svg-icons'
import axios from 'axios';
import { Link } from 'react-router-dom';
import numberWithCommas from '../../utils/number'

const Card = () => {

    const [arr, setArr] = useState([]);

    const fetchData = () => {
        return axios.get("https://api-travell.herokuapp.com/api/v1/rooms")
            .then((response) => {
                // console.log(response.data);
                setArr(response.data.slice(0, 10))
            })
    }

    useEffect(() => {
        fetchData()
    }, [])

    console.log(arr)
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
                    slidesToScroll: 2,
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
    console.log(dataBestSeller)

    return (
        <>
            <div className='slickSale xl:pl-32 xl:pr-32 sm:pl-5'>
                <div className='slickSale_img pt-3 pb-5 ml-2'>
                    <img style={{ height: 64, marginTop: 15 }} src={imgs.imgSale} alt="" />
                </div>

                <Slider {...settings} >

                    {arr && arr.map((item, index) => (

                        <div className='cardSale'>
                            <div className='cardSale-item  '>
                                <div className='cardSale-top '>

                                    <img src={dataBestSeller[index].linkImg} />
                                    <div className=' h-[53px] flex '>
                                        <Link to={`/hotel/${item.hotel.id}`} className='text-black text-lg hover:ease-in  hover:duration-300 hover:text-blue-400 font-bold'>{item.name}</Link>
                                    </div>


                                    <Rating className='pl-2'
                                        name="size-small" defaultValue={5} size="small" />
                                </div>
                                <div className='carSale-bottom pl-2  '>
                                    <div className='flex h-[40px]'>
                                        <div className='mr-2 text-[17px]'><FontAwesomeIcon icon={faLocationDot} /></div>
                                        <div>
                                            {item.hotel.name}

                                        </div>

                                    </div>
                                    <div className='flex'>
                                        <button className='mr-1 mb-2 h-6 w-14 bg-slate-200 text-[14px] border-neutral-100 text-pink-400'><FontAwesomeIcon icon={faUmbrellaBeach} /> {dataBestSeller[index].point}</button>
                                        <h4 className='text-[16px]'>{dataBestSeller[index].cmt}</h4>
                                    </div>
                                    <span className='bg-slate-200 text-sm border-neutral-100 rounded-sm text-blue-500'>{dataBestSeller[index].time}</span>
                                    <div className='flex justify-between items-end '>
                                        <button className='h-[38px] w-[98px] p-1  bg-blue-500 rounded-500 text-[16px] font-semibold  border-none'>Đã đặt: {dataBestSeller[index].bought}</button>
                                        <div className='mr-4 '>
                                            <h3 className="justify-end line-through mt-3">{numberWithCommas(item.price)}.đ</h3>
                                            <h1 className='flex justify-end text-lg font-medium text-pink-400'>???</h1>
                                        </div>

                                    </div>

                                </div>

                            </div>
                        </div>
                    ))
                    }
                </Slider>
                <div className="text-center mt-7 text-xl ">
                    <Link to='/services'>
                        <button className="px-4 pb-1 h-[40px] mt-2 text-pink-400 back bg-pink-100 border-pink-400 hover:ease-in  hover:duration-300 hover:text-blue hover:bg-white">Xem thêm</button>
                    </Link>

                </div>

            </div >

        </>

    )
}

export default Card
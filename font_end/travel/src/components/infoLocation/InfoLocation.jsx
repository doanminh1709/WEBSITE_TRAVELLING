import React from 'react'
import './InfoLocation.scss'
import { imgs } from '../assets/img'
import { dataLocation } from './dataLocation'
import { Link } from 'react-router-dom'
const InfoLocation = () => {
    return (
        <div className='infoLocation '>
            <div className='infoLocation__content '>
                <div className='infoLocation__content_header'>
                    <div className='infoLocation__content_header-title'>
                        <h1 className='xl:text-3xl xl:font-bold md:font-semibold md:text-2xl sm:text-xl sm:font-semibold'>Các điểm đến hấp dẫn</h1>
                        <h2 className='xl:text-base md:text-lg sm:text-xs'>Các địa điểm du lịch hàng đầu do HelloTravel đề xuất </h2>
                    </div>
                    <div className='infoLocation__content_header-btn '>
                        <Link to='location'>
                            <button className=" mr-5  text-pink-400 back bg-white border-pink-400 ">Khám phá ngay</button>
                        </Link>

                    </div>

                </div>

                <div className='infoLocation__content_main lg:grid lg:grid-cols-4 lg:gap-4 lg:grid-rows-4 md:grid md:grid-cols-3 md:grid-rows-2 md:gap-5 md:mt-5 sm:mt-3 sm:grid '>
                    <div className='hover:shadow-2xl  infoLocation__content_main-pic lg:h-full lg:row-span-2 lg:max-h-[390px]  lg:max-w-[285px]  md:max-h-[250px]  '>
                        <button className='h-[30px] w-[100px] mt-2 ml-2 absolute  bg-pink-500 border-pink-500'>{dataLocation.haNoi}</button>
                        <img src={imgs.img1} alt="" className='w-full h-full '></img>
                    </div>
                    <div className=' hover:shadow-2xl infoLocation__content_main-pic xl:h-full xl:row-span-1 lg:max-h-[187px] lg:max-w-[285px]   md:max-h-[250px] '>
                        <button className='mt-2 ml-2 absolute h-[30px] w-[100px] bg-pink-500 border-pink-500'>{dataLocation.daNang}</button>
                        <img src={imgs.img2} alt="" className='w-full h-full '></img>
                    </div>
                    <div className='hover:shadow-2xl infoLocation__content_main-pic lg:h-full lg:row-span-2 lg:max-h-[390px]  lg:max-w-[285px]  md:max-h-[250px]'>
                        <button className='mt-2 ml-2 absolute h-[30px] w-[100px] pb-1 bg-pink-500 border-pink-500'>{dataLocation.hoChiMinh}</button>
                        <img src={imgs.img3} alt="" className='w-full h-full '></img>
                    </div>
                    <div className='hover:shadow-2xl infoLocation__content_main-pic  xl:row-span-1 lg:max-h-[187px] lg:max-w-[285px]   md:max-h-[250px]'>
                        <button className='mt-2 ml-2 absolute h-[30px] w-[100px] bg-pink-500 border-pink-500'>{dataLocation.daLat}</button>
                        <img src={imgs.img4} alt="" className='w-full h-full '></img>
                    </div>
                    <div className='hover:shadow-2xl infoLocation__content_main-pic  xl:row-span-1 lg:max-h-[187px] lg:max-w-[285px]  md:max-h-[250px] '>
                        <button className='mt-2 ml-2 absolute h-[30px] w-[100px] bg-pink-500 border-pink-500'>{dataLocation.phuQuoc}</button>
                        <img src={imgs.img5} alt="" className='w-full h-full '></img>
                    </div>
                    <div className='hover:shadow-2xl infoLocation__content_main-pic  xl:row-span-1 lg:max-h-[187px] lg:max-w-[285px]  md:max-h-[250px] '>
                        <button className='mt-2 ml-2 absolute h-[30px] w-[100px] bg-pink-500 border-pink-500'>{dataLocation.hoiAn}</button>
                        <img src={imgs.img6} alt="" className='w-full h-full '></img>
                    </div>
                </div>
            </div>

        </div>
    )
}

export default InfoLocation
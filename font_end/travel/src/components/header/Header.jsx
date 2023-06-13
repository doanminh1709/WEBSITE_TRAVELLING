import React, { useState } from 'react';
import { animateScroll as scroll, } from 'react-scroll'
import { MenuIcon, XIcon } from '@heroicons/react/outline';
import './Header.scss'
import { Link, useNavigate, useParams } from 'react-router-dom'
import { dataHeader } from './dataHeader';
import storageService from '../../services/storage.service';
import axios from 'axios';
import { useEffect } from 'react';
import { API } from '../../modules/Auth/const/const.api'
const Navbar = () => {
    const [nav, setNav] = useState(false)
    const handleClick = () => setNav(!nav)

    const handleClose = () => setNav(!nav)
    const navigate = useNavigate()

    const handleLogoutSystem = () => {
        storageService.set('isLogin', false)
        const isLogin = storageService.get('isLogin')
        if (isLogin === 'false') {
            storageService.remove('accessToken')
            navigate('/login')
            window.history.replaceState({}, "/");
            storageService.remove('isLogin')
        }
    }



    const idUser = storageService.get('id')
    // console.log(idUser);
    const handleUser = () => {
        navigate(`/user/${idUser}`)
    }
    // const isLogin = storageService.getItem('isLogin')
    const isLogin = storageService.get('isLogin')
    // console.log(isLogin);
    const [show, setShow] = useState(false)

    // const { id } = useParams();
    // useEffect(() => {
    //     console.log(id);

    // }, []);

    const [user, setUser] = useState([]);
    const fetchUser = () => {
        return axios.get(`https://api-travell.herokuapp.com/api/v1/users/${idUser}`)
            .then((response) => {
                // console.log(response.data);
                setUser(response.data)
            })
    }

    useEffect(() => {
        fetchUser()
    }, [])

    // const [cus, setCus] = useState([]);
    // const fetchCus = () => {
    //     return axios.get(`https://api-travell.herokuapp.com/api/v1/users/${user.id}`)
    //         .then((response) => {
    //             // console.log(response.data);
    //             setUser(response.data)
    //         })
    // }
    // useEffect(() => {
    //     fetchCus()
    // }, [])
    // console.log(user.id);
    return (
        <div className='header'>
            {/* <div>
                <video className='vid' src='/videos/beach_-_3998 (Original).mp4' autoPlay loop muted />
            </div> */}
            <div className='w-screen md:h-[80px] lg:h-[56px] z-10 bg-zinc-200 fixed drop-shadow-lg '>
                <div className=' flex justify-between items-center w-full h-full px-12'>
                    <div className='flex items-center '>
                        <h1 className='text-3xl font-bold xl:ml-10 sm:text-3xl'>

                            HelloTravel</h1>

                    </div>
                    <div>
                        <ul className=' md:flex text-lg font-normal '>
                            {dataHeader.map((item) => (
                                <li className=''><Link to={item.linkPage} onClick className='text-black font-medium hover:text-blue-500'>{item.title}</Link></li>
                            ))}
                        </ul>
                    </div>
                    <div className='hidden md:flex lg:hidden xl:block '>
                        {
                            isLogin ?
                                <>
                                    <div className='icon-user mr-12 font-bold text-2xl flex mb-1' onClick={() => setShow(!show)} >  <i class="fa-solid fa-user"></i></div>
                                    {
                                        show && <>
                                            <div className=' h-[110px] w-[110px] absolute border-white bg-white  rounded-sm'>
                                                <h1 className=' px-4 py-1 border-b-2 cursor-pointer hover:bg-violet-100' onClick={handleUser}>Tài khoản</h1>
                                                <h1 className=' px-4 py-1 border-b-2 cursor-pointer hover:bg-violet-100' onClick={handleLogoutSystem}>Đăng xuất</h1>
                                                <h1 className='flex justify-center cursor-pointer hover:bg-violet-100'> <Link to='/changepass ' className='text-black'>Đổi mật khẩu</Link> </h1>
                                            </div></>
                                    }
                                    {/* <button>Đổi mật khẩu</button> */}

                                </>
                                :
                                <>

                                    <Link to='/login'>
                                        <button className='border-none bg-transparent text-black mr-4 font-medium text-lg hover:ease-in  hover:duration-200'>
                                            Đăng nhập
                                        </button>
                                    </Link>
                                    <Link to='/resgister'>
                                        <button className='px-8 py-2 font-medium bg-blue-500 text-lg hover:ease-in  hover:duration-300 border-none'>Đăng ký</button>
                                    </Link>
                                </>
                        }



                    </div>
                    <div className='md:hidden mr-4 lg:hidden' onClick={handleClick}>
                        {!nav ? <MenuIcon className='w-5' /> : <XIcon className='w-5' />}
                    </div>
                </div>

                <ul className={!nav ? 'hidden' : 'absolute bg-zinc-200 w-full px-8'}>
                    {dataHeader.map((item) => (
                        <li className='text-black font-medium border-b-2 border-zinc-300 w-full '>{item.title}</li>
                    ))}

                    <div className='flex flex-col my-4'>
                        {
                            isLogin ? <button onClick={handleLogoutSystem}>Logout</button> :
                                <><Link to='\login'><button className='bg-transparent text-indigo-600 px-8 py-3 mb-4 text-base'>Đăng nhập</button></Link>

                                    <Link to='\register'><button className='px-8 py-3 text-base'>Đăng Ký</button></Link></>
                        }


                    </div>
                </ul>
            </div>
        </div>

    );
};

export default Navbar;

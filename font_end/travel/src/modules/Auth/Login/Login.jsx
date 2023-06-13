import React, { useState, useEffect } from 'react'
import './Login.scss'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faXmark } from '@fortawesome/free-solid-svg-icons'
import { Link, useNavigate } from 'react-router-dom'
import { GoogleLogin } from 'react-google-login'
import { gapi } from 'gapi-script'
import { useFormik } from 'formik';
import axios from 'axios'
import { API } from '../const/const.api'
import storageService from '../../../services/storage.service'
import { id } from 'date-fns/locale'
const Login = () => {
    const navigate = useNavigate();
    const clientId = '36536591056-nqetopeqi2e466uk4ujl7dh8e73m9n73.apps.googleusercontent.com'
    const onSuccess = (res) => {
        console.log("LOGIN SUCCESS ! Current user: ", res.profileObj);
        alert('Đăng nhập thành công')
    }
    const onFailure = (res) => {
        console.log("LOGIN FAILED! res: ", res);
    }
    useEffect(() => {
        function start() {
            gapi.client.init({
                clientId: clientId,
                scope: ""
            })
        }
        gapi.load('client:auth2', start)
    })

    const formik = useFormik({
        initialValues: {
            username: '',
            password: ''
        },
        onSubmit: async (values) => {

            // alert(JSON.stringify(values, null, 2));
            try {
                const resData = await axios.post(`${API}auth/login`, values)
                console.log(resData);
                alert('Đăng nhập thành công')

                if (resData.status === 200) {
                    navigate('/')
                    storageService.set("accessToken", resData.data.jwt)
                    storageService.set('isLogin', true)
                    storageService.set('username', resData.data.username)
                    storageService.set('id', resData.data.id)
                    // storageService.set('fullName', resData.data.fullName)
                    // storageService.set('email', resData.data.email)
                    // storageService.set('phoneNumber', resData.data.phoneNumber)
                }
            } catch (err) {
                console.log(err)
                alert('Fail')
            }
        },
    });
    // const [data, setData] = useState([]);
    // const fetchData = () => {
    //     return axios.post("https://api-travell.herokuapp.com/api/v1/users/create")
    //         .then((response) => console.log(response.data));
    // }
    // console.log(data)
    // fetchData()

    return (
        <div className='login'>
            <div className='login_main'>
                <div className='login_main-img'>
                    <img src="https://images.unsplash.com/photo-1654896888033-a12b0ee910b6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwxN3x8fGVufDB8fHx8&auto=format&fit=crop&w=500&q=60" alt="" />
                </div>
                <div className='login_main-content'>
                    <div className='login_main-content__signin'>
                        <Link to='/'>
                            <div className='flex justify-end' >
                                <div className='login_main-content__signin-close text-black'>
                                    <FontAwesomeIcon icon={faXmark} />
                                </div>
                            </div>
                        </Link>
                        <h1 className='text-3xl font-bold'>Hello Travell</h1>

                        <div>
                            <GoogleLogin
                                clientId={clientId}
                                buttonText='Đăng nhập bằng Google'
                                onSuccess={onSuccess}
                                onFailure={onFailure}
                                cookiePolicy={'single_host_origin'}
                                isSignedIn={false}
                                className='h-[44px] w-[312px] mt-5 mb-5 bg-white text-black border-neutral-300 ' >
                            </GoogleLogin>
                        </div>
                        <h1 className='text-base mb-5'>Hoặc đăng nhập bằng tài khoản có sẵn</h1>
                        <div >
                            <form onSubmit={formik.handleSubmit}>

                                <input
                                    id="username"
                                    name="username"
                                    type="username"
                                    onChange={formik.handleChange}
                                    value={formik.values.username}
                                    placeholder='Nhập tài khoản'
                                    className='inputFormik mb-2 hover:ease-in  hover:duration-300'
                                />
                                <input
                                    id="password"
                                    name="password"
                                    type="password"
                                    onChange={formik.handleChange}
                                    value={formik.values.password}
                                    placeholder='Nhập mật khẩu '
                                    className='inputFormik'
                                />
                                <br />
                                <br />
                                <button className='hover:ease-in  hover:duration-300 btn-signin bg-[#FC5981] hover:bg-[#9ec0e2] hover:text-black ' type="submit">Đăng nhập</button>
                            </form>
                            <div className='mt-3'>
                                <Link to='/pass'><span className='text-[#00B6F3]'>Quên mật khẩu</span></Link></div>
                            <div className='mt-2'>Bạn chưa có tài khoản
                                <Link to='/resgister'>
                                    <span className='text-[#00B6F3] font-bold'>Đăng kí</span>
                                </Link> </div>
                        </div>

                    </div>

                </div>
            </div>
        </div>
    )
}

export default Login
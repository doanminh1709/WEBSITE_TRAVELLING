import React from 'react'
import './ForgotPassword.scss'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faXmark } from '@fortawesome/free-solid-svg-icons'
import { Link, useNavigate } from 'react-router-dom'
import { useFormik } from 'formik';
import axios from 'axios'
import { API } from '../const/const.api'

const ForgotPassword = () => {
    const formik = useFormik({
        initialValues: {
            username: '',
        },
        onSubmit: async (values) => {
            //alert(JSON.stringify(values, null, 2));
            try {
                console.log(values.username);
                const resData = await axios.delete(`${API}auth/resetPassword/${values.username}`)
                console.log(resData);
                alert('Thay đổi mật khẩu thành công')
            } catch (err) {
                console.log(err)
                alert('Fail')
            }
        },
    });
    const navigate = useNavigate();
    return (
        <div className='forgot'>
            <div className="forgot_main">
                <div className="forgot_main-img">
                    <img src="https://images.unsplash.com/photo-1654896888033-a12b0ee910b6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwxN3x8fGVufDB8fHx8&auto=format&fit=crop&w=500&q=60" alt="" />
                </div>
                <div className='forgot_main-content'>
                    <div className='forgot_main-content__pass'>
                        <Link to='/'>
                            <div className='flex justify-end' >
                                <div className='login_main-content__signin-close text-black'>
                                    <FontAwesomeIcon icon={faXmark} />
                                </div>
                            </div>
                        </Link>
                        <h1 className='text-3xl font-bold'>Hello Travell</h1>
                        <h1 className='mt-3'>Xác nhận để lấy lại mật khẩu</h1>
                        <div className='mt-5'>
                            <form onSubmit={formik.handleSubmit}>
                                <input
                                    id="username"
                                    name="username"
                                    type="text"
                                    onChange={formik.handleChange}
                                    value={formik.values.username}
                                    placeholder='Nhập tài khoản cần xác thực'
                                    className='inputForgot'
                                />

                                <button className='btn-forgot bg-[#FC5981] hover:bg-[#9ec0e2] hover:text-black' type="submit">Xác thực</button>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    )
}

export default ForgotPassword
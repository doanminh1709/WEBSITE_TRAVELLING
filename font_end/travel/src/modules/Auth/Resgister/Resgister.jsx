import React, { useState } from 'react'
import { useFormik } from 'formik';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faXmark } from '@fortawesome/free-solid-svg-icons'
import { Link, useNavigate } from 'react-router-dom'
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './Resgister.scss'
import userService from "../../../services/user.service.js"
import axios from 'axios';
import { API } from '../const/const.api';


const validate = (values) => {

    const errors = {};
    // if (!values.userName) {
    //     errors.userName = "Vui lòng nhập tên người dùng ";
    // } else if (values.userName.length < 8) {
    //     errors.userName = "Tên tài khoản ít nhất 8 ký tự!";
    // }
    // if (!values.password) {
    //     errors.password = "Vui lòng nhập mật khẩu";
    // } else if (
    //     !/(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/.test(
    //         values.password
    //     )
    // ) {
    //     errors.password =
    //         "Mật khẩu phải chứa ít nhất 8 ký tự, 1 ký tự in Hoa, 1 ký tự đặc biệt, 1 ký tự số";
    // }
    if (!values.email) {
        errors.email = 'Required';
    } else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(values.email)) {
        errors.email = 'Invalid email address';
    }
    return errors;
}


const Resgister = () => {
    const [type, setType] = useState("password");
    const navigate = useNavigate()
    const formik = useFormik({
        initialValues: {
            account: '',
            password: '',
            confirmPassword: '',
            fullName: '',
            email: '',
            phoneNumber: '',
            address: ''

        },
        validate,
        onSubmit: async (values) => {
            if (values.account === '' || values.password === '' || values.confirmPassword === '' || values.fullName === '' || values.email === '' || values.phoneNumber === '') {
                alert('Vui lòng điền đầy đủ thông tin')
            }
            else if (values.password !== values.confirmPassword) {
                alert('Mật khẩu xác thực không khớp')
            }

            else {
                try {
                    // console.log(values);
                    // userService.resgister(values).then(data => {
                    //     console.log(data)
                    // })
                    const result = await axios.post(`${API}users`, {
                        username: values.account,
                        password: values.password,
                        fullName: values.fullName,
                        email: values.email,
                        phoneNumber: values.phoneNumber,
                        address: values.address
                    })

                    if (result.status === 200) {
                        alert('Đăng kí thành công')
                        navigate('/login')
                    }
                }
                catch (err) {
                    console.log(err);
                }
            }
        },
    });

    return (
        <div className='signup'>
            <div className='signup_main'>
                <div className="signup_main-img">
                    <img src="https://images.unsplash.com/photo-1654896888033-a12b0ee910b6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwxN3x8fGVufDB8fHx8&auto=format&fit=crop&w=500&q=60" alt="" />
                </div>
                <div className='signup_main-content'>
                    <div className='signup_main-content__box'>
                        <Link to='/'>
                            <div className='flex justify-end' >
                                <div className='login_main-content__signin-close text-black'>
                                    <FontAwesomeIcon icon={faXmark} />
                                </div>
                            </div>
                        </Link>
                        <h1 className='text-3xl font-bold'>Hello Travell</h1>
                        <h1 className='mb-2'>Đăng ký tài khoản</h1>
                        <div>

                            <form onSubmit={formik.handleSubmit}>
                                <input
                                    id="account"
                                    name="account"
                                    type="text"
                                    onChange={formik.handleChange}
                                    value={formik.values.account}
                                    className='inputSignup '
                                    placeholder='Nhập tài khoản'
                                />

                                {formik.errors.account ? <div>{formik.errors.account}</div> : null}
                                <br />
                                <input
                                    id="password"
                                    name="password"
                                    type="password"
                                    onChange={formik.handleChange}
                                    value={formik.values.password}
                                    className='inputSignup'
                                    placeholder='Nhập mật khẩu'
                                    onBlur={formik.handleBlur}

                                />
                                {formik.errors.password ? <div>{formik.errors.password}</div> : null}
                                <br />
                                <input
                                    id="comfirmPassword"
                                    name="confirmPassword"
                                    type={type}
                                    onChange={formik.handleChange}
                                    value={formik.values.confirmPassword}
                                    className='inputSignup'
                                    placeholder='Xác nhận mật khẩu'
                                />

                                {type === "password" ? (
                                    <i class="fa-solid fa-eye" onClick={() => setType("text")}
                                        className="icon-toggle-password"></i>
                                ) : (

                                    <i class="fa-solid fa-eye-slash" onClick={() => setType("password")}
                                        className="icon-toggle-password"></i>
                                )}
                                <input
                                    id="fullName"
                                    name="fullName"
                                    type="text"
                                    onChange={formik.handleChange}
                                    value={formik.values.fullName}
                                    className='inputSignup'
                                    placeholder='Họ và Tên'
                                />

                                <input
                                    id="email"
                                    name="email"
                                    type="email"
                                    onChange={formik.handleChange}
                                    value={formik.values.email}
                                    className='inputSignup'
                                    placeholder='Email'

                                />
                                <input
                                    id="address"
                                    name="address"
                                    type="text"
                                    onChange={formik.handleChange}
                                    value={formik.values.address}
                                    className='inputSignup'
                                    placeholder='Địa chỉ'

                                />

                                <input
                                    id="phoneNumber"
                                    name="phoneNumber"
                                    type="text"
                                    onChange={formik.handleChange}
                                    value={formik.values.phoneNumber}
                                    className='inputSignup'
                                    placeholder='Số điện thoại'
                                />

                                <br />

                                <button className='hover:ease-in  hover:duration-300 btn-signup bg-[#FC5981] hover:bg-[#9ec0e2] hover:text-black ' type="submit">Đăng ký</button>
                            </form>
                        </div>
                        <h1 className='mt-5'>Bạn đã có tài khoản ? <Link to='/login'><span className='text-[#00B6F3] font-bold'>Đăng nhập</span></Link> </h1>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Resgister
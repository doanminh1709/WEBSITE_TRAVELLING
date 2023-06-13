import React from 'react'
import "./Footer.scss"

const Footer = () => {
  
  return (
    <footer className='total'>
      <div className='footer'>
        <div className='footer__top'>
          <b className='hello_travel'>HelloTravel</b>
          <div className='footer__img'>
            <img src="https://ik.imagekit.io/tvlk/image/imageResource/2017/12/13/1513150198216-822560165b4cfa5d5ac17a7987028b03.svg?tr=h-35,q-75" alt="" />
            <img src="https://ik.imagekit.io/tvlk/image/imageResource/2017/12/13/1513150313470-072f6bdc02c0b73fcf791aa2b2264fbd.svg?tr=h-35,q-75" alt="" />
            <img src="https://ik.imagekit.io/tvlk/image/imageResource/2017/12/13/1513150321127-5096be77d2a19401b476853e54ba2cc6.svg?tr=h-35,q-75" alt="" />
            <img src="https://ik.imagekit.io/tvlk/image/imageResource/2021/05/10/1620638808154-e6c02ed786235ab59252628a9aa9b715.png?tr=h-35,q-75" alt="" />
            <img src="https://ik.imagekit.io/tvlk/image/imageResource/2021/05/10/1620639321776-9db1bf99b0d4ff46db160c7a02b0536f.png?tr=h-35,q-75" alt="" />
            <img src="https://ik.imagekit.io/tvlk/image/imageResource/2019/09/23/1569229181629-eeb038ad844874f951326d0a8534bf48.png?tr=q-75,w-100" alt="" />
            <img src="https://ik.imagekit.io/tvlk/image/imageResource/2019/09/23/1569229181629-eeb038ad844874f951326d0a8534bf48.png?tr=q-75,w-100" alt="" />        
          </div>
        </div>
        
        <div className='footer__left'>
          <div>
            <b>About Hello Travel</b>
            <p>How to Book</p>
            <p>Contact Us</p>
            <p>Help Center</p>
            <p>Careers</p>
            <p>About Us</p>
          </div>
          <div className='footer__left__icon'>
            <b>Follow us on</b>
            <div>
              <div>
                <i class="fa-brands fa-twitter"></i>
                <a href="#">Twitter</a>
              </div>
              <div>
                <i class="fa-brands fa-facebook"></i>
                <a href="#">Facebook</a>
              </div>
              <div>
                <i class="fa-brands fa-instagram"></i>
                <a href="#">Instagram</a>
              </div>
              <div>
                <i class="fa-brands fa-youtube"></i>
                <a href="#">Youtube</a>
              </div>        
            </div>
          </div>
        </div>
        
        <div className='footer__between'>
          <b>Products</b>
          <p>Flights</p>
          <p>Hotels</p>
          <p>Flight + Hotel</p>
          <p>Xperience</p>
          <p>Car Rental</p>
          <p>Villas</p>
          <p>Aparytments</p>
        </div>

        <div className='footer__right'>
          <b>Other</b>
          <p>HelloTravel Afiliate</p>
          <p>Blog</p>
          <p>Privacy Policy</p>
          <p>Terms & Conditions</p>
          <p>Regulation for operation</p>
          <p>Register Your Accommodation</p>
          <p>Register Your Experience Business</p>
          <p>HelloTravel Press Room</p>
        </div>

      </div>
      
      <div className='bott'>
        <p className='bott__p'>HelloTravel là thành viên của VNTravel Group<span className='bottom'> - Một trong những tập đoàn đứng đầu Đông Nam Á về du lịch trực tuyến và các dịch vụ liên quan</span></p>
        <p><strong > <span className='bottom'> Copyright 2022 - </span>CÔNG TY CỔ PHẦN DU LỊCH VIỆT NAM HELLOTRAVEL</strong> <span className='str'>- Đăng kí kinh doanh số 0000000 - do Sở kế hoạch và Đầu tư thành phố Hà Nội cấp lần đầu ngày 25 tháng 05 năm 2022</span></p>
      </div>
    </footer>
    
  )
}

export default Footer






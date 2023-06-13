

import Resgister from "../modules/Auth/Resgister/Resgister";
import Login from "../modules/Auth/Login/Login";
import Home from "../pages/Home";
import { Navigate } from "react-router-dom";
import ForgotPassword from "../modules/Auth/forgot-password/ForgotPassword";
import Locations from "../../src/pages/Locations";
import ChangePass from "../modules/Auth/ChangePass/ChangePass";
import Room from "../../src/pages/Room";
import Blog from "../../src/pages/Blog";
import InsideBlog from "../../src/pages/InsideBlog";
import PickRoom from "../pages/PickRoom";
import Contact from "../pages/Contact";
import Services from "../pages/Services";
import User from '../pages/User';
import UserRoom from '../pages/UserRoom';
import UserPosts from '../pages/UserPosts';
import UserAssess from '../pages/UserAssess.jsx';
import UserPromotion from '../pages/UserPromotion.jsx';
import Location2 from "../pages/Location2";
const publicRoutes = [
    { path: '/', component: Home },
    { path: '/login', component: Login },
    { path: '/resgister', component: Resgister },
    { path: '/pass', component: ForgotPassword },
    { path: '/location', component: Locations },
    { path: '/changepass', component: ChangePass },
    { path: '/hotel/:id', component: Room},
    { path: '/blog', component: Blog },
    { path: '/insideBlog', component: InsideBlog},
    { path: '/blog', component: Blog},
    { path: '/bookRoom/:id', component: PickRoom},
    { path: '/contact', component: Contact},
    { path: '/services', component: Services},
    { path: '/location2', component: Location2},
    { path: '/user/:id', component: User },
    { path: '/user2/:id', component: UserRoom },
    { path: '/user3/:id', component: UserPosts},
    { path: '/user4/:id', component: UserAssess },
    { path: '/user5/:id', component: UserPromotion},
]; 


const privateRoutes = [];

export { publicRoutes, privateRoutes };
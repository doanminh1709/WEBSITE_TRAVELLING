// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import {getFireStore} from 'firebase/firestore'
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyD1wiW2unSCrb3X2j2hkHcKjkmVJ-AVMN8",
  authDomain: "producttravel-b0b98.firebaseapp.com",
  projectId: "producttravel-b0b98",
  storageBucket: "producttravel-b0b98.appspot.com",
  messagingSenderId: "771138909701",
  appId: "1:771138909701:web:c21ccc704e3244a05d8e6c"
};

// Initialize Firebase
 initializeApp(firebaseConfig);

const db =  getFireStore();
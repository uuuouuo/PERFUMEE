import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://j6c101.p.ssafy.io:8081",
  headers: {
    "Content-type": "application/json",
  },
});

export default axiosInstance;

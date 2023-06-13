import axios from "axios";
import storageService from "./storage.service";

class HttpService {
  async get(uri, options = { headers: {}, params: {}, body: {} }) {
    return await this.request("GET", uri, options);
  }

  async post(uri, options = { headers: {}, params: {}, body: {} }) {
    return await this.request("POST", uri, options);
  }

  async patch(uri, options = { headers: {}, params: {}, body: {} }) {
    return await this.request("PATCH", uri, options);
  }

  async delete(uri, options = { headers: {}, params: {}, body: {} }) {
    return await this.request("DELETE", uri, options);
  }

  async request(method, uri, options = { headers: {}, params: {}, body: {} }) {
    return await axios.request({
      method: method,
      baseURL: "https://api-travell.herokuapp.com/api/v1/",
      url: uri,
      headers: this.generateHttpHeaders(options.headers),
      params: options.params,
      data: options.body,
    });
  }

  generateHttpHeaders(headerInfo) {
    const headers = {
      "Content-Type": "application/json",
      Authorization: `Bearer ${storageService.get("accessToken")}`,
    };

    if (headerInfo) {
      for (const item of Object.keys(headerInfo)) {
        headers[item] = headerInfo[item];
      }
    }
    return headers;
  }
}

export default HttpService;
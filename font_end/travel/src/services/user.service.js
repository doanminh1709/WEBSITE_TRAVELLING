import HttpService from "./http-service";
class UserService extends HttpService {
  async getAllUser(page) {
    try {
      const response = await this.get(`/users`, {
        params: {
          page: page,
        },
      });

      return response.data;
    } catch (error) {
      throw new Error(error.message);
    }
  }
  async addUser({ username, phone, email, fullName, roleId }) {
    try {
      const response = await this.post(`/users`, {
        body: {
          username,
          phone,
          email,
          fullName,
          avatar: null,
          address: "Ha noi",
          roleId,
        },
      });

      return response.data;
    } catch (error) {
      throw new Error(error.message);
    }
  }

  async changeUser(
    payload,
    { username, phone, email, fullName, avt, address, roleId }
  ) {
    try {
      const response = await this.patch(`/users/${payload}`, {
        body: {
          username,
          phone,
          email,
          fullName,
          address,
          roleId
        },
      });

      return response.data;
    } catch (error) {
      throw new Error(error.message);
    }
  }
  async deleteUser(payload) {
    try {
      const response = await this.delete(`/users/${payload}`);

      return response.data;
    } catch (error) {
      throw new Error(error.message);
    }
  }
  async getUserInfo(payload) {
    try {
      const response = await this.get(`/users/${payload}`);

      return response.data;
    } catch (error) {
      throw new Error(error.message);
    }
  }
  async changePassword({newPassword, oldPassword}) {
    try {
      const response = await this.patch(`users/changePassword`, {
        body: {
          newPassword,
          oldPassword
        }
      });

      return response.data;
    } catch (error) {
      throw new Error(error.message);
    }
  }
  async register({username, password, phoneNumber,fullName,email}) {
    try {
      const response = await this.post(`users`, {
        body: {
          username,
          password,
          phoneNumber,
          fullName,
          email
        }
      });

      return response.data;
    } catch (error) {
      throw new Error(error.message);
    }
  }
}

export default new UserService();

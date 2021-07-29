import axios from "axios";

const API_URL = "https://localhost:8443/";

class AuthService {
  login(user) {
    return axios
      .post(API_URL + "ind/login", {
        username: user.username,
        password: user.password,
      })
      .then((response) => {
        if (response.data.accessToken) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
  }

  register(user) {
    return axios.post(API_URL + "ind", {
      username: user.username,
      email: user.email,
      password: user.password,
    });
  }
}

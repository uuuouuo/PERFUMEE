import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  const request = {
    id: user["id"],
    password: user["password"],
  };
  await api
    .post(`/user/login`, JSON.stringify(request))
    .then(success)
    .catch(fail);
}

async function findById(id, success, fail) {
  await api.get(`user/${id}`).then(success).catch(fail);
}
export { login, findById };

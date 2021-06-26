export default function authHeader() {
  let user = JSON.parse(localStorage.getItem('authId'));
  if (user) {
    return {Authorization: 'Bearer ' + user};
  } else {
    return {};
  }
}

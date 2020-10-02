function Logout() {
    localStorage.clear();
    window.location.assign("/login");

    return null;
}

export default Logout;
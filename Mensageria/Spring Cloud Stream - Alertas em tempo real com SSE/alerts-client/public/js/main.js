var eventSource;
var basicHeaderValue;

createBasicHeader = (username, password) => {
    return "Basic " + btoa(username + ":" + password);
};

login = ()=> {
    console.log('login');
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    basicHeaderValue = createBasicHeader(usernameInput.value, passwordInput.value);
};

logout = ()=> {
    console.log('logout');
    if (eventSource) {
        eventSource._close();
    }
    const eventList = document.querySelector('ul');
    eventList.textContent = '';
};


watch = ()=> {
    console.log('watch');
    eventSource = new EventSourcePolyfill(
        'http://localhost:8080/user/me/alerts',
        {
            headers: {
                'Authorization': basicHeaderValue
            }
        }
    )
    eventSource.onmessage = (e) => {
        const eventList = document.querySelector('ul');
        const newElement = document.createElement("li");
        newElement.textContent = `message: ${e.data}`;
        eventList.appendChild(newElement);
    }
};

const btnLogin = document.getElementById('login');
btnLogin.addEventListener("click", () => {
    login();
    watch();
});

const btnLogout = document.getElementById('logout');
btnLogout.addEventListener("click", () => {
    logout();
});
function getLocalIpAddress() {
    fetch('/login', {
        method: 'POST',
    })
        .then(response => response.text())
        .then(ipAddress => {
            document.getElementById('ipAddress').value = ipAddress;
        })
        .catch(error => {
            console.error('Error getting local IP address:', error);
        });
}
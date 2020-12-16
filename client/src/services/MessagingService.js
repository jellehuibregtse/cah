export default class MessagingService {
    static async fetchHandler(method, uri, content) {
        let result = null;

        await fetch(process.env.REACT_APP_GATEWAY_URL + uri, {
            method: method,
            body: JSON.stringify(content),
            headers: {
                'Content-Type': 'application/json',
                'accept': '*/*',
                'Accept-Encoding': 'gzip, deflate, br',
                'Connection': 'keep-alive',
                'Authorization': localStorage.getItem('token')
            }

        })
            .then((response) => {
                if (response.ok) {
                    return response.text();
                } else {
                    throw new Error(response.status + " " + response.statusText);
                }
            })
            .then((res) => {
                try {
                    result = typeof JSON.parse(res) === 'object' && JSON.parse(res) !== null ? JSON.parse(res) : res;
                } catch (e) {
                    result = res;
                }
            })
            .catch(error => {
                throw new Error(error.message);
            });
        return result;
    }
}
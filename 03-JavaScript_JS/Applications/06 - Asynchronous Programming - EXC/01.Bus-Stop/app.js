async function getInfo() {
    // const res = await fetch(url);
    // const data = await res.json();
    
    // read input value
    // make request to server
    // parse response data
    // display data
    //* error checking for request
    const stopNameElement = document.getElementById('stopName');
    const timeTableElement = document.getElementById('buses');

    const stopId = document.getElementById('stopId').value;

    const url = `http://localhost:3030/jsonstore/bus/businfo/${stopId}`;

    try {
        timeTableElement.replaceChildren();
        
        const res = await fetch(url);
        if (res.status != 200) {
            throw new Error('Stop ID not found');
        }

        const data = await res.json();
        
        stopNameElement.textContent = data.name;

        Object.entries(data.buses).forEach(b => {
            const liElement = document.createElement('li');
            liElement.textContent = `Bus ${b[0]} arrives in ${b[1]} minutes`;

            timeTableElement.appendChild(liElement);
        })
    } catch (error) {
        stopNameElement.textContent = 'Error';
    }


    
}
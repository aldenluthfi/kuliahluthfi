async function getProducts() {
    return fetch("/debug/json").then((res) => res.json())
}

async function refreshSalts() {
    const datatable = document.getElementById("datatable")
    datatable.innerHTML = ""
    const products = await getProducts();
    let counter = 0;
    let htmlString = `
        <col/>
        <col/>
        <col/>
        <col/>
        <col/>
        <tr>
    `

    products.forEach((item) => {
        if (counter < 10) {
            if (counter % 5 == 0) {
                htmlString += `
                </tr>
                <tr>
                `
            }
            htmlString += `
            <td>
                <img src="../static/images/vector/${(parseInt(item.fields.sha256sum, 16) % 22) + 1}.svg"/>
                <button class="clickable delete-button" onClick="deleteSalt('${item.fields.sha256sum}')">
                    Delete
                </button>
            </td>`
        }
        counter++
    })

    htmlString += "</tr>"
    datatable.innerHTML = htmlString
    document.getElementById("salt_count").innerHTML = `You have ${products.length} salts in your database...`
    setClickable(datatable)
}

refreshSalts()

function addSalts() {
    fetch("/create", {
        method: "POST",
        body: new FormData(document.querySelector('form'))
    }).then(refreshSalts)

    document.querySelector('form').reset()
}

document.getElementById("button_add").onclick = addSalts

function deleteSalt(hash) {
    fetch(`/delete/${hash}`, {
        method: "DELETE",
    }).then(refreshSalts);
}
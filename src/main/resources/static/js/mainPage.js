import {dataHandler} from "./api.js";
import {createProductCard} from "./productCard.js";

window.addEventListener('load',  async function(event){
    await renderAllProductCards();
});

async function renderAllProductCards(){
    let allProducts = await dataHandler.getAllProducts();
    for (let product of allProducts) {
        const card = await createProductCard(product);
        card.addEventListener('click', function (event){
            location.href = location.href + "product/" + product.id;
        })
        document.querySelector(".container").appendChild(card);
    }
}
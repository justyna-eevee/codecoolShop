import {renderProductCard} from "./productCard.js";
import {dataHandler} from "./api.js";

window.addEventListener('load',  async function(event){
    //let productId = location.href.split("/").slice(-1);
    let productId = 1;
    await renderProductCard(productId);

    let productDataCard = document.getElementsByClassName("card")[0];
    const divDescription = await createProductCardDescriptionField(productId);
    console.log(divDescription);
    productDataCard.insertAdjacentElement('beforeend',divDescription);
})

async function createProductCardDescriptionField(productId){
    const productDescription = (await dataHandler.getProductById(productId)).description;

    let divDescription = document.createElement('div');
    divDescription.classList.add('card__description');
    divDescription.innerText = productDescription;

    return divDescription;
}
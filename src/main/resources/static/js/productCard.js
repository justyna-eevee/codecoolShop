import {dataHandler} from "./api.js";


export async function renderProductCard(productId){
    let productObject = await dataHandler.getProductById(productId);
    let productCard = await createProductCard(productObject);
    document.querySelector(".container").appendChild(productCard);
}

export async function createProductCard(productObject){
    let divWrapper = document.createElement('div');
    divWrapper.classList.add('card');

    let divName = createProductCardNameField(productObject);
    let divPrice = createProductCardPriceField(productObject);
    let divCategory = await createProductCardCategoryField(productObject);
    let divSupplier = await createProductCardSupplierField(productObject);
    let divImage = createProductCardImageField(productObject);

    divWrapper.insertAdjacentElement('beforeend', divName);
    divWrapper.insertAdjacentElement('beforeend', divCategory);
    divWrapper.insertAdjacentElement('beforeend', divImage);
    divWrapper.insertAdjacentElement('beforeend', divPrice);
    divWrapper.insertAdjacentElement('beforeend', divSupplier);

    return divWrapper;
}

function createProductCardNameField(productObject){
    const productName = productObject.name;

    let divName = document.createElement('div');
    divName.classList.add('card__name');
    divName.innerText = productName;

    return divName;
}

function createProductCardPriceField(productObject){
    const defaultPrice = productObject.price;
    const defaultCurrency = productObject.currency;

    let divPrice = document.createElement('div');
    divPrice.classList.add('card__price');
    let divPriceAmount = document.createElement('div');
    divPriceAmount.innerText = defaultPrice;
    let divGap = document.createElement('div');
    divGap.innerHTML = "&nbsp;";
    let divPriceCurrency = document.createElement('div');
    divPriceCurrency.innerText = defaultCurrency;

    divPrice.insertAdjacentElement('beforeend', divPriceAmount);
    divPrice.insertAdjacentElement('beforeend', divGap);
    divPrice.insertAdjacentElement('beforeend', divPriceCurrency);

    return divPrice;
}

async function createProductCardCategoryField(productObject){
    const productCategoryId = productObject.categoryId;
    const productCategory = (await dataHandler.getCategoryById(productCategoryId)).name;

    let divCategory = document.createElement('div');
    divCategory.classList.add('card__category');

    let divSingleCategory = document.createElement('div');
    divSingleCategory.innerText = "#" + productCategory;
    divCategory.insertAdjacentElement('beforeend', divSingleCategory);

    return divCategory;
}

async function createProductCardSupplierField(productObject){
    const supplierId = productObject.supplierId;
    const supplier = (await dataHandler.getSupplierById(supplierId)).name;

    let divSupplier = document.createElement('div');
    divSupplier.classList.add('card__supplier');
    divSupplier.innerText = supplier;

    return divSupplier;
}

function createProductCardImageField(productObject){
    const imagePath = productObject.imagePath;
    const productName = productObject.name;

    let divImage = document.createElement('div');
    divImage.classList.add('card__image');
    let img = document.createElement('img');
    img.src = imagePath;
    img.alt = productName;
    divImage.insertAdjacentElement('beforeend', img);

    return divImage;
}

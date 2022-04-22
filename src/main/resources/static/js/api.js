export let dataHandler = {
    getProductById: async function (productId) {
        return  await apiGet(`/product/${productId}`);
    },
    getProductsFromCategory: async function (categoryId) {
        return await apiGet(`/category/${categoryId}/products`);
    },
    getProductsFromSupplier: async function(supplierId){
        return await apiGet(`/supplier/${supplierId}/products`);
    },
    getCategoryById: async function(categoryId){
        return await apiGet(`/category/${categoryId}`);
    },
    getSupplierById: async function(supplierId){
        return await apiGet(`/supplier/${supplierId}`);
    },
    getAllProducts: async function(){
        return await apiGet(`/products`);
    },



    createNewBoard: async function () {
        const response = await apiPost('/api/boards');
        return response;

    },
    deleteAnyBoard: async function (boardId) {
        const data = { 'id': boardId };
        await apiDelete('/api/boards', data);
    },
    updateBoardTitle: async function (newTitle, boardId, user_name, user_id) {
        const data = {
            'id': boardId,
            'title': newTitle,
            'user_name': user_name,
            'user_id': user_id
        };
        await apiPut(`/api/boards`, data);
    },
};

async function apiGet(url) {
    let response = await fetch(url, {
        method: "GET",
    });
    if (response.status === 200) {
        let data = response.json();
        return data;
    }
}

async function apiPost(url) {
    let response = await fetch(url, {
        method: "POST",
    });
    if (response.status === 200) {
        return await response.json();
    }
}

async function apiDelete(url, data) {
    let response = await fetch(url, {
        method: "DELETE",
        headers: {
            'Content-Type': 'application/json'},
        body: JSON.stringify(data),
    });
    if (response.status === 200) {
        await response.json();
        console.log("Successful DELETE operation")
    }
}

async function apiPut(url, data) {
    let response = await fetch(url, {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json'},
        body: JSON.stringify(data),
    });
    if (response.status === 200) {
        const receivedData = await response.json();
        console.log("PUT sent successfully")
        return receivedData;
    }
}

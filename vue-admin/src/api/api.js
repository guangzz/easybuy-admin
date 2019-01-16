import axios from 'axios';

let base = '/api';
/*
axios.defaults.headers = {
  'Content-type': 'application/x-www-form-urlencoded'
}*/

export const requestLogin = params => { return axios.post (`${base}/login`, params).then(res => res.data); };

export const getUserListPage = params => { return axios.get(`${base}/user/list`, { params: params }); };

export const setUserState = params => { return axios.get(`${base}/user/state`, { params: params }); };

export const editUser = params => { return axios.get(`${base}/user/update`, { params: params }); };

export const getProductList = params => { return axios.get(`${base}/product/list`, { params: params }); };

export const setProductState = params => { return axios.get(`${base}/product/state`, { params: params }); };

export const getSortList = params => { return axios.get(`${base}/sort/select`, { params: params }); };

export const getSort = params => { return axios.get(`${base}/sort/list`, { params: params }); };

export const editSort = params => { return axios.get(`${base}/sort/update`, { params: params }); };

export const insertSort = params => { return axios.get(`${base}/sort/insert`, { params: params }); };

export const deleteSort = params => { return axios.get(`${base}/sort/delete`, { params: params }); };

export const editlevelSorts = params => { return axios.get(`${base}/levelSorts/update`, { params: params }); };

export const deletelevelSorts = params => { return axios.get(`${base}/levelSorts/delete`, { params: params }); };

export const insertlevelSorts = params => { return axios.get(`${base}/levelSorts/insert`, { params: params }); };

export const editProduct = params => { return axios.get(`${base}/product/update`, { params: params }); };

export const addProduct = params => { return axios.get(`${base}/product/add`, { params: params }); };

export const getTodayOrderList =  params => { return axios.get(`${base}/order/today`, { params: params }); };

export const getDetailedOrderList =  params => { return axios.get(`${base}/order/detailed`, { params: params }); };

export const setDelivery =  params => { return axios.get(`${base}/order/delivery`, { params: params }); };

export const getHistoryOrderList =  params => { return axios.get(`${base}/order/history`, { params: params }); };

export const getKeyWordList =  params => { return axios.get(`${base}/keyword/list`, { params: params }); };

export const editKeyWord =  params => { return axios.get(`${base}/keyword/update`, { params: params }); };

export const deleteKeyWord =  params => { return axios.get(`${base}/keyword/delete`, { params: params }); };

export const insertKeyWord =  params => { return axios.get(`${base}/keyword/insert`, { params: params }); };

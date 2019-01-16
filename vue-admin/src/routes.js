import Login from './views/Login.vue'
import NotFound from './views/404.vue'
import Home from './views/Home.vue'
import Main from './views/Main.vue'
import UserList from './views/user/UserList.vue'
import ProductList from './views/product/ProductList.vue'
import OrderList from './views/order/OrderList.vue'
import OrderToday from './views/order/OrderToday.vue'
import Sort from './views/other/Sort.vue'
import KeyWord from './views/other/KeyWord.vue'

let routes = [
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
    //{ path: '/main', component: Main },
    {
        path: '/',
        component: Home,
        name: '用户管理',
        iconCls: 'fa fa-user',//图标样式class
        children: [
            { path: '/user-list', component: UserList, name: '用户列表' },
        ]
    },
    {
        path: '/',
        component: Home,
        name: '商品管理',
        iconCls: 'fa fa-product-hunt',//图标样式class
        children: [
            { path: '/product-list', component: ProductList, name: '商品列表' },
        ]
    },
    {
        path: '/',
        component: Home,
        name: '订单管理',
        iconCls: 'fa fa-bars',//图标样式class
        children: [
            { path: '/today-list', component: OrderToday, name: '今日订单' },
            { path: '/order-list', component: OrderList, name: '历史订单' },
        ]
    },
    {
        path: '/',
        component: Home,
        name: '其他管理',
        iconCls: 'fa fa-cloud',//图标样式class
        children: [
            { path: '/sort-list', component: Sort, name: '分类管理' },
            { path: '/keyword-list', component: KeyWord, name: '关键词管理' },
        ]
    },
    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    }
];

export default routes;
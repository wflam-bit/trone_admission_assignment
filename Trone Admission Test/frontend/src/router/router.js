import { createWebHistory, createRouter } from "vue-router";

const routes = [
    {
        path: "/",
        alias: "/dish",
        name: "dish",
        component: () => import("../components/DishOverview.vue")
    },

];

const router = createRouter({
    history: createWebHistory(),
    routes,
});
export default router;
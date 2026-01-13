import http from "./http-common.js";


class DishDataService{
    getAll(){
        return http.get(`/dishes`);
    }

    get(id){
        return http.get(`/dishes/${id}`);
    }

    create(data){
        return http.post(`/dishes`, data);
    }

    update(id, data){
        return http.put(`/dishes/${id}`, data);
    }

    delete(id){
        return http.delete(`/dishes/${id}`);
    }

}
export default new DishDataService();
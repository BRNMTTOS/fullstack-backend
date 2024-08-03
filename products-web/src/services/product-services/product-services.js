import api from "../api";
import Swal from "sweetalert2";


class ProductServices {
    async getAll() {
      try {
        const response = await api.get("/produtos");
        return response.data; // Retorna apenas os dados da resposta
      } catch (error) {
        // Lida com erros de forma mais elegante
        Swal.fire({
          title: "Erro ao buscar produtos!",
          timer: 900,
          icon: "error"
        });
        throw error; // Permite que o erro seja tratado no componente que chamou a função
      }
    }
    async create(productData) { 
      try {
        const response = await api.post("/produtos", productData);
        Swal.fire({
          title: "Salvo com sucesso!",
          timer: 900,
          icon: "success"
        });
        return response;
      } catch (error) {
        Swal.fire({
          title: "Erro ao criar produto:!",
          timer: 900,
          icon: "error"
        });
        throw error;
      }
    }
    async delete(id) {
      try {
        await api.delete(`/produtos/${id}`);
        return true; // Indica sucesso na exclusão
      } catch (error) {
        Swal.fire({
          title: "Erro ao deletar produto:!",
          timer: 900,
          icon: "error"
        });
        throw error;
      }
    }
    async getById(id) {
      try {
        const response = await api.get(`/produtos/${id}`);
        return response.data;
      } catch (error) {
        Swal.fire({
          title: "Erro ao buscar produto!",
          timer: 900,
          icon: "error"
        });
        throw error;
      }
    }
  
  }
  
  export default new ProductServices(); 
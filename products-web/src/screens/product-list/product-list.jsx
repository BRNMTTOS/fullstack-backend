import React, { useEffect, useState } from "react";
import productServices from "../../services/product-services/product-services";
import { Link, useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

export default function ProductList() {
  const navigate = useNavigate();
  const [products, setProducts] = useState([])

  useEffect(() => {
    getAll()
  }, [])

  const getAll = () => {
    const products = productServices.getAll().then((response) => {
      setProducts(response);
    });
  }

  const handleDelete = async (productId) => {
    Swal.fire({
      title: " Tem certeza?",
      text: "Essa ação é irreversível!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Sim, excluir!"
    }).then((result) => {
      if (result.isConfirmed) {
        productServices.delete(productId).then((response) => {
          if (response) {
            getAll()
          }
        });
        Swal.fire({
          title: "Excluído!",
          text: "O produto foi excluído.",
          icon: "success"
        });
      }
    });
  };

  const handleEdit = (id) => {
    if (!id) {
      navigate('/products/new');
    } else {
      navigate(`/products/${id}`);
    }

  }

  return (
    <main>
      <header>
        <h1>Lista de Produtos</h1>
      </header>
      <div class="button-container">
        <a class="button" onClick={() => handleEdit(null)}>Adicionar Novo Produto</a>
      </div>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Categoria</th>
            <th>Nome</th>
            <th>Preço</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {products.length > 0 ? (
            products.map(product => (
              <tr key={product.id}>
                <td>{product.id}</td>
                <td>{product.categoria?.nome || '-'}</td>
                <td>{product.nome}</td>
                <td>{product.preco}</td>
                <td>
                  <button className="action-button" onClick={() => handleEdit(product.id)}>Editar</button>
                  <button className="action-button" onClick={() => handleDelete(product.id)}>Excluir</button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="5">Vazio</td>
            </tr>
          )}
        </tbody>
      </table>
    </main >
  );
}
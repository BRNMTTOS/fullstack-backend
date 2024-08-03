import React, { useEffect, useState } from "react";
import productServices from "../../services/product-services/product-services";
import { useNavigate, useParams } from "react-router-dom";

export default function ProductEdit() {
  const navigate = useNavigate();
  const [product, setProduct] = useState({
    id: null,
    nome: "",
    preco: null
  });
  const [errors, setErrors] = useState({}); // Estado para armazenar erros
  const { productID } = useParams();

  useEffect(() => {
    if (productID !== "new") {
      productServices.getById(productID).then((response) => {
        setProduct(response)
      })
    }
  }, [productID])

  const handleSubmit = async (event) => {
    event.preventDefault();
    const validationErrors = validateProduct(product);
    if (Object.keys(validationErrors).length > 0) {
      setErrors(validationErrors); // Atualiza o estado com os erros
      return; // Não envia o formulário se houver erros
    }

    try {
      const response = await productServices.create(product);
      if (response && response.status === 200) {
        navigate('/');
      }
    } catch (error) {
      // Lida com erros da API
      console.error("Erro ao criar produto:", error);
      // Você pode adicionar aqui um alerta de erro para o usuário, se desejar
    }
  };

  const validateProduct = (product) => {
    const errors = {};
    if (!product.nome.trim()) {
      errors.nome = "O nome é obrigatório";
    }
    if (product?.preco <= 0 || isNaN(product?.preco)) {
      errors.preco = "O preço deve ser um número positivo";
    }
    return errors;
  };

  return (
    <>
      <header>
        <h1>Produto - {productID!== "new" ? "Editar" : "Adicionar"}</h1>
      </header>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="nome">Nome:</label>
          <input
            type="text"
            id="nome"
            value={product.nome}
            onChange={(e) => setProduct({ ...product, nome: e.target.value })}
          />
          {errors.nome && <p style={{ color: "red" }}>{errors.nome}</p>}
        </div>
        <div>
          <label htmlFor="preco">Preço:</label>
          <input
            type="number"
            id="preco"
            value={product.preco}
            onChange={(e) =>
              setProduct({ ...product, preco: parseFloat(e.target.value) || 0 })
            }
          />
          {errors.preco && <p style={{ color: "red" }}>{errors.preco}</p>}
        </div>
        <button type="submit">{productID !== "new" ? "Editar Produto" : "Adicionar Produto"}</button>
      </form>
    </>

  );
}


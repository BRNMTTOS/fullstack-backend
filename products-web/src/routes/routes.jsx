import {
  createBrowserRouter,
} from "react-router-dom";
import ProductList from "../screens/product-list/product-list";
import ProductEdit from "../screens/product-edit/product-edit";


export const router = createBrowserRouter([
  {
    path: "/",
    element: <ProductList/>,
  },
  {
    path: "products/:productID",
    element: <ProductEdit/>
  }
]);
import './globals.css'

import { RouterProvider, createBrowserRouter } from 'react-router-dom'

import AddProduct from './pages/AddProduct'
import AddRating from './pages/AddRating'
import Product from './pages/Product'
import ProductList from './pages/ProductList'
import React from 'react'
import ReactDOM from 'react-dom/client'

const router = createBrowserRouter([
	{
		path: '/',
		element: <ProductList />,
	},
	{
		path: '/product',
		element: <Product />,
	},
	{
		path: '/list',
		element: <ProductList />,
	},
	{
		path: '/addProduct',
		element: <AddProduct />,
	},
	{
		path: '/addRating',
		element: <AddRating />,
	},
])

ReactDOM.createRoot(document.getElementById('root')!).render(
	<React.StrictMode>
		<RouterProvider router={router} />
	</React.StrictMode>
)

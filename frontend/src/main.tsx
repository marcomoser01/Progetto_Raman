import React from 'react'
import ReactDOM from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import './globals.css'
import App from './App'
import Product from './pages/Product'
import ProductList from './pages/ProductList'

const router = createBrowserRouter([
	{
		path: '/',
		element: <App />,
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
		element: <ProductList />,
	},
	{
		path: '/addRating',
		element: <ProductList />,
	},
])

ReactDOM.createRoot(document.getElementById('root')!).render(
	<React.StrictMode>
		<RouterProvider router={router} />
	</React.StrictMode>
)

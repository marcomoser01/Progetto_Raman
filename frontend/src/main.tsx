import './globals.css'

import { RouterProvider, createBrowserRouter } from 'react-router-dom'

import AddProduct from './pages/AddProduct'
import AddRating from './pages/AddRating'
import Product from './pages/Product'
import ProductList from './pages/ProductList'
import React from 'react'
import ReactDOM from 'react-dom/client'
import { Toaster } from '@/components/ui/toaster'
import UsersList from './pages/UsersList'
import AddUser from './pages/AddUser'

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
		path: '/addProduct',
		element: <AddProduct />,
	},
	{
		path: '/addRating',
		element: <AddRating />,
	},
	{
		path: '/Users',
		element: <UsersList />,
	},
	{
		path: '/addUser',
		element: <AddUser />,
	},
])

ReactDOM.createRoot(document.getElementById('root')!).render(
	<React.StrictMode>
		<RouterProvider router={router} />
		<Toaster></Toaster>
	</React.StrictMode>
)

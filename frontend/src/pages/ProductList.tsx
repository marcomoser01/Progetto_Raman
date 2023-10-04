/* eslint-disable @typescript-eslint/no-unused-vars */

import { useEffect, useState } from 'react'

import { Button } from '@/components/ui/button'
import { Link } from 'react-router-dom'
import { Product } from '@/lib/types'
import Typography from '@/components/Typography'
import { cn } from '@/lib/utils'
import { fetchPopularProducts } from '@/lib/fetch'

export default function ProductList() {
	const [popularProducts, setPopularProducts] = useState<Product[] | []>()

	function openProduct(product: Product) {
		localStorage.setItem('product', JSON.stringify(product))
		location.assign('/Product') //got to that product page
	}

	// function goToAddProduct() {
	// 	// const productId: number = 1234 //TODO get this dinamically. this should NOT exist in the DB.

	// 	// localStorage.setItem('productId', productId.toString())
	// 	// go to page /addProduct
	// 	location.assign('/addProduct')
	// }

	useEffect(() => {
		fetchPopularProducts().then(data => {
			setPopularProducts(data)
		})
	}, [])

	useEffect(() => {
		console.log(popularProducts)
	}, [popularProducts])

	return (
		<main className="max-w-2xl mx-auto my-4 px-2 py-4">
			<Typography variant="h1">Popular Products List</Typography>
			<br />
			<Button asChild>
				<Link to="/addProduct">Add Product</Link>
			</Button>
			{/* table */}
			<div
				className={cn('my-6 w-full overflow-y-auto', {
					hidden: !popularProducts || popularProducts?.length === 0,
				})}
			>
				<table className="w-full">
					{/* HEADER */}
					<thead>
						<tr className="m-0 border-t p-0 even:bg-muted">
							<th className="border px-4 py-2 text-left font-bold [&[align=center]]:text-center [&[align=right]]:text-right">
								Product ID
							</th>
							<th className="border px-4 py-2 text-left font-bold [&[align=center]]:text-center [&[align=right]]:text-right">
								Product Title
							</th>
							<th className="border px-4 py-2 text-left font-bold [&[align=center]]:text-center [&[align=right]]:text-right">
								Price
							</th>
							<th className="border px-4 py-2 text-left font-bold [&[align=center]]:text-center [&[align=right]]:text-right">
								Average Rating (stelline)
							</th>
						</tr>
					</thead>

					{/* DATA */}
					<tbody>
						{popularProducts &&
							popularProducts
								.sort((a, b) => b.rating - a.rating) //descending order
								.map((product, index) => (
									<tr
										className="m-0 border-t p-0 even:bg-muted cursor-pointer hover:bg-slate-900/10"
										tabIndex={0}
										key={index}
										onClick={() => openProduct(product)}
									>
										<td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
											{product.id || -1}
										</td>
										<td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
											{product.title || 'prodotto'}
										</td>
										<td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
											{product.price || 0}€
										</td>
										<td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
											{product.rating || 0}
										</td>
									</tr>
								))}
					</tbody>
				</table>
			</div>

			{(!popularProducts || popularProducts?.length === 0) && (
				<Typography variant="p">
					(┬┬﹏┬┬) There seems to be no products left.
					<br />
					Add one clicking the button above.
				</Typography>
			)}
		</main>
	)
}

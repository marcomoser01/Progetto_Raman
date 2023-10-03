/* eslint-disable @typescript-eslint/no-unused-vars */

import { useEffect, useState } from 'react'

import { Button } from '@/components/ui/button'
import Typography from '@/components/Typography'

export default function ProductList() {
	const [popularProducts, setPopularProducts] = useState<object[]>()

	function openProduct(id) {
		localStorage.setItem('productId', id.toString())
		// go to page /Product
		location.assign('/Product')
	}

	function goToAddProduct() {
		const productId: number = 1234 //TODO get this dinamically. this should NOT exist in the DB.

		localStorage.setItem('productId', productId.toString())
		// go to page /addProduct
		location.assign('/addProduct')
	}

	async function fetchPopularProducts() {
		const response = await fetch(URL_BASE + `/popular`)
		if (!response.ok) {
			return []
		}

		const ratingsJSON = await response.json()
		if (!ratingsJSON.ok) {
			return []
		}

		const retrievedRatings: object[] = JSON.parse(ratingsJSON)
		if (!Array.isArray(retrievedRatings)) {
			return []
		}
		console.log('retrievedFetchThing:\n', retrievedRatings)

		return retrievedRatings || []
	}

	useEffect(() => {
		fetchPopularProducts().then(data => {
			setPopularProducts(data)
		})
	}, [])

	return (
		<main className="max-w-2xl mx-auto my-4">
			<Typography variant="h1">Popular Product List</Typography>
			<br />
			<Button onClick={goToAddProduct}>Add Product</Button>
			{/* table */}
			<div className="my-6 w-full overflow-y-auto">
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
								Average Vote
							</th>
						</tr>
					</thead>

					{/* DATA */}
					<tbody>
						{popularProducts &&
							popularProducts
								.sort((a, b) => b.vote - a.vote) //descending order
								.map(product => (
									<tr
										className="m-0 border-t p-0 even:bg-muted"
										tabIndex={0}
										onClick={(id = product.productId) => openProduct(id)}
									>
										<td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
											{product.productId}
										</td>
										<td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
											{product.title}
										</td>
										<td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
											{product.price}
										</td>
										<td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
											{product.vote}
										</td>
									</tr>
								))}
					</tbody>
				</table>
			</div>
		</main>
	)
}

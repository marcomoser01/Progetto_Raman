/* eslint-disable @typescript-eslint/no-unused-vars */

import { Product, ProductWithAVGVote } from '@/lib/types'
import { useEffect, useState } from 'react'

import { Button } from '@/components/ui/button'
import { Link } from 'react-router-dom'
import Typography from '@/components/Typography'
import { cn } from '@/lib/utils'
import { fetchPopularProducts } from '@/lib/fetch'

export default function ProductList() {
	const [popularProducts, setPopularProducts] = useState<ProductWithAVGVote[]>()

	function openProduct(product: Product) {
		// localStorage.setItem('product', JSON.stringify(product))
		localStorage.setItem('product', JSON.stringify(product))
		location.assign('/Product') //got to that product page
	}

	useEffect(() => {
		fetchPopularProducts().then(data => {
			console.log(data)
			setPopularProducts(data)
		})
	}, [])

	return (
		<main className="max-w-2xl mx-auto my-4 px-2 py-4">
			<div className='flex flex-row justify-between items-end'>
				<Typography variant="h1">Popular Products List</Typography>

				<Button asChild>
					<Link to="/addProduct">Add Product</Link>
				</Button>
			</div>
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
								ID
							</th>
							<th className="border px-4 py-2 text-left font-bold [&[align=center]]:text-center [&[align=right]]:text-right">
								Product
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
								// .sort((a, b) => b.vote - a.vote) //descending order
								.map((product, index) => {
									const productItem: Product = product?.product
									return (
										<tr
											className="m-0 border-t p-0 even:bg-gray-100 cursor-pointer hover:bg-slate-900/10 "
											tabIndex={0}
											key={index}
											onClick={() => openProduct(productItem)}
											onKeyDown={e =>
												e.key === 'Enter' ? openProduct(productItem) : ''
											}
										>
											<td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
												{productItem.id || -1}
											</td>
											<td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
												{productItem.title || 'FETCH FAILED'}
											</td>
											<td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
												{productItem.price || 0}€
											</td>
											<td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
												{product.avgVote || 0}
											</td>
										</tr>
									)
								})}
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
